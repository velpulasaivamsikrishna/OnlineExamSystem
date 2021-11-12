package com.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Admin implements Serializable{
	   
	@Id
	@NotEmpty(message="username to be filled")
	@Size(min=4, message="username should have atleast two characters")
	@Column(name = "username")
	private String UserName;
	
	@NotEmpty(message="password to be filled")
	@Size(min=4, message="password should have atleast 8 characters")
	private String Password;
	
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public String toString() {
		return "Admin [UserName=" + UserName + ", Password=" + Password + "]";
	}
	
}
