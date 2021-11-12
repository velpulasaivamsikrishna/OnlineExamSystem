package com.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advices.DataNotFoundedException;
import com.entity.Exam;
import com.entity.Student;
import com.entity.StudentEnrollment;
import com.entity.TestPaper;
import com.repository.ExamRepository;
import com.repository.StudentEnrollmentRepository;
import com.repository.StudentRepository;
import com.repository.TestPaperRepository;
import com.service.AdminExamManagementService;

@Service
public class AdminExamManagement implements AdminExamManagementService {

	@Autowired
	private StudentEnrollmentRepository studentEnrollmentRepository;

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private TestPaperRepository testPaperRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private AdminAuthenticationImpl adminAuthenticationImpl;

	@Override
	public Exam scheduleExamForStudent(int student_id, int enrollmentId, int testPaperCode, LocalDateTime locDateTime,
			int examDurationInMinutes) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			Student student = studentRepository.findById(student_id)
					.orElseThrow(() -> new DataNotFoundedException("student Id not found in database"));
			StudentEnrollment studentEnrollment = studentEnrollmentRepository.findById(enrollmentId)
					.orElseThrow(() -> new DataNotFoundedException("enrollment Id not found in database"));
			TestPaper testPaper = testPaperRepository.findById(testPaperCode)
					.orElseThrow(() -> new DataNotFoundedException("test paper code not found in database"));

			Exam exam = new Exam();
			for (StudentEnrollment se : student.getStudentEnrollment()) {
				if (se.getEnrollmentId() == enrollmentId) {
					exam.setExamDuration(examDurationInMinutes);
					exam.setMaximumScore(10);
					exam.setDateOfExam(locDateTime);
					// exam.setActualScore(0);
					exam.setStatus(true);
					exam.setAnnouncedToStudent(false);
					exam.setTestpaper(testPaper);

					examRepository.save(exam);
					studentEnrollment.addExam(exam);
					studentEnrollmentRepository.save(studentEnrollment);

				} else {
					throw new DataNotFoundedException("Student didn't enrolled in this course....");
				}

			}

			return exam;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public Exam scheduleExamForBatch(String batchName, int testPaperCode, LocalDateTime localDateTime,
			int examDurationInMinutes) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			List<StudentEnrollment> studentEnrollment = studentEnrollmentRepository.findBybatchName(batchName)
					.orElseThrow(() -> new DataNotFoundedException("batch name not found in database"));
			TestPaper testPaper = testPaperRepository.findById(testPaperCode)
					.orElseThrow(() -> new DataNotFoundedException("test paper code not found in database"));
			Exam exam = null;
			for (StudentEnrollment se : studentEnrollment) {
				Exam e = new Exam();
				e.setTestpaper(testPaper);
				e.setAnnouncedToStudent(false);
				e.setMaximumScore(10);
				e.setStatus(true);
				e.setExamDuration(examDurationInMinutes);
				e.setDateOfExam(localDateTime);
				// e.setActualScore(0);
				se.addExam(e);
				examRepository.save(e);
				exam = e;
				studentEnrollmentRepository.save(se);
			}
			return exam;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public Exam changeTestPaperForStudent(int studentId, int enrollmentId, int testPaperCode, int examrollno)
			throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			TestPaper testPaper = testPaperRepository.findById(testPaperCode)
					.orElseThrow(() -> new DataNotFoundedException("test paper code not found in database"));
			StudentEnrollment studentEnrollment = studentEnrollmentRepository.findById(enrollmentId)
					.orElseThrow(() -> new DataNotFoundedException("enrollment Id not found in database"));
			Student student = studentRepository.findById(studentId)
					.orElseThrow(() -> new DataNotFoundedException("student Id not found in database"));
			Exam exam = examRepository.findById(examrollno)
					.orElseThrow(() -> new DataNotFoundedException("exam roll number not found in database"));

			Exam e = null;
			for (StudentEnrollment se : student.getStudentEnrollment()) {
				if (se.getEnrollmentId() == enrollmentId) {
					for (Exam exam1 : se.getExam()) {
						if (exam1.getExamRollNo() == examrollno) {
							testPaperRepository.save(testPaper);
							exam1.setTestpaper(testPaper);
							examRepository.save(exam1);
							e = exam1;
							break;
						}

						else {
							throw new DataNotFoundedException("exam roll number not matched");
						}
					}
				} else {
					throw new DataNotFoundedException("enrollment Id not matched");
				}
			}
			return e;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}

	}

	@Override
	public Exam changeTestPaperForBatch(int enrollmentId, int testPaperCode) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			TestPaper testPaper = testPaperRepository.findById(testPaperCode)
					.orElseThrow(() -> new DataNotFoundedException("test paper code not found in databse"));
			StudentEnrollment studentenrollment = studentEnrollmentRepository.findById(enrollmentId)
					.orElseThrow(() -> new DataNotFoundedException("enrollment Id not found in databse"));

			List<StudentEnrollment> studentEnrollment1 = studentEnrollmentRepository
					.findBybatchName(studentenrollment.getBatchName())
					.orElseThrow(() -> new DataNotFoundedException("batch name not found in databse"));
			Exam e = null;
			for (StudentEnrollment se : studentEnrollment1) {
				for (Exam exam1 : se.getExam()) {
					exam1.setTestpaper(testPaper);
					examRepository.save(exam1);
					e = exam1;

				}
			}

			return e;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public List<Exam> findAllResultsByBatchName(String batchName, int enrollId) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			List<StudentEnrollment> studentEnrollment = studentEnrollmentRepository.findBybatchName(batchName)
					.orElseThrow(() -> new DataNotFoundedException("batch name not found in databse"));

			for (StudentEnrollment se : studentEnrollment) {
				if (se.getEnrollmentId() == enrollId) {
					return se.getExam();
				} else {
					throw new DataNotFoundedException("enrollment Id not matched");
				}
			}
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
		return null;
	}

	@Override
	public List<Exam> findResultByEnrollmentId(int enrollmentId) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			StudentEnrollment studentenrollment = studentEnrollmentRepository.findById(enrollmentId)
					.orElseThrow(() -> new DataNotFoundedException("enrollment Id not found in databse"));
			return studentenrollment.getExam();
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public List<Exam> findAllResultsByStudId(int studentId, int enrollId) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			Student s = studentRepository.findById(studentId)
					.orElseThrow(() -> new DataNotFoundedException("Student Id not matched..."));
			for (StudentEnrollment se : s.getStudentEnrollment()) {
				if (se.getEnrollmentId() == enrollId) {
					return se.getExam();
				} else {
					throw new DataNotFoundedException("Enrollment Id not matched...");
				}
			}
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
		return null;
	}

	static boolean releaseTestResultForStudent = false;
	static boolean releaseTestResultByEnrollmentId = false;
	static boolean releaseTestResultForBatch = false;

	@Override
	public boolean releaseTestResultByEnrollmentId(int enrollmentId) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			return AdminExamManagement.releaseTestResultByEnrollmentId = true;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

	@Override
	public boolean releaseAllTestResultForStudent(int studentId, int enrollId) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			return AdminExamManagement.releaseTestResultForStudent = true;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}

	}

	@Override
	public boolean releaseAllTestResultForBatch(String batchName, int enrollId) throws Exception {
		if (adminAuthenticationImpl.isLogin) {
			return AdminExamManagement.releaseTestResultForBatch = true;
		} else {
			throw new Exception("You Must be Logged in as Administrator...");
		}
	}

}
