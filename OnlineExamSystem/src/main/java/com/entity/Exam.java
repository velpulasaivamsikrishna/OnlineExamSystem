package com.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Exam implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int examRollNo;
	
	private LocalDateTime dateOfExam;
	
	public void setExamRollNo(int examRollNo) {
		this.examRollNo = examRollNo;
	}


	@NotNull(message="status to be filled as true or false")
	private boolean status;
	
	@NotNull(message="maximum score to be filled")
	private int MaximumScore;
	
	private int actualScore;
	
	@NotNull(message = "exam duration to be filled")
	private int examDuration;
	
	@NotNull(message="Is announced to student to be filled as true or false")
	private boolean isAnnouncedToStudent;
	
	public int getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
	}
	
	
	@OneToOne(targetEntity = TestPaper.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "testPaperCode", referencedColumnName = "testPaperCode")
	private TestPaper testpaper;
	
	public int getExamRollNo() {
		return examRollNo;
	}
	
	public LocalDateTime getDateOfExam() {
		return dateOfExam;
	}
	public void setDateOfExam(LocalDateTime localDate) {
		this.dateOfExam = localDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getMaximumScore() {
		return MaximumScore;
	}
	public void setMaximumScore(int maximumScore) {
		MaximumScore = maximumScore;
	}
	public int getActualScore() {
		return actualScore;
	}
	public void setActualScore(int actualScore) {
		this.actualScore = actualScore;
	}
//	public Timer getExamDuration() {
//		return examDuration;
//	}
//	public void setExamDuration(Timer examDuration) {
//		this.examDuration = examDuration;
//	}
	
	public boolean isAnnouncedToStudent() {
		return isAnnouncedToStudent;
	}
	public void setAnnouncedToStudent(boolean isAnnouncedToStudent) {
		this.isAnnouncedToStudent = isAnnouncedToStudent;
	}
	
	public TestPaper getTestpaper() {
		return testpaper;
	}
	public void setTestpaper(TestPaper testpaper) {
		this.testpaper = testpaper;
	}

	@Override
	public String toString() {
		return "Exam [examRollNo=" + examRollNo + ", dateOfExam=" + dateOfExam + ", status=" + status
				+ ", MaximumScore=" + MaximumScore + ", actualScore=" + actualScore + ", examDuration=" + examDuration
				+ ", isAnnouncedToStudent=" + isAnnouncedToStudent + ", testpaper=" + testpaper + "]";
	}
	

//		       TimerTask timerTask = new TimerTask() {
//		           @Override
//		           public void run() {
//		               System.out.println("task  run:"+ new Date());
//		           }
//
//		       };
//
//		       Timer timer = new Timer();
//	
//		       //Schedule the specified task to start repeated, fixed delays at a specified time.This is executed every 3 seconds
//		       timer.schedule(timerTask,10,3000);
		   //}

	
	
}
