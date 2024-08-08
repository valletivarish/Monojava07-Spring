package com.monocept.myapp.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monocept.myapp.dto.LoginDto;
import com.monocept.myapp.dto.RegisterDto;
import com.monocept.myapp.entity.Role;
import com.monocept.myapp.entity.User;
import com.monocept.myapp.exception.BankApiException;
import com.monocept.myapp.repository.RoleRepository;
import com.monocept.myapp.repository.UserRepository;
import com.monocept.myapp.security.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {
	
	Logger logger=LoggerFactory.getLogger(AuthServiceImpl.class);

	private AuthenticationManager authenticationManager;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private JwtTokenProvider jwtTokenProvider;

	public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String login(LoginDto loginDto) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}

    @Override
    public String register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new BankApiException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new BankApiException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role;
        if (registerDto.isAdmin()) {
            role = roleRepository.findByName("ROLE_ADMIN").orElseThrow(() ->
                    new BankApiException(HttpStatus.BAD_REQUEST, "Admin role not set up in the database"));
        } else {
            role = roleRepository.findByName("ROLE_CUSTOMER").orElseThrow(() ->
                    new BankApiException(HttpStatus.BAD_REQUEST, "User role not set up in the database"));
        }
        logger.info("Registering user {} with role {}", registerDto.getName(), registerDto.isAdmin() ? "ROLE_ADMIN" : "ROLE_CUSTOMER");
        roles.add(role);
        user.setRoles(roles);
       
        User savedUser = userRepository.save(user);
        logger.debug("User with ID {} registered successfully", savedUser.getId());


        return "User registered successfully!";
    }
}
