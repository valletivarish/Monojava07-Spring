package com.monocept.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.myapp.entity.ImageStructure;

public interface FileRepository extends JpaRepository<ImageStructure, Integer>{

	ImageStructure findByName(String fileName);

}
