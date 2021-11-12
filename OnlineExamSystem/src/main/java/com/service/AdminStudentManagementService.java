package com.service;

import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Student;
public interface AdminStudentManagementService {
	 
		Student enrollStudent (int student_id, int course_id, String batch_Name) throws DataNotFoundedException, Exception;
		//Student enrollStudent (StudentCourse studentCourse);
		Course addNewCourse(Course c) throws Exception; 
		Student deEnRollstudent(int student_id, int course_id, String batch_Name, int enroll_id) throws DataNotFoundedException, Exception;
}
