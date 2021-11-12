package com.service;

import com.advices.DataNotFoundedException;
import com.entity.TestPaper;
import com.entity.TestQuestion;

public interface AdminTestManagementService {
	TestPaper addNewTest(TestPaper testPaper, int course_id) throws DataNotFoundedException, Exception;
	boolean removeQuestionById(int id) throws DataNotFoundedException, Exception; 
	TestPaper addQuestionForExistingTestPaper(int testPaper_code, TestQuestion testQuestion) throws DataNotFoundedException, Exception;
	String removeTestPaper(int course_id, int testpaper_code) throws DataNotFoundedException, Exception;
}
