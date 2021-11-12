package com.DTO;

import javax.validation.constraints.NotEmpty;

public class ExamFormDTO {
	
		@NotEmpty(message="question number to be filled")
		private int questionNo;
				
		private int marked_option;
		
		private int testPaperCode;
		
		private int examRollNo;
		
		public int getQuestionNo() {
			return questionNo;
		}
		public void setQuestionNo(int questionNo) {
			this.questionNo = questionNo;
		}
		public int getMarked_option() {
			return marked_option;
		}
		public void setMarked_option(int marked_option) {
			this.marked_option = marked_option;
		}
		public int getTestPaperCode() {
			return testPaperCode;
		}
		public void setTestPaperCode(int testPaperCode) {
			this.testPaperCode = testPaperCode;
		}
		public int getExamRollNo() {
			return examRollNo;
		}
		public void setExamRollNo(int examRollNo) {
			this.examRollNo = examRollNo;
		}
		@Override
		public String toString() {
			return "ExamFormDTO [questionNo=" + questionNo + ", marked_option=" + marked_option + ", testPaperCode="
					+ testPaperCode + ", examRollNo=" + examRollNo + "]";
		}

	}