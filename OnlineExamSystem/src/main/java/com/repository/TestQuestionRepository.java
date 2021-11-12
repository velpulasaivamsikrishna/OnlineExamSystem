package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.TestQuestion;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Integer>{

}
