package com.service;

import java.util.List;

import com.DTO.ExamFormDTO;
import com.DTO.TestQuestionDTO;
import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Exam;
import com.entity.TestQuestion;

public interface StudentExamService {
		List<TestQuestionDTO> startTest(int examRollNo) throws DataNotFoundedException; 
		Exam submitTest(List<ExamFormDTO> examFormDto) throws DataNotFoundedException; 
		Exam getResultByExamRollNo(int examRollNo) throws DataNotFoundedException; 
		//List<Exam> findAllResults (int studId); 
		List<Exam> findAllResults(int studId, int enrollId) throws DataNotFoundedException, Exception;
		List<Exam> searchResultsByCourseName(String courseName, int enrollId) throws DataNotFoundedException;

}
