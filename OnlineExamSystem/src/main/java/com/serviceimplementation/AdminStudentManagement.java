package com.serviceimplementation;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Student;
import com.entity.StudentEnrollment;
import com.repository.CourseRepository;
import com.repository.StudentEnrollmentRepository;
import com.repository.StudentRepository;
import com.service.AdminStudentManagementService;

@Service
public class AdminStudentManagement implements AdminStudentManagementService {

	@Autowired
	private AdminAuthenticationImpl adminAuthenticationImpl;

	@Autowired
	StudentEnrollmentRepository studentEnrollmentRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	CourseRepository courseRepository;

	@Override
	public Student enrollStudent(int student_id, int course_id, String batch_Name) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			StudentEnrollment studentEnrollment = new StudentEnrollment();

			Student s1 = studentRepository.findById(student_id)
					.orElseThrow(() -> new DataNotFoundedException("student Id not found in database"));
			Course c1 = courseRepository.findById(course_id)
					.orElseThrow(() -> new DataNotFoundedException("course Id not found in database"));

			studentEnrollment.setBatchName(batch_Name);
//		long millis = System.currentTimeMillis();
//		Date date = new Date(millis);
			LocalDate date = LocalDate.now();
			studentEnrollment.setEnrollmentDate(date);
			studentEnrollment.setCompletionDate(null);
			studentEnrollment.setCourse(c1);
			studentEnrollment.setStatus(false);

			s1.addStudentEnrollment(studentEnrollment);
			studentEnrollmentRepository.save(studentEnrollment);
			return s1;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public Course addNewCourse(Course c) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			return courseRepository.save(c);
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}

	}

	@Override
	public Student deEnRollstudent(int student_id, int course_id, String batch_Name, int enroll_id) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			Student s1 = studentRepository.findById(student_id)
					.orElseThrow(() -> new DataNotFoundedException("student Id not found in database"));
			Course c1 = courseRepository.findById(course_id).orElseThrow();

			StudentEnrollment studentEnrollment = studentEnrollmentRepository.findById(enroll_id)
					.orElseThrow(() -> new DataNotFoundedException("course Id not found in database"));
			if (studentEnrollment.getBatchName().equals(batch_Name)) {
				s1.removeStudentEnrollment(studentEnrollment);
			}
			studentEnrollmentRepository.deleteById(enroll_id);
			return studentRepository.save(s1);
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

}
