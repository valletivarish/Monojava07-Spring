package com.monocept.myapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.myapp.entity.MailStructure;
import com.monocept.myapp.service.EmailService;

import jakarta.mail.MessagingException;


@RestController
@RequestMapping("/mail")
public class MailController {
	
	
	private EmailService service;
	
	
	
	public MailController(EmailService service) {
		super();
		this.service = service;
	}



	@PostMapping("/send/{email}")
	public String postMethodName(@PathVariable(name = "email")String mail , @RequestBody MailStructure mailStructure) {
		if(service.sendEmail(mail, mailStructure)) {
			return "sent Succesfull";
		}
		return "fail to send";
		
}
	@PostMapping("/sendWithAttachment/{email}")
    public String sendEmailWithAttachment(@PathVariable(name = "email") String mail, @RequestBody MailStructure mailStructure, @RequestParam(name = "filepath") String path) throws MessagingException {
        if (service.sendEmailWithAttachment(mail, mailStructure, path)) {
            return "sent successfully";
        }
        return "failed to send";
    }

}
