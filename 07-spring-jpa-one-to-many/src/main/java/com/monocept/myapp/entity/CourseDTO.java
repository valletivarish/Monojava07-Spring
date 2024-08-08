package com.monocept.myapp.entity;

public class CourseDTO {
	private String name;
	private int duration;
	public CourseDTO(String name, int duration) {
		super();
		this.name = name;
		this.duration = duration;
	}
	public CourseDTO() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
