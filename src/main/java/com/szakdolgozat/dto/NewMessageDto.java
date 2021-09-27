package com.szakdolgozat.dto;

public class NewMessageDto {

	private Long id;
	private String message;
	private boolean isEmployer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEmployer() {
		return isEmployer;
	}

	public void setEmployer(boolean isEmployer) {
		this.isEmployer = isEmployer;
	}

}
