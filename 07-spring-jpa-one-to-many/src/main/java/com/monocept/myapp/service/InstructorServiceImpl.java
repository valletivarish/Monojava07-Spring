package com.monocept.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monocept.myapp.entity.Course;
import com.monocept.myapp.entity.Instructor;
import com.monocept.myapp.repository.CourseRepository;
import com.monocept.myapp.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	private InstructorRepository instructorRepository;
	private CourseRepository courseRepository;
	
	
	
	public InstructorServiceImpl(InstructorRepository instructorRepository,CourseRepository courseRepository) {
		super();
		this.instructorRepository = instructorRepository;
		this.courseRepository=courseRepository;
	}



	@Override
	public List<Instructor> getAllInstructors() {
		return instructorRepository.findAll();
	}

	

	@Override
	public Instructor saveInstructor(Instructor instructor) {
		if(instructor.getId()==0) {
			return instructorRepository.save(instructor);
		}
		Instructor instructorById = getInstructorById(instructor.getId());
		if(instructorById!=null) {
			return instructorRepository.save(instructor);
		}
		return null;
	}




	@Override
	public Instructor getInstructorById(int id) {
		return instructorRepository.findById(id).orElse(null);
	}



	@Override
	public String deleteInstructor(int id) {
		Instructor instructorById = getInstructorById(id);
		if(instructorById!=null) {
			instructorRepository.delete(instructorById);
			
			return "Deleted Succesfully";
		}
		return "no instructor found";
	}



	@Override
	public String updateCourse(String id, Course course) {
		return null;
	}



	@Override
    public Instructor removeCourseFromInstructor(int instructorID, int courseID) {
        Instructor instructor = instructorRepository.findById(instructorID).orElse(null);
        if (instructor != null) {
            Course course = courseRepository.findById(courseID).orElse(null);
            if (course != null) {
                instructor.removeCourse(course);
                courseRepository.save(course);
                return instructorRepository.save(instructor);
            }
        }
        return null;
    }



	@Override
	public Instructor asignCourseToInstructor(int instructorID, int courseID) {
		Instructor instructor = instructorRepository.findById(instructorID).orElse(null);
		if(instructor!=null) {
			Course course = courseRepository.findById(courseID).orElse(null);
			if(course.getInstructor()==null) {
				instructor.addCourse(course);
				course.setInstructor(instructor);
				return instructorRepository.save(instructor);
			}
		}
		return null;
	}
	
	
	

}
