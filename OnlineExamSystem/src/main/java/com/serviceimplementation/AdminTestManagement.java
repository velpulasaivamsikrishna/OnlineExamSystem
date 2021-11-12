package com.serviceimplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.TestPaper;
import com.entity.TestQuestion;
import com.repository.CourseRepository;
import com.repository.TestPaperRepository;
import com.repository.TestQuestionRepository;
import com.service.AdminTestManagementService;

@Service
public class AdminTestManagement implements AdminTestManagementService{
	
	@Autowired
	private AdminAuthenticationImpl adminAuthenticationImpl;
	
	@Autowired
	CourseRepository courseRepository;
	
	@Autowired
	TestPaperRepository testPaperRepository;
	
	@Autowired
	TestQuestionRepository testQuestionRepository;

	@Override
	public TestPaper addNewTest(TestPaper testPaper, int course_id) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
		Course course = courseRepository.findById(course_id).orElseThrow(()-> new DataNotFoundedException("Cousre Id not found in database"));
		course.addTestpaper(testPaper);
		return testPaperRepository.save(testPaper);
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
		
	}

	@Override
	public boolean removeQuestionById(int id) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
		TestQuestion a = testQuestionRepository.findById(id).orElseThrow(()-> new DataNotFoundedException("Question Id not found in database"));
		if(a != null)
		{
			testQuestionRepository.deleteById(id);
			return true;
		}
		return false;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public TestPaper addQuestionForExistingTestPaper(int testPaper_code, TestQuestion testQuestion) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
		TestPaper testPaper = testPaperRepository.findById(testPaper_code).orElseThrow(()-> new DataNotFoundedException("testPaper code is not found in database"));
		
		testPaper.addTestquestion(testQuestion);
		
		testQuestionRepository.save(testQuestion);
		
		return testPaper;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public String removeTestPaper(int course_id, int testpaper_code) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
		Course course = courseRepository.findById(course_id).orElseThrow(()-> new DataNotFoundedException("course Id not found in database"));
		TestPaper testPaper = testPaperRepository.findById(testpaper_code).orElseThrow();
		
		//course.removeTestpaper(testPaper);
		testPaperRepository.deleteById(testpaper_code);
		return "Deleted Test Paper code = " + testpaper_code;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}
	
}
