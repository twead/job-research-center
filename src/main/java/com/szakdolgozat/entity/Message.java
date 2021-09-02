package com.szakdolgozat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(columnDefinition="TEXT")
	private String message;
	@NotNull
	private Date dateOfSending;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Employer employer;
	
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
	public Date getDateOfSending() {
		return dateOfSending;
	}
	public void setDateOfSending(Date dateOfSending) {
		this.dateOfSending = dateOfSending;
	}	
	
	
	
}
