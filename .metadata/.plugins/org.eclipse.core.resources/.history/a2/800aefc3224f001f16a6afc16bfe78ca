package com.monocept.myapp.controller;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.myapp.service.FileService;


@RestController
public class FileDownloadController {

	private FileService fileService;

	public FileDownloadController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@GetMapping("download-file")
	public ResponseEntity<byte[]> postMethodName(@RequestParam(name = "fileName") String fileName) throws IOException {	
		byte[] resource=fileService.downloadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(resource);
	}
	
}
