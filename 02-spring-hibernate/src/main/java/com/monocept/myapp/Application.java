package com.monocept.myapp;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monocept.myapp.dao.StudentDao;
import com.monocept.myapp.entity.Student;

@SuppressWarnings("unused")
@SpringBootApplication
public class Application implements CommandLineRunner{
	
	private StudentDao studentDao;
	

	public Application(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 //addStudent();
		//getAllStudents();
		//getStudentById();
		//getStudentByFirstName();
		//getStudentByFirstNameAndLastName();
		//updateStudent();
		//deleteStudent();
		//updateStudentWithoutMerge();
		//deleteAllStudents();
	}

	private void deleteAllStudents() {
		studentDao.deleteAllStudents(7);
		
	}

	private void updateStudentWithoutMerge() {
		Student s=new Student(1,"varish","valleti","varishvalleti@gmail.com");
		studentDao.updateStudentWithoutMerge(s);
		getStudentById();
		
	}

	private void deleteStudent() {
		studentDao.deleteStudent(7);
		
	}

	private void updateStudent() {
		Student student=new Student(1,"varish","valleti","varishvalleti52@gmail.com");
		studentDao.updateStudent(student);
		
	}

	private void getStudentByFirstNameAndLastName() {
		List<Student> studentList=studentDao.getStudentByFirstNameAndLastName("varish","valleti");
		for(Student s:studentList) {
			System.out.println(s);
		}
		
	}

	private void getStudentByFirstName() {
		List<Student> studentList=studentDao.getStudentByFirstName("varish");
		for(Student s:studentList) {
			System.out.println(s);
		}
	}

	private void getStudentById() {
		int id=1;
		Student s=studentDao.getStudentById(id);
		if(s!=null)
			System.out.println(s);
		else {
			System.out.println("no student found with the id "+id);
		}
	}

	@SuppressWarnings("unused")
	private void getAllStudents() {
		List<Student> studentList=studentDao.getAllStudents();
		System.out.println(studentList);
		
	}

	@SuppressWarnings("unused")
	private void addStudent() {
		Student student =new Student("Vishnu", "Ravula", "vishnuravula@gmail.com");
		studentDao.save(student);
	}

}
