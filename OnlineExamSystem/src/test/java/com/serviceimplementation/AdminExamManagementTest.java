package com.serviceimplementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.advices.DataNotFoundedException;
import com.entity.Exam;
import com.entity.Student;
import com.entity.StudentEnrollment;
import com.entity.TestPaper;
import com.repository.ExamRepository;
import com.repository.StudentEnrollmentRepository;
import com.repository.StudentRepository;
import com.repository.TestPaperRepository;

@SpringBootTest
class AdminExamManagementTest {

	@Autowired
	AdminAuthenticationImpl adminAuthenticationImpl;

	@Autowired
	AdminExamManagement adminexammanagementservice;

	@MockBean
	StudentEnrollmentRepository studentEnrollmentRepository;

	@MockBean
	ExamRepository examRepository;

	@MockBean
	TestPaperRepository testPaperRepository;

	@MockBean
	StudentRepository studentRepository;

	@Test
	void testScheduleExamForStudent() throws Exception {
		Student s1 = new Student();
		s1.setStudentId(1);
		s1.setUsername("renu");
		s1.setPassword("hem@3458");
		s1.setFirstName("Hema");
		s1.setLastName("Sree");
		s1.setGender("female");
		s1.setEmail("hema@gmail.com");
		s1.setMobileNo("9080706050");

		TestPaper t2 = new TestPaper();
		t2.setTestPaperCode(5);
		t2.setDifficultyLevel(null);
		t2.setDescription("This is test");

		StudentEnrollment se1 = new StudentEnrollment();
		se1.setEnrollmentId(2);
		se1.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se1.setEnrollmentDate(date);
		se1.setStatus(false);

		Exam e2 = new Exam();
		e2.setExamRollNo(0);
		e2.setDateOfExam(null);
		e2.setStatus(true);
		e2.setMaximumScore(10);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		e2.setExamDuration(10);
		LocalDateTime ldt = LocalDateTime.of(2021, 12, 10, 16, 0);
		e2.setDateOfExam(ldt);

		e2.setTestpaper(t2);
		se1.addExam(e2);
		s1.addStudentEnrollment(se1);

		Optional<TestPaper> tpop = Optional.of(t2);
		Optional<Exam> eop = Optional.of(e2);
		Optional<StudentEnrollment> seop = Optional.of(se1);
		Optional<Student> sop = Optional.of(s1);

		Mockito.when(testPaperRepository.save(t2)).thenReturn(t2);
		Mockito.when(examRepository.save(e2)).thenReturn(e2);
		Mockito.when(studentEnrollmentRepository.save(se1)).thenReturn(se1);
		Mockito.when(studentRepository.save(s1)).thenReturn(s1);

		Mockito.when(studentRepository.findById(s1.getStudentId())).thenReturn(sop);
		Mockito.when(studentEnrollmentRepository.findById(se1.getEnrollmentId())).thenReturn(seop);
		Mockito.when(testPaperRepository.findById(t2.getTestPaperCode())).thenReturn(tpop);
		Mockito.when(examRepository.findById(e2.getExamRollNo())).thenReturn(eop);

		adminAuthenticationImpl.isLogin = true;

		Exam e3 = adminexammanagementservice.scheduleExamForStudent(s1.getStudentId(), se1.getEnrollmentId(),
				t2.getTestPaperCode(), ldt, e2.getExamDuration());
		assertTrue(e3.getActualScore() == e2.getActualScore());
		assertTrue(e3.getDateOfExam() == e2.getDateOfExam());
		assertTrue(e3.getExamDuration() == e2.getExamDuration());
		assertTrue(e3.getTestpaper().equals(e2.getTestpaper()));
		assertTrue(e3.getMaximumScore() == e2.getMaximumScore());
		assertTrue(e3.getExamRollNo() == e2.getExamRollNo());
	}

