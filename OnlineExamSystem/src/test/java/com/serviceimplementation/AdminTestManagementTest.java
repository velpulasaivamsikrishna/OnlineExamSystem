
//DONE
package com.serviceimplementation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.entity.Course;
import com.entity.TestPaper;
import com.entity.TestQuestion;
import com.repository.CourseRepository;
import com.repository.TestPaperRepository;
import com.repository.TestQuestionRepository;
import com.service.AdminTestManagementService;
@SpringBootTest
class AdminTestManagementTest {
	
	@Autowired
	private AdminAuthenticationImpl adminAuthenticationImpl;
	
	@Autowired
	AdminTestManagement admintestmanagementservice;
	
	@MockBean
	TestPaperRepository testPaperrepo;
	
	@MockBean
	TestQuestionRepository testQuesrepo;
	
	@MockBean
	CourseRepository courserepo;
	

	@Test
	void testAddNewTest() throws Throwable {
		Course c= new Course();
		c.setCourseId(3);
		Optional<Course> c1=Optional.of(c);
		Mockito.when(courserepo.findById(c.getCourseId())).thenReturn(c1);
		
		TestPaper t4= new TestPaper();
		t4.setTestPaperCode(0);
		t4.setDifficultyLevel("high");
		t4.setDescription("abc");
		
		c.addTestpaper(t4);
		
		adminAuthenticationImpl.isLogin = true;
		
		Mockito.when(testPaperrepo.save(t4)).thenReturn(t4);
		assertThat(admintestmanagementservice.addNewTest(t4, c.getCourseId())).isEqualTo(t4);
		
	}

	@Test
	void testRemoveQuestionById() {
		TestQuestion t1= new TestQuestion();
		t1.setId(1);
		t1.setQuestion("test");
		t1.setOption1(1);
		t1.setOption2(2);
		t1.setOption3(3);
		t1.setOption4(4);
		t1.setCorrectAnswer(4);
		t1.setQuestionNo(2);
		Optional<TestQuestion> tq=Optional.of(t1);
		Mockito.when(testQuesrepo.findById(1)).thenReturn(tq);
		Mockito.when(testQuesrepo.existsById(t1.getId())).thenReturn(false);
		assertFalse(testQuesrepo.existsById(t1.getId()));
		
	}
	
	

	@Test
	void testAddQuestionForExistingTestPaper() throws Throwable {
TestPaper tp=new TestPaper();
		
		tp.setTestPaperCode(1);
		
		TestQuestion t3= new TestQuestion();
		t3.setId(1);
		t3.setQuestion("what is this?");
		t3.setOption1(1);
		t3.setOption2(2);
		t3.setOption3(3);
		t3.setOption4(4);
		t3.setCorrectAnswer(3);
		t3.setQuestionNo(2);
		tp.addTestquestion(t3);
		Optional<TestPaper> tp1=Optional.of(tp);
		Mockito.when(testPaperrepo.findById(1)).thenReturn(tp1);
		Mockito.when(testQuesrepo.save(t3)).thenReturn(t3);
		Mockito.when(testPaperrepo.save(tp)).thenReturn(tp);
		
		adminAuthenticationImpl.isLogin = true;
		
		assertThat(admintestmanagementservice.addQuestionForExistingTestPaper(tp.getTestPaperCode(), t3)).isEqualTo(tp);
		
	}

	@Test
	void testRemoveTestPaper() {
		TestPaper t2= new TestPaper();
		t2.setTestPaperCode(1);
		t2.setDifficultyLevel(null);
		t2.setDescription("This is test");
		Optional<TestPaper> tp=Optional.of(t2);
		Mockito.when(testPaperrepo.findById(1)).thenReturn(tp);
		Mockito.when(testPaperrepo.existsById(t2.getTestPaperCode())).thenReturn(false);
		assertFalse(testPaperrepo.existsById(t2.getTestPaperCode()));
	}

}

