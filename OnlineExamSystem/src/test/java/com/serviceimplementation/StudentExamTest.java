package com.serviceimplementation;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.DTO.ExamFormDTO;
import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Exam;
import com.entity.Student;
import com.entity.StudentEnrollment;
import com.entity.TestPaper;
import com.entity.TestQuestion;
import com.repository.CourseRepository;
import com.repository.ExamRepository;
import com.repository.StudentEnrollmentRepository;
import com.repository.StudentRepository;
import com.repository.TestPaperRepository;
import com.repository.TestQuestionRepository;
@SpringBootTest
class StudentExamTest {
	
	@Autowired
	StudentExam studentexamservice;
	
	@Autowired
	private AdminAuthenticationImpl adminAuthenticationImpl;
	
	@Autowired
	AdminExamManagement adminExamManagement;
	
	@MockBean
	ExamRepository examRepository;
	
	@MockBean
	StudentEnrollmentRepository studentEnrollmentRepository;
	
	@MockBean
	CourseRepository courseRepository;
	
	@MockBean
	TestQuestionRepository testQuestionRepository;
	
	@MockBean
	StudentRepository studentRepository;
	
	@MockBean
	TestPaperRepository testPaperRepository;
	
	@Test
	void testStartTest() throws Throwable {
		TestPaper tp4=new TestPaper();
		
		Exam e=new Exam();
		e.setExamRollNo(2);
		List<TestQuestion> list=new ArrayList<TestQuestion>();
		TestQuestion tq=new TestQuestion();
		tq.setId(1);
		tq.setQuestion("test");
		tq.setOption1(1);
		tq.setOption2(2);
		tq.setOption3(3);
		tq.setOption4(4);
		tq.setCorrectAnswer(4);
		tq.setQuestionNo(2);
		list.add(tq);
		tp4.addTestquestion(tq);
		e.setTestpaper(tp4);
		
		Mockito.when(examRepository.findById(e.getExamRollNo())).thenReturn(Optional.of(e));
		assertThat(studentexamservice.startTesT(e.getExamRollNo())).isEqualTo(tp4.getTestquestion());
	
	}
	  @Test void testSubmitTest() throws DataNotFoundedException {
		  
		  List<ExamFormDTO> efd = new ArrayList<>();
		  ExamFormDTO e1=new ExamFormDTO();
			e1.setExamRollNo(1);
			e1.setMarked_option(2);
			e1.setTestPaperCode(5);
			e1.setQuestionNo(1);
			
			efd.add(e1);
			
			TestPaper t2 = new TestPaper();
			t2.setTestPaperCode(5);
			t2.setDifficultyLevel(null);
			t2.setDescription("This is test");
			
			TestQuestion tq = new TestQuestion();
			tq.setCorrectAnswer(2);
			tq.setId(1);
			tq.setQuestionNo(1);
			tq.setOption1(1);
			tq.setOption2(2);
			tq.setOption3(3);
			tq.setOption4(4);
			tq.setQuestion("sdfgsvds?");
			
			TestQuestion tq1 = new TestQuestion();
			tq1.setCorrectAnswer(2);
			tq1.setId(1);
			tq1.setQuestionNo(1);
			tq1.setOption1(1);
			tq1.setOption2(2);
			tq1.setOption3(3);
			tq1.setOption4(4);
			tq1.setQuestion("sdfgsvds?");
			
			TestQuestion tq2 = new TestQuestion();
			tq2.setCorrectAnswer(2);
			tq2.setId(1);
			tq2.setQuestionNo(1);
			tq2.setOption1(1);
			tq2.setOption2(2);
			tq2.setOption3(3);
			tq2.setOption4(4);
			tq2.setQuestion("sdfgsvds?");
			
			t2.addTestquestion(tq);
			t2.addTestquestion(tq1);
			t2.addTestquestion(tq2);
			
			Exam e2 = new Exam();
			e2.setExamRollNo(0);
			e2.setDateOfExam(null);
			e2.setStatus(true);
			e2.setMaximumScore(10);
			e2.setAnnouncedToStudent(false);
			e2.setExamDuration(10);
			LocalDateTime ldt = LocalDateTime.of(2021, 12, 10, 16, 0);
			e2.setDateOfExam(ldt);
			e2.setTestpaper(t2);
			
			Mockito.when(examRepository.findById(e1.getExamRollNo())).thenReturn(Optional.of(e2));
			Mockito.when(testPaperRepository.findById(e1.getTestPaperCode())).thenReturn(Optional.of(t2));
			Mockito.when(examRepository.save(e2)).thenReturn(e2);
			
			//System.out.println(studentexamservice.submitTest(efd));
			assertThat(studentexamservice.submitTest(efd)).isEqualTo(e2);
			System.out.println(e2);
	  
	  }
	 
