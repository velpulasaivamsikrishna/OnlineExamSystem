package com.serviceimplementation;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.ExamFormDTO;
import com.DTO.TestQuestionDTO;
import com.advices.DataNotFoundedException;
import com.entity.Course;
import com.entity.Exam;
import com.entity.StudentEnrollment;
import com.entity.TestPaper;
import com.entity.TestQuestion;
import com.repository.CourseRepository;
import com.repository.ExamRepository;
import com.repository.StudentEnrollmentRepository;
import com.repository.StudentRepository;
import com.repository.TestPaperRepository;
import com.service.StudentExamService;

@Service
public class StudentExam implements StudentExamService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private StudentEnrollmentRepository studentEnrollmentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private TestPaperRepository testPaperRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AdminExamManagement adminExamManagement;

	@Override
	public List<TestQuestionDTO> startTest(int examRollNo) throws DataNotFoundedException {
		Exam e = examRepository.findById(examRollNo).orElseThrow(()-> new DataNotFoundedException("Exam Roll number is not found in database"));
//		TimerTask timerTask = new TimerTask() {
//	        @Override
//	        public void run() {
//	            System.out.println("task  run:"+ new Date());
//	        }
//
//	    };
//
//	    Timer timer = new Timer();
//
//	    timer.schedule(timerTask,10,1000);
		e.setAnnouncedToStudent(true);
		examRepository.save(e);
		
		List<TestQuestion> tql = e.getTestpaper().getTestquestion();
		List<TestQuestionDTO> tqdl = new ArrayList<>();
		for(TestQuestion tq : tql)
		{
			TestQuestionDTO tqd = new TestQuestionDTO();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			tqd = mapper.map(tq, TestQuestionDTO.class);
			tqdl.add(tqd);
		}
		return tqdl;
	}
	
	public List<TestQuestion> startTesT(int examRollNo) throws DataNotFoundedException {
		Exam e = examRepository.findById(examRollNo).orElseThrow(()-> new DataNotFoundedException("Exam Roll number is not found in database"));
		e.setAnnouncedToStudent(true);
		examRepository.save(e);
		
		return e.getTestpaper().getTestquestion();
	}

	@Override
	public Exam submitTest(List<ExamFormDTO> examFormDto) throws DataNotFoundedException {
		
		Exam e = null;
		int score = 0;
		TestPaper tp = null;
		for(ExamFormDTO dto : examFormDto)
		{
			e = examRepository.findById(dto.getExamRollNo()).orElseThrow(()-> new DataNotFoundedException("Exam Roll number is not found"));
			tp = testPaperRepository.findById(dto.getTestPaperCode()) .orElseThrow(()-> new DataNotFoundedException("Test Paper Code is not found"));
			break;
		}
		for(ExamFormDTO dto : examFormDto)
		{
			for(TestQuestion tq : tp.getTestquestion())
			{
				
				if((dto.getMarked_option() == tq.getCorrectAnswer()) && (dto.getQuestionNo() == tq.getQuestionNo()))
				{
					e.setActualScore(++score);
				}
			}
		}
		
		List<StudentEnrollment> se_list = studentEnrollmentRepository.findAll();
		for(StudentEnrollment se : se_list)
		{
			for(Exam ex : se.getExam())
			{
				if(ex.getExamRollNo() == e.getExamRollNo())
				{
					Instant instant = Instant.now();
					se.setCompletionDate(Date.from(instant));
					se.setStatus(true);
					studentEnrollmentRepository.save(se);
					break;
				}
			}
		}
		Exam e1 = examRepository.save(e);
		return e1;
	}

	@Override
	public Exam getResultByExamRollNo(int examRollNo) throws DataNotFoundedException {
		Exam exam = examRepository.findById(examRollNo).orElseThrow(()-> new DataNotFoundedException("Exam roll number not found in database")); // need to write exception
		return exam;
	}

	@Override
	public List<Exam> findAllResults(int studId, int enrollId) throws Exception {
		if(adminExamManagement.releaseTestResultForStudent)
		{
			return adminExamManagement.findAllResultsByStudId(studId, enrollId);
		}
		else
		{
			throw new DataNotFoundedException("Results are not announced...");
		}
	}
	
	@Override
	public List<Exam> searchResultsByCourseName(String courseName, int enrollId) throws DataNotFoundedException {
		
		Course c = courseRepository.findByCourseName(courseName).orElseThrow(()-> new DataNotFoundedException("course name not found in database"));

		studentEnrollmentRepository.findById(enrollId).orElseThrow(()-> new DataNotFoundedException("enrollment Id not found in database"));

		List<StudentEnrollment> stEnrollment = studentEnrollmentRepository.findAllBycourseId(c.getCourseId())
				.orElseThrow(()-> new DataNotFoundedException("course Id not found in database"));
		for(StudentEnrollment se : stEnrollment)
		{
			if(se.getEnrollmentId() == enrollId)
			{
				return se.getExam();
			}
			else
			{
				throw new DataNotFoundedException("Enrollment Id not matched...");
			}
		}
		
		return null;
	}

	

}
