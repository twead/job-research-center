package com.szakdolgozat.dto;

import javax.validation.constraints.NotBlank;

public class NewAdvertisementDto {

	@NotBlank
	private String title;
	@NotBlank
	private String type;
	private String city;
	private int payment;
	@NotBlank
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
