package com.monocept.demo.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.demo.model.Instructor;
@RestController
public class InstructorController {
	private Instructor instructor;
	
	
	public InstructorController(@Qualifier(value="javaInstructor") Instructor instructor) {
		super();
		this.instructor = instructor;
	}


	@GetMapping("/train-map")
	public String getMessage() {
		return this.instructor.getTrainingPlan()+"<br>"+this.instructor.getResource() ;
		
	}
}
