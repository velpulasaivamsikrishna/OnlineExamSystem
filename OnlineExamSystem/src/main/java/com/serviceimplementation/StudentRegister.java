package com.serviceimplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.StudentDTO;
import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Student;
import com.repository.CourseRepository;
import com.repository.StudentRepository;
import com.service.StudentRegisterService;

@Service
public class StudentRegister implements StudentRegisterService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Override
	public boolean loginStudent(String username, String password) throws DataNotFoundedException {

		Student stu = studentRepository.findByusername(username).orElseThrow(()->new DataNotFoundedException("username or password is not found in database"));

		if (stu.getUsername().equals(username) && stu.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public Student registerNewStudent(Student student) {
		return studentRepository.save(student);

	}

	@Override
	public Student updateStudentDetails(StudentDTO student) throws DataNotFoundedException {
		int stuId = student.getStudentId();

		Student stu = studentRepository.findById(stuId).orElseThrow(()-> new DataNotFoundedException("StudentId is not found in database"));
		
		if(student.getEmail() != null)
		{
			stu.setEmail(student.getEmail());
		}
		
		if(student.getFirstName() != null)
		{
			stu.setFirstName(student.getFirstName());
		}
		
		if(student.getGender() != null)
		{
			stu.setGender(student.getGender());
		}
		
		if(student.getLastName() != null)
		{
			stu.setLastName(student.getLastName());
		}
		
		if(student.getMobileNo() != null)
		{
			stu.setMobileNo(student.getMobileNo());
		}
		
		if(student.getPassword() != null)
		{
			stu.setPassword(student.getPassword());
		}
		
		if(student.getUsername() != null)
		{
			stu.setUsername(student.getUsername());
		}
		
		return studentRepository.save(stu);
	}

	@Override
	public List<Course> findAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Student updateStudentDetails(Student s2) throws DataNotFoundedException {
		int stuId = s2.getStudentId();

		Student stu = studentRepository.findById(stuId).orElseThrow(()-> new DataNotFoundedException("StudentId is not found in database"));
		
		if(s2.getEmail() != null)
		{
			stu.setEmail(s2.getEmail());
		}
		
		if(s2.getFirstName() != null)
		{
			stu.setFirstName(s2.getFirstName());
		}
		
		if(s2.getGender() != null)
		{
			stu.setGender(s2.getGender());
		}
		
		if(s2.getLastName() != null)
		{
			stu.setLastName(s2.getLastName());
		}
		
		if(s2.getMobileNo() != null)
		{
			stu.setMobileNo(s2.getMobileNo());
		}
		
		if(s2.getPassword() != null)
		{
			stu.setPassword(s2.getPassword());
		}
		
		if(s2.getUsername() != null)
		{
			stu.setUsername(s2.getUsername());
		}
		
		return studentRepository.save(stu);
	}

}
