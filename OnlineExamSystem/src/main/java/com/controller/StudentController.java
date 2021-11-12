package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.StudentDTO;
import com.entity.Course;
import com.entity.Student;
import com.serviceimplementation.AdminStudentManagement;
import com.serviceimplementation.StudentExam;
import com.serviceimplementation.StudentRegister;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

	@Autowired
	StudentRegister studentservice;
	
	@Autowired
	AdminStudentManagement adminStudentManagement;
	
	@Autowired
	StudentExam studentExam;

	@PostMapping(path = "/login")
	public ResponseEntity<Boolean> studentLogin(@RequestBody Student s) throws Throwable {

		boolean existence = studentservice.loginStudent(s.getUsername(), s.getPassword());
		ResponseEntity re = new ResponseEntity<Boolean>(existence, HttpStatus.OK);
		return re;

	}

	@PutMapping(path = "/updateStudent")
	public ResponseEntity<Student> updateStudent(@Valid @RequestBody StudentDTO s) throws Throwable {
		Student n = studentservice.updateStudentDetails(s);
		ResponseEntity re = new ResponseEntity<Student>(n, HttpStatus.OK);
		return re;
	}

	@PostMapping(path = "/newRegister")
	public ResponseEntity<Student> newRegistration(@Valid @RequestBody Student s) {
		Student stu = studentservice.registerNewStudent(s);
		ResponseEntity re = new ResponseEntity<Student>(stu, HttpStatus.OK);
		return re;
	}

	@GetMapping(path = "/getallCourses")
	public ResponseEntity<List<Student>> getAllCourses() {
		List<Course> list = studentservice.findAllCourses();
		ResponseEntity re = new ResponseEntity<List<Course>>(list, HttpStatus.OK);
		return re;
	}

	@GetMapping(path = "/deEnrollstudent/{student_id}/{course_id}/{batch_Name}/{enroll_id}")
	public ResponseEntity<Student> deEnrollStudent(@PathVariable int student_id, @PathVariable int course_id,
			@PathVariable String batch_Name, @PathVariable int enroll_id) throws Throwable {
		ResponseEntity re = new ResponseEntity<Student>(adminStudentManagement.deEnRollstudent(student_id, course_id, batch_Name, enroll_id), HttpStatus.OK);
		return re;
	}
}
