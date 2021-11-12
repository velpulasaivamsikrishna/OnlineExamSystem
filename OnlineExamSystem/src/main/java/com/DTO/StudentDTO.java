package com.DTO;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class StudentDTO {
	private  int studentId;
	
	@NotEmpty(message="username to be filled")
	@Size(min=4,message="username should have atleast two characters")
	private  String username;
	
	@NotEmpty(message="password to be filled")
	@Size(min=8, message="password should have atleast 8 characters")
	private  String password;
	
	private  String firstName;
	private  String lastName;
	private  String Gender;
	private  String Email;
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	private  String mobileNo;
	public int getStudentId() {
		return studentId;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getGender() {
		return Gender;
	}
	public String getEmail() {
		return Email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	
}
