package com.monocept.myapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.myapp.entity.Instructor;
import com.monocept.myapp.service.InstructorService;

@RestController
@RequestMapping("/Instructor")
public class InstructorController {

	private InstructorService instructorService;
	
	

	public InstructorController(InstructorService instructorService) {
		super();
		this.instructorService = instructorService;
	}



	@GetMapping
	public List<Instructor> getAllDetails() {
		return instructorService.getAllInstructors();
	}
	@GetMapping("{id}")
	public Instructor getInstructoById(@PathVariable(name = "id") int id) {
		return instructorService.getInstructorById(id);
	}
	@PostMapping
	public Instructor saveInstructor(@RequestBody Instructor instructor) {
		return instructorService.saveInstructor(instructor);
	}
	@PutMapping
	public Instructor updateInstructor(@RequestBody Instructor instructor) {
		return instructorService.saveInstructor(instructor);
	}
	@DeleteMapping("{id}")
	public String deleteInstructor(@PathVariable(name = "id")int id) {
		return instructorService.deleteInstructor(id);
		
	}
	
	@PutMapping("{instructorid}/course/{courseid}/remove")
	public Instructor removeCourseFromInstructor(@PathVariable(name = "instructorid")int instructorID, @PathVariable(name = "courseid")int courseID ) {
		return instructorService.removeCourseFromInstructor(instructorID,courseID);
		
	}
	
	@PutMapping("{instructorid}/course/{courseid}/add")
	public Instructor assignCourseToInstructor(@PathVariable(name = "instructorid")int instructorID, @PathVariable(name = "courseid")int courseID ) {
		return instructorService.asignCourseToInstructor(instructorID,courseID);
		
	}
	

}
