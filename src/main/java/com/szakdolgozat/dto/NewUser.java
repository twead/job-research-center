package com.szakdolgozat.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class NewUser {

	@Email
	private String email;
	@NotBlank
	private String password;
	@NotBlank
	private String name;	
	private String role;
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date dateOfBorn;
	private String phoneNumber;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Date getDateOfBorn() {
		return dateOfBorn;
	}
	public void setDateOfBorn(Date dateOfBorn) {
		this.dateOfBorn = dateOfBorn;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
