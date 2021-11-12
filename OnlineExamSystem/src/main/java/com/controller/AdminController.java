package com.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advices.DataNotFoundedException;
import com.entity.Admin;
import com.entity.Course;
import com.entity.Exam;
import com.entity.Student;
import com.entity.TestPaper;
import com.entity.TestQuestion;
import com.serviceimplementation.AdminAuthenticationImpl;
//import com.serviceimplementation.AdminAuthenticationImpl;
import com.serviceimplementation.AdminExamManagement;
import com.serviceimplementation.AdminStudentManagement;
import com.serviceimplementation.AdminTestManagement;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminStudentManagement adminStudentManagementService;
	
	@Autowired
	private AdminExamManagement adminExamManagementService;
	
	@Autowired
	AdminTestManagement admintestmanagementservice;
	
	@Autowired
	AdminAuthenticationImpl adminAuthenticationService;
	
	@PostMapping("/login")
	public ResponseEntity<String> adminLogin(@Valid @RequestBody Admin admin) throws DataNotFoundedException
	{
		return new ResponseEntity<String>(adminAuthenticationService.adminLogin(admin), HttpStatus.OK);
	}
	
	@PostMapping("/enrollstudent/{student_id}/{course_id}/{batch_Name}")
	public ResponseEntity<Student> enrollStudentCourse(@PathVariable int student_id, @PathVariable int course_id,
			@PathVariable String batch_Name) throws Throwable {
		ResponseEntity<Student> re = new ResponseEntity<Student>(
				adminStudentManagementService.enrollStudent(student_id, course_id, batch_Name), HttpStatus.OK);
		return re;
	}

	@PostMapping("/addNewCourse")
	public ResponseEntity<Course> addnewCourse(@Valid @RequestBody Course course) throws Exception {
		ResponseEntity<Course> re = new ResponseEntity<Course>(adminStudentManagementService.addNewCourse(course),
				HttpStatus.OK);
		return re;
	}


	@PostMapping(path = "/addNewTest/{course_id}")
	public ResponseEntity<TestPaper> addNewTest(@Valid @RequestBody TestPaper testPaper, @PathVariable int course_id) throws Throwable {
		TestPaper t = admintestmanagementservice.addNewTest(testPaper, course_id);
		ResponseEntity re = new ResponseEntity<TestPaper>(t, HttpStatus.OK);
		return re;
	}

	@DeleteMapping(path = "/deleteQuestion/{id}")
	public ResponseEntity<Boolean> removeQuestionById(@PathVariable int id) throws Throwable {

		boolean b = admintestmanagementservice.removeQuestionById(id);

		ResponseEntity re = new ResponseEntity<Boolean>(b, HttpStatus.OK);
		return re;

	}

	@PostMapping(path = "/addQuestionForTestPaper/{testPaper_code}")
	public ResponseEntity<TestPaper> addQuestionForTestPaper(@Valid @PathVariable int testPaper_code,
			@RequestBody TestQuestion testQuestion) throws Throwable {

		TestPaper t = admintestmanagementservice.addQuestionForExistingTestPaper(testPaper_code, testQuestion);

		ResponseEntity re = new ResponseEntity<TestPaper>(t, HttpStatus.OK);

		return re;
	}

	@DeleteMapping(path = "/deleteTestPaper/{course_id}/{testpaper_code}")
	public ResponseEntity<String> removeTestPaper(@PathVariable int course_id, @PathVariable int testpaper_code) throws Throwable {

		String i = admintestmanagementservice.removeTestPaper(course_id, testpaper_code);

		ResponseEntity re = new ResponseEntity<String>(i, HttpStatus.OK);
		return re;
	}

	@GetMapping(path = "scheduleExamForStudent/{sid}/{eid}/{tpc}/{ldt}/{examduration}")
	public ResponseEntity<Exam> scheduleExamforStudent(@PathVariable int sid, @PathVariable int eid,
			@PathVariable int tpc, @PathVariable("ldt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
			@PathVariable int examduration) throws Throwable 
	{
		ResponseEntity<Exam> re = new ResponseEntity<Exam>(adminExamManagementService.scheduleExamForStudent(sid, eid, tpc, date, examduration), HttpStatus.OK);
		return re;

	}
	
	@PutMapping("/scheduleExamForBatch/{batchName}/{testPaperCode}/{localDateTime}/{examDurationInMinutes}")
	public ResponseEntity<Exam> scheduleExamForBatch(@PathVariable String batchName, @PathVariable int testPaperCode, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime,
			@PathVariable int examDurationInMinutes) throws Throwable
	{
		ResponseEntity re=new ResponseEntity(adminExamManagementService.scheduleExamForBatch(batchName, testPaperCode, localDateTime, examDurationInMinutes),HttpStatus.OK);
		return re;
	}
	
	@PutMapping("/updateTestPaperForStudent/{studentId}/{enrollmentId}/{testPaperCode}/{examrollno}")
	public ResponseEntity<Exam> updateTestpaperForStudent(@PathVariable int studentId, @PathVariable int enrollmentId, @PathVariable int testPaperCode, @PathVariable int examrollno) throws Throwable
	{
		ResponseEntity re=new ResponseEntity(adminExamManagementService.changeTestPaperForStudent(studentId, enrollmentId, testPaperCode, examrollno),HttpStatus.OK);
		return re;
	}
	
	@PutMapping("/updateTestPaperForBatch/{enrollmentId}/{testPaperCode}")
	public ResponseEntity<Exam> updateTestpaperForBatch(@PathVariable int enrollmentId, @PathVariable int testPaperCode) throws Throwable
	{
		ResponseEntity re=new ResponseEntity(adminExamManagementService.changeTestPaperForBatch(enrollmentId, testPaperCode),HttpStatus.OK);
		return re;
	}
	
	@PutMapping("/releaseAllTestResultForStudent/{studentId}/{enrollId}")
	public ResponseEntity<Boolean> releaseAllTestResultofStudent(int studentId, int enrollId) throws Exception
	{
		ResponseEntity re=new ResponseEntity(adminExamManagementService.releaseAllTestResultForStudent(studentId, enrollId), HttpStatus.OK);
		return re;
	}
	
	@GetMapping("/findAllResultsByBatchName/{batchName}/{enrollId}")
	public ResponseEntity<List<Exam>> findResultsByBatchName(String batchName, int enrollId) throws Exception
	{
		return new ResponseEntity(adminExamManagementService.findAllResultsByBatchName(batchName, enrollId), HttpStatus.OK);
	}
	
	@GetMapping("/findResultByEnrollmentId/{batchName}/{enrollmentId}")
	public ResponseEntity<List<Exam>> findResultByEnrollmentId(int enrollmentId) throws Exception
	{
		return new ResponseEntity(adminExamManagementService.findResultByEnrollmentId(enrollmentId), HttpStatus.OK);
	}
}