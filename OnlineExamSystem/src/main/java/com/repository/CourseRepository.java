package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>
{
	@Query("SELECT cn FROM Course cn WHERE cn.CourseName = :courseName")
	Optional<Course> findByCourseName(@Param("courseName") String courseName);
	
	//Optional<Course> findBycoursename(String courseName);
}