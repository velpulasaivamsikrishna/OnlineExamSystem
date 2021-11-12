package com.serviceimplementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.entity.Course;
import com.entity.Student;
import com.repository.CourseRepository;
import com.repository.StudentRepository;
import com.service.StudentRegisterService;
@SpringBootTest
class StudentRegisterTest {
	@MockBean
	StudentRepository studentrepo;
	
	@MockBean
	CourseRepository courserepo;
	
	@Autowired
	StudentRegisterService studentregisterservice;
	

	@Test
	void testLoginStudent() throws Throwable {
		Student s= new Student();
		s.setUsername("hema");
		s.setPassword("he@45678");
		Optional<Student> t=Optional.of(s);
		Mockito.when(studentrepo.findByusername(s.getUsername())).thenReturn(t);
		
		assertThat(studentregisterservice.loginStudent(s.getUsername(), s.getPassword())).isEqualTo(true);
	
	}

	@Test
	void testRegisterNewStudent() {
			Student s1= new Student();
			s1.setStudentId(1);
			s1.setUsername("renu");
			s1.setPassword("hem@3458");
			s1.setFirstName("Hema");
			s1.setLastName("Sree");
			s1.setGender("female");
			s1.setEmail("hema@gmail.com");
			s1.setMobileNo("9080706050");
			
			Mockito.when(studentrepo.save(s1)).thenReturn(s1);
			assertThat(studentregisterservice.registerNewStudent(s1)).isEqualTo(s1);
	}

	@Test
	void testUpdateStudentDetails() throws Throwable {
		Student s2= new Student();
		s2.setStudentId(1);
		s2.setUsername("renu");
		s2.setPassword("hem@3458");
		s2.setFirstName("Hema");
		s2.setLastName("Sree");
		s2.setGender("female");
		s2.setEmail("hema@gmail.com");
		s2.setMobileNo("9080706050");
		Optional<Student> s3=Optional.of(s2);
		Mockito.when(studentrepo.findById(s2.getStudentId())).thenReturn(s3);
		s2.setUsername("navya");
		s2.setPassword("navya@123");
		s2.setFirstName("Navya");
		s2.setLastName("Hanitha");
		s2.setGender("female");
		s2.setEmail("navya@gmail.com");
		s2.setMobileNo("9987654322");
		Mockito.when(studentrepo.save(s2)).thenReturn(s2);
		assertThat(studentregisterservice.updateStudentDetails(s2)).isEqualTo(s2);
		
	
	}

	@Test
	void testFindAllCourses() {
		Course c= new Course();
		c.setCourseId(1);
		c.setCourseName("python");
		c.setCourseType("mcq");
		c.setDescription("Abcd");
		Course c1=new Course();
		c1.setCourseId(2);
		c1.setCourseName("java");
		c1.setCourseType("Prog");
		c1.setDescription("dcba");
		List<Course> courseList = new ArrayList<>();
		courseList.add(c);
		courseList.add(c1);
		Mockito.when(courserepo.findAll()).thenReturn(courseList);
		assertThat(studentregisterservice.findAllCourses()).isEqualTo(courseList);
		
	}

}
