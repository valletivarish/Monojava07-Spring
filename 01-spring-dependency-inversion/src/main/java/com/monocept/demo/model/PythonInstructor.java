package com.monocept.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PythonInstructor implements Instructor {
	
//	@Autowired
//	@Qualifier(value = "videoResource")
	private Resource resource;
	

//	public PythonInstructor(@Qualifier(value="videoResource") Resource resource) {
//		super();
//		this.resource = resource;
//	}
	
	@Autowired
	@Qualifier(value = "videoResource")
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	

	@Override
	public String getTrainingPlan() {
		// TODO Auto-generated method stub
		return "practicing linear regression on house dataset";
	}


	@Override
	public String getResource() {
		// TODO Auto-generated method stub
		return this.resource.getResource();
	}

}