	@Test
	void testScheduleExamForBatch() throws Exception {
		Student s1 = new Student();
		s1.setStudentId(1);
		s1.setUsername("renu");
		s1.setPassword("hem@3458");
		s1.setFirstName("Hema");
		s1.setLastName("Sree");
		s1.setGender("female");
		s1.setEmail("hema@gmail.com");
		s1.setMobileNo("9080706050");

		Student s2 = new Student();
		s2.setStudentId(2);
		s2.setUsername("raj");
		s2.setPassword("raj@101");
		s2.setFirstName("Raj");
		s2.setLastName("Kumar");
		s2.setGender("male");
		s2.setEmail("R.kumar@gmail.com");
		s2.setMobileNo("8392386755");

		TestPaper t2 = new TestPaper();
		t2.setTestPaperCode(5);
		t2.setDifficultyLevel(null);
		t2.setDescription("This is test");

		StudentEnrollment se1 = new StudentEnrollment();
		se1.setEnrollmentId(2);
		se1.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se1.setEnrollmentDate(date);
		se1.setStatus(false);

		StudentEnrollment se2 = new StudentEnrollment();
		se2.setEnrollmentId(3);
		se2.setBatchName("Python");
		LocalDate date1 = LocalDate.now();
		se2.setEnrollmentDate(date1);
		se2.setStatus(false);

		List<StudentEnrollment> sel = new ArrayList<>();

		Exam e2 = new Exam();
		e2.setExamRollNo(0);
		e2.setStatus(true);
		e2.setMaximumScore(10);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		e2.setExamDuration(10);
		LocalDateTime ldt = LocalDateTime.of(2021, 12, 10, 16, 0);
		e2.setDateOfExam(ldt);
		e2.setTestpaper(t2);

		se1.addExam(e2);
		se2.addExam(e2);
		sel.add(se1);
		s1.addStudentEnrollment(se1);
		s1.addStudentEnrollment(se2);

		Optional<TestPaper> tpop = Optional.of(t2);
		Optional<List<StudentEnrollment>> seopl = Optional.of(sel);
		Optional<Student> sop = Optional.of(s2);

		Mockito.when(examRepository.save(e2)).thenReturn(e2);
		Mockito.when(studentEnrollmentRepository.save(se1)).thenReturn(se1);
		Mockito.when(testPaperRepository.findById(t2.getTestPaperCode())).thenReturn(tpop);
		Mockito.when(studentEnrollmentRepository.findBybatchName(se1.getBatchName())).thenReturn(seopl);

		adminAuthenticationImpl.isLogin = true;

		Exam e3 = adminexammanagementservice.scheduleExamForBatch(se1.getBatchName(), t2.getTestPaperCode(), ldt, 10);

		assertTrue(e3.getActualScore() == e2.getActualScore());
		assertTrue(e3.getDateOfExam() == e2.getDateOfExam());
		assertTrue(e3.getExamDuration() == e2.getExamDuration());
		assertTrue(e3.getTestpaper().equals(e2.getTestpaper()));
		assertTrue(e3.getMaximumScore() == e2.getMaximumScore());
		assertTrue(e3.getExamRollNo() == e2.getExamRollNo());
	}

	@Test
	@Transactional
	void testChangeTestPaperForStudent() throws Throwable {
		Student s1 = new Student();
		s1.setStudentId(1);
		s1.setUsername("renu");
		s1.setPassword("hem@3458");
		s1.setFirstName("Hema");
		s1.setLastName("Sree");
		s1.setGender("female");
		s1.setEmail("hema@gmail.com");
		s1.setMobileNo("9080706050");

		TestPaper t2 = new TestPaper();
		t2.setTestPaperCode(5);
		t2.setDifficultyLevel(null);
		t2.setDescription("This is test");

		StudentEnrollment se1 = new StudentEnrollment();
		se1.setEnrollmentId(2);
		se1.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se1.setEnrollmentDate(date);
		se1.setStatus(false);

		Exam e2 = new Exam();
		e2.setExamRollNo(0);
		e2.setDateOfExam(null);
		e2.setStatus(true);
		e2.setMaximumScore(10);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		e2.setExamDuration(10);
		LocalDateTime ldt = LocalDateTime.of(2021, 12, 10, 16, 0);
		e2.setDateOfExam(ldt);

		e2.setTestpaper(t2);
		se1.addExam(e2);
		s1.addStudentEnrollment(se1);

		Optional<TestPaper> tpop = Optional.of(t2);
		Optional<Exam> eop = Optional.of(e2);
		Optional<StudentEnrollment> seop = Optional.of(se1);
		Optional<Student> sop = Optional.of(s1);

		Mockito.when(testPaperRepository.save(t2)).thenReturn(t2);
		Mockito.when(examRepository.save(e2)).thenReturn(e2);
		Mockito.when(studentEnrollmentRepository.save(se1)).thenReturn(se1);
		Mockito.when(studentRepository.save(s1)).thenReturn(s1);

		Mockito.when(studentRepository.findById(s1.getStudentId())).thenReturn(sop);
		Mockito.when(studentEnrollmentRepository.findById(se1.getEnrollmentId())).thenReturn(seop);
		Mockito.when(testPaperRepository.findById(t2.getTestPaperCode())).thenReturn(tpop);
		Mockito.when(examRepository.findById(e2.getExamRollNo())).thenReturn(eop);
		
		adminAuthenticationImpl.isLogin = true;
		
		assertEquals(adminexammanagementservice.changeTestPaperForStudent(s1.getStudentId(), se1.getEnrollmentId(),
				t2.getTestPaperCode(), e2.getExamRollNo()), e2);
	}

