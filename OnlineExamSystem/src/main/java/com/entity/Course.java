package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Course implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	
	@NotEmpty(message="course name to be filled")
	@Size(min=2,message="course name should have atleast two characters")
	private String CourseName;
	
	@NotEmpty(message="course type to be filled")
	private String courseType;
	
	@NotEmpty(message="description to be filled")
	private String Description;
	
	@OneToMany(targetEntity = TestPaper.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "courseId", referencedColumnName = "courseId")
	private List<TestPaper> testpaper = new ArrayList<>();;
	
	public List<TestPaper> getTestpaper() {
		return testpaper;
	}
	public void addTestpaper(TestPaper testpaper) {
		this.testpaper.add(testpaper);
	}
	
	public void removeTestpaper(TestPaper testpaper) {
		this.testpaper.remove(testpaper);
	}
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", CourseName=" + CourseName + ", courseType=" + courseType
				+ ", Description=" + Description + "]";
	}
	
	
}
