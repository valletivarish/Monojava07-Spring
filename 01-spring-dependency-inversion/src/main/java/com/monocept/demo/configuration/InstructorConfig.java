package com.monocept.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.monocept.demo.model.PdfResource;
import com.monocept.demo.model.Resource;
import com.monocept.demo.model.VideoResource;

@Configuration
public class InstructorConfig {

    @Bean(name = "pdfResource")
    @Primary
    public Resource getPdfResourceObject() {
		return new PdfResource();
	}
    
    @Bean(name = "videoResource")
    public Resource getVidoResourceObject() {
		return new VideoResource();
	}
}
