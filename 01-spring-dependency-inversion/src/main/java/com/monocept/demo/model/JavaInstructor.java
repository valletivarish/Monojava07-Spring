package com.monocept.demo.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JavaInstructor implements Instructor {
	private Resource resource1;
	private Resource resource2;
	
	@Value("${myapp.collegeName}")
	private String collegeName;

	public JavaInstructor(Resource resource1 ,Resource resource2 ) {
		super();
		this.resource1 = resource1;
		this.resource2 = resource2;
	}

	@Override
	public String getTrainingPlan() {
		
		return "Practice oops content";
	}

	@Override
	public String getResource() {
		return "is this bean equal"+" "+resource1.equals(resource2)+" "+collegeName;
	}

}
