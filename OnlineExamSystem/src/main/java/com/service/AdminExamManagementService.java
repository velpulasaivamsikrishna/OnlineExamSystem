package com.service;

import java.time.LocalDateTime;
import java.util.List;

import com.advices.DataNotFoundedException;
import com.entity.Exam;

public interface AdminExamManagementService {
	Exam scheduleExamForStudent(int student_id, int enrollmentId, int testPaperCode, LocalDateTime locDateTime,
			int examDurationInMinutes) throws DataNotFoundedException, Exception;
	Exam scheduleExamForBatch(String batchName, int testPaperCode, LocalDateTime localDateTime, int examDurationInMinutes) throws DataNotFoundedException, Exception;

	//Exam changeTestPaperForStudent(int enrollmentId, int testPaperCode);

	Exam changeTestPaperForBatch(int enrollmentId, int testPaperCode) throws DataNotFoundedException, Exception;

	List<Exam> findResultByEnrollmentId(int enrollmentId) throws DataNotFoundedException, Exception;

	boolean releaseTestResultByEnrollmentId(int enrollmentId) throws Exception;
	Exam changeTestPaperForStudent(int studentId, int enrollmentId, int testPaperCode, int examrollno) throws DataNotFoundedException, Exception;
	List<Exam> findAllResultsByBatchName(String batchName, int enrollId) throws DataNotFoundedException, Exception;
	List<Exam> findAllResultsByStudId(int studentId, int enrollId) throws DataNotFoundedException, Exception;
	boolean releaseAllTestResultForBatch(String batchName, int enrollId) throws Exception;
	boolean releaseAllTestResultForStudent(int studentId, int enrollId) throws Exception;

	
}
