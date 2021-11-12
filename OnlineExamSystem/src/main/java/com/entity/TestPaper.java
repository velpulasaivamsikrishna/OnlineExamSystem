package com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
public class TestPaper implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int testPaperCode;
	
	@NotEmpty(message="difficult level to be filled")
	@Pattern(regexp="^low$|^medium$|^high$|^Medium$|^Low$|^High$",message="allowed input: low or medium or high")
	private String difficultyLevel;
	
	@NotEmpty(message="description to be filled")
	private String description;
	
	@OneToMany(targetEntity = TestQuestion.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "testPaperCode", referencedColumnName = "testPaperCode")
	private List<TestQuestion> testquestion = new ArrayList<>();

	public int getTestPaperCode() {
		return testPaperCode;
	}

	public void setTestPaperCode(int testPaperCode) {
		this.testPaperCode = testPaperCode;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<TestQuestion> getTestquestion() {
		return testquestion;
	}

	public void addTestquestion(TestQuestion testquestion) {
		this.testquestion.add(testquestion);
	}
	
	public void removeTestquestion(TestQuestion testquestion) {
		this.testquestion.remove(testquestion);
	}

	@Override
	public String toString() {
		return "TestPaper [testPaperCode=" + testPaperCode + ", difficultyLevel=" + difficultyLevel + ", description="
				+ description + ", testquestion=" + testquestion + "]";
	}
	
	
}

