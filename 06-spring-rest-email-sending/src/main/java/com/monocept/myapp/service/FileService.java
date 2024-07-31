package com.monocept.myapp.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.monocept.myapp.entity.ImageStructure;
import com.monocept.myapp.repository.FileRepository;
import com.monocept.myapp.util.ImageUtil;

@Service
public class FileService {
	
	FileRepository fileRepository;
	
	
	public FileService(FileRepository fileRepository) {
		super();
		this.fileRepository = fileRepository;
	}

	public ImageStructure uploadFile(MultipartFile file) throws IOException {
		ImageStructure image=new ImageStructure(0, file.getOriginalFilename(), file.getContentType(), ImageUtil.compressImage(file.getBytes()));
		ImageStructure save = fileRepository.save(image);
		return save;
		
	}

	public byte[] downloadFile(String fileName) throws IOException {
		ImageStructure image=fileRepository.findByName(fileName);
		byte[] decompressImage = ImageUtil.decompressImage(image.getImageData());
		return decompressImage;
	}
}
