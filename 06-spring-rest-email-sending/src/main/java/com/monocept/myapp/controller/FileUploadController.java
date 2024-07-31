package com.monocept.myapp.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.myapp.entity.ImageStructure;
import com.monocept.myapp.service.FileService;


@RestController
public class FileUploadController {
	
	private FileService fileService;
	
	
	
	public FileUploadController(FileService fileService) {
		super();
		this.fileService = fileService;
	}



	@PostMapping("upload-file")
	public ResponseEntity<String> fileUpload(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException {
		if(multipartFile.isEmpty()) {
			return new ResponseEntity<String>("File is empty",HttpStatus.BAD_REQUEST);
		}
//		if(!multipartFile.getContentType().equals("image/jpeg")) {
//			return new ResponseEntity<String>("only jpeg file types are allowed",HttpStatus.BAD_REQUEST);
//		}
		ImageStructure uploadFile = fileService.uploadFile(multipartFile);
		if(uploadFile!=null) {
			return new ResponseEntity<String>("file successfully uploaded",HttpStatus.ACCEPTED);
		}
		
		return new ResponseEntity<String>("some thing went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