	@Test
	void testChangeTestPaperForBatch() throws Throwable {
		TestPaper t2 = new TestPaper();
		t2.setTestPaperCode(1);
		t2.setDifficultyLevel(null);
		t2.setDescription("This is test");
		Optional<TestPaper> tp = Optional.of(t2);

		List<StudentEnrollment> list1 = new ArrayList<>();
		StudentEnrollment se1 = new StudentEnrollment();
		se1.setEnrollmentId(2);
		se1.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se1.setEnrollmentDate(date);
		se1.setStatus(false);
		list1.add(se1);

		List<Exam> list = new ArrayList<Exam>();
		Exam e2 = new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		list.add(e2);
		se1.addExam(e2);

		Mockito.when(testPaperRepository.findById(t2.getTestPaperCode())).thenReturn(tp);
		Mockito.when(studentEnrollmentRepository.findById(se1.getEnrollmentId())).thenReturn(Optional.of(se1));
		Mockito.when(studentEnrollmentRepository.findBybatchName(se1.getBatchName())).thenReturn(Optional.of(list1));
		e2.setTestpaper(t2);
		Mockito.when(examRepository.save(e2)).thenReturn(e2);
		assertThat(adminexammanagementservice.changeTestPaperForBatch(se1.getEnrollmentId(), t2.getTestPaperCode()))
				.isEqualTo(e2);

	}

	@Test
	void testFindAllResultsByBatchName() throws Throwable {
		List<StudentEnrollment> list = new ArrayList<StudentEnrollment>();
		StudentEnrollment se = new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);
		list.add(se);

		Exam e2 = new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		se.addExam(e2);

		Mockito.when(studentEnrollmentRepository.findBybatchName(se.getBatchName())).thenReturn(Optional.of(list));
		
		adminAuthenticationImpl.isLogin = true;
		
		assertThat(adminexammanagementservice.findAllResultsByBatchName(se.getBatchName(), se.getEnrollmentId()))
				.isEqualTo(se.getExam());
	}

	@Test
	void testFindResultByEnrollmentId() throws Throwable {

		StudentEnrollment se = new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);

		Exam e2 = new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		se.addExam(e2);
		Mockito.when(studentEnrollmentRepository.findById(se.getEnrollmentId())).thenReturn(Optional.of(se));
		
		adminAuthenticationImpl.isLogin = true;
		
		assertThat(adminexammanagementservice.findResultByEnrollmentId(se.getEnrollmentId())).isEqualTo(se.getExam());

	}

	@Test
	void testFindAllResultsByStudId() throws Throwable {
		Student s1 = new Student();
		s1.setStudentId(1);
		s1.setUsername("renu");
		s1.setPassword("hem@3458");
		s1.setFirstName("Hema");
		s1.setLastName("Sree");
		s1.setGender("female");
		s1.setEmail("hema@gmail.com");
		s1.setMobileNo("9080706050");

		Exam e2 = new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);

		StudentEnrollment se = new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);
		se.addExam(e2);
		s1.addStudentEnrollment(se);
		Mockito.when(studentRepository.save(s1)).thenReturn(s1);
		Mockito.when(studentRepository.findById(s1.getStudentId())).thenReturn(Optional.of(s1));
		Mockito.when(studentEnrollmentRepository.findById(se.getEnrollmentId())).thenReturn(Optional.of(se));
		assertThat(adminexammanagementservice.findAllResultsByStudId(s1.getStudentId(), se.getEnrollmentId()))
				.isEqualTo(se.getExam());

	}

	@Test
	void testReleaseTestResultByEnrollmentId() throws Throwable {

		StudentEnrollment se = new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);

		Exam e2 = new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);

		se.addExam(e2);

		Mockito.when(studentEnrollmentRepository.findById(se.getEnrollmentId())).thenReturn(Optional.of(se));
		
		adminAuthenticationImpl.isLogin = true;
		
		assertThat(adminexammanagementservice.releaseTestResultByEnrollmentId(se.getEnrollmentId())).isEqualTo(true);

	}

	@Test
	// @BeforeTestClass
	void testReleaseAllTestResultForStudent() throws Throwable {
		Student s1 = new Student();
		s1.setStudentId(1);
		s1.setUsername("renu");
		s1.setPassword("hem@3458");
		s1.setFirstName("Hema");
		s1.setLastName("Sree");
		s1.setGender("female");
		s1.setEmail("hema@gmail.com");
		s1.setMobileNo("9080706050");

		Exam e2 = new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);

		StudentEnrollment se = new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);
		se.addExam(e2);

//		Mockito.when(studentRepository.findById(s1.getStudentId())).thenReturn(Optional.of(s1));
//		Mockito.when(studentEnrollmentRepository.findById(se.getEnrollmentId())).thenReturn(Optional.of(se));
		assertThat(adminexammanagementservice.releaseAllTestResultForStudent(s1.getStudentId(), se.getEnrollmentId()))
				.isEqualTo(true);

	}

	@Test
	void testReleaseAllTestResultForBatch() {

	}

}
