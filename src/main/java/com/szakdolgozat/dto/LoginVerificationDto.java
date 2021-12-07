package com.szakdolgozat.dto;

public class LoginVerificationDto {

	private String email;
	private String password;
	private String loginVerificationCode;

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

	public String getLoginVerificationCode() {
		return loginVerificationCode;
	}

	public void setLoginVerificationCode(String loginVerificationCode) {
		this.loginVerificationCode = loginVerificationCode;
	}

}
