package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Exam;
import com.entity.StudentEnrollment;

public interface ExamRepository extends JpaRepository<Exam, Integer>
{
	
}