	@Test
	void testGetResultByExamRollNo() throws Throwable {
		Exam e1=new Exam();
		e1.setExamRollNo(1);
		e1.setDateOfExam(null);
		e1.setStatus(false);
		e1.setMaximumScore(0);
		e1.setActualScore(0);
		e1.setAnnouncedToStudent(false);
		Optional<Exam> e2=Optional.of(e1);
		Mockito.when(examRepository.findById(e1.getExamRollNo())).thenReturn(e2);
		//Mockito.when(examRepository.save(e1)).thenReturn(e1);
		assertThat(studentexamservice.getResultByExamRollNo(e1.getExamRollNo())).isEqualTo(e1);
	
	}

	@Test
	void testFindAllResults() throws Throwable {
		Student s1=new Student();
		s1.setStudentId(1);
		s1.setUsername("renu");
		s1.setPassword("hem@3458");
		s1.setFirstName("Hema");
		s1.setLastName("Sree");
		s1.setGender("female");
		s1.setEmail("hema@gmail.com");
		s1.setMobileNo("9080706050");
		
		
		List<Exam> list=new ArrayList<Exam>();
		Exam e2=new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		list.add(e2);
		
		StudentEnrollment se=new StudentEnrollment();
		se.setEnrollmentId(2);
		se.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se.setEnrollmentDate(date);
		se.setStatus(false);
		se.addExam(e2);
		s1.addStudentEnrollment(se);
				
		Mockito.when(studentRepository.findById(s1.getStudentId())).thenReturn(Optional.of(s1));
		Mockito.when(studentEnrollmentRepository.findById(se.getEnrollmentId())).thenReturn(Optional.of(se));
		
		adminExamManagement.releaseTestResultForStudent = true;
		
		adminAuthenticationImpl.isLogin = true;
		
		assertThat(studentexamservice.findAllResults(s1.getStudentId(),se.getEnrollmentId())).isEqualTo(list);
	}

	@Test
	void testSearchResultsByCourseName() throws Throwable {
		Course c=new Course();
		c.setCourseId(1);
		c.setCourseName("Java");
		c.setCourseType("mcq");
		c.setDescription("qwer");
		
		List<StudentEnrollment> list1=new ArrayList<>();
		StudentEnrollment se1=new StudentEnrollment();
		se1.setEnrollmentId(2);
		se1.setBatchName("Javva");
		LocalDate date = LocalDate.now();
		se1.setEnrollmentDate(date);
		se1.setStatus(false);
		list1.add(se1);
		se1.setCourse(c);
		
		List<Exam> list=new ArrayList<Exam>();
		Exam e2=new Exam();
		e2.setExamRollNo(1);
		e2.setDateOfExam(null);
		e2.setStatus(false);
		e2.setMaximumScore(0);
		e2.setActualScore(0);
		e2.setAnnouncedToStudent(false);
		list.add(e2);
		se1.addExam(e2);
		
		Mockito.when(courseRepository.findByCourseName(c.getCourseName())).thenReturn(Optional.of(c));
		Mockito.when(studentEnrollmentRepository.findById(se1.getEnrollmentId())).thenReturn(Optional.of(se1));
		Mockito.when(studentEnrollmentRepository.findAllBycourseId(se1.getCourse().getCourseId())).thenReturn(Optional.of(list1));
		assertThat(studentexamservice.searchResultsByCourseName(c.getCourseName(),se1.getEnrollmentId())).isEqualTo(se1.getExam());
		
	}

}
