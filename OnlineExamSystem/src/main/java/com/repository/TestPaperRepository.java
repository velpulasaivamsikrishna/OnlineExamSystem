package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.TestPaper;

public interface TestPaperRepository extends JpaRepository<TestPaper, Integer>{

}
