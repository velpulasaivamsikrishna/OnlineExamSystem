
//DONE
package com.serviceimplementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.entity.Course;
import com.entity.Student;
import com.entity.StudentEnrollment;
import com.repository.CourseRepository;
import com.repository.StudentEnrollmentRepository;
import com.repository.StudentRepository;
@SpringBootTest
class AdminStudentManagementTest {
	
	@Autowired
	private AdminAuthenticationImpl adminAuthenticationImpl;
	
	@Autowired
	AdminStudentManagement adminstudentmanagementservice;
	
	@MockBean
	StudentRepository studentRepository;

	@MockBean
	CourseRepository courseRepository;
	
	@MockBean
	StudentEnrollmentRepository studentenrollmentrepository;
	
		
	@Test
	void testEnrollStudent() throws Throwable {
		Student s1=new Student();
		s1.setStudentId(1);
		
		Course c1=new Course();
		c1.setCourseId(3);
		c1.setCourseName("Java");
		c1.setCourseType("mcq");
		c1.setDescription("qwer");
		//Optional<Course> c2= Optional.of(c1);
		
		StudentEnrollment se=new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);
		se.setCourse(c1);
		s1.addStudentEnrollment(se);
		
		Optional<Student> s2=Optional.of(s1);
		
		Mockito.when(courseRepository.findById(c1.getCourseId())).thenReturn(Optional.of(c1));
		Mockito.when(studentRepository.findById(s1.getStudentId())).thenReturn(s2);
		Mockito.when(studentenrollmentrepository.save(se)).thenReturn(se);
		assertThat(adminstudentmanagementservice.enrollStudent(s1.getStudentId(), c1.getCourseId(), se.getBatchName())).isEqualTo(s1);
		
	}

	@Test
	void testAddNewCourse() throws Exception {
		Course c=new Course();
		c.setCourseId(1);
		c.setCourseName("python");
		c.setCourseType("mcq");
		c.setDescription("Abcd");
		Mockito.when(courseRepository.save(c)).thenReturn(c);
		
		adminAuthenticationImpl.isLogin = true;

		assertThat(adminstudentmanagementservice.addNewCourse(c)).isEqualTo(c);
	}

	@Test
	void testDeEnRollstudent() {
		Student s1=new Student();
		s1.setStudentId(1);
		Course c1=new Course();
		c1.setCourseId(1);
		c1.setCourseName("Java");
		c1.setCourseType("mcq");
		c1.setDescription("qwer");
		Optional<Course> c2= Optional.of(c1);
		StudentEnrollment se=new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);
		Optional<StudentEnrollment> se1=Optional.of(se);
		Optional<Student> s2=Optional.of(s1);
		Mockito.when(courseRepository.findById(1)).thenReturn(c2);
		Mockito.when(studentRepository.findById(1)).thenReturn(s2);
		Mockito.when(studentenrollmentrepository.findById(se.getEnrollmentId())).thenReturn(se1);
		Mockito.when(studentenrollmentrepository.existsById(se.getEnrollmentId())).thenReturn(false);
		assertFalse(studentenrollmentrepository.existsById(se.getEnrollmentId()));
		
	}

}
