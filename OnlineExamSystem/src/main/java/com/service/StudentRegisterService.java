package com.service;

import java.util.List;
import java.util.function.IntPredicate;

import com.DTO.StudentDTO;
import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Student;

public interface StudentRegisterService {
	boolean loginStudent(String username,String password) throws DataNotFoundedException;
	Student registerNewStudent(Student student);
	//Student updateStudentDetails(Student student) throws DataNotFoundedException;
	List<Course> findAllCourses();
	Student updateStudentDetails(StudentDTO student) throws DataNotFoundedException;
	Student updateStudentDetails(Student s2) throws DataNotFoundedException;
	

}
