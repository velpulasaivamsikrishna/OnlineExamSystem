package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DTO.ExamFormDTO;
import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Exam;
import com.entity.TestQuestion;
import com.serviceimplementation.StudentExam;

@RestController
@RequestMapping("/exam")
public class ExamController 
{
	@Autowired
	StudentExam studentexamservice;
	
	@GetMapping("/start_test/{examRollNo}")
	public ResponseEntity<List<TestQuestion>> startTest(@PathVariable int examRollNo) throws Throwable
	{
		ResponseEntity re =  new ResponseEntity(studentexamservice.startTest(examRollNo), HttpStatus.OK);
		return re;
	}
	
	@PostMapping("/submitTest")
	public ResponseEntity<Exam> submit_test(@Valid @RequestBody List<ExamFormDTO> examFormDto) throws DataNotFoundedException
	{
		ResponseEntity re =  new ResponseEntity(studentexamservice.submitTest(examFormDto), HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/getResult/{examRollNo}")
	public ResponseEntity<Exam> getResult(@PathVariable int examRollNo) throws Throwable
	{
		ResponseEntity re =  new ResponseEntity(studentexamservice.getResultByExamRollNo(examRollNo), HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/getAllResults/{studentid}/{enrollId}")
	public ResponseEntity<List<Exam>> getResults(@PathVariable int studentid, @PathVariable int enrollId) throws Throwable
	{
		ResponseEntity re =  new ResponseEntity(studentexamservice.findAllResults(studentid, enrollId), HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/searchResults/{courseName}/{studentid}")
	public ResponseEntity<List<Exam>> getResultsByCourseName(@PathVariable String courseName, @PathVariable int studentid) throws Throwable
	{
		ResponseEntity re =  new ResponseEntity(studentexamservice.searchResultsByCourseName(courseName, studentid), HttpStatus.OK);
		return re;
	}
	
}
