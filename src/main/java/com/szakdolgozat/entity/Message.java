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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String message;
	@NotNull
	private Date dateOfSending;
	private Boolean fromEmployer;
	private Boolean seen;

	@ManyToOne
	@JsonBackReference("messageEmployeeJson")
	private User user;

	@ManyToOne
	@JsonBackReference("messageEmployerJson")
	private Employer employer;

	public Message() {}
	
	public Message(String message) {
		this.message=message;
	}

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

	public Boolean getFromEmployer() {
		return fromEmployer;
	}

	public void setFromEmployer(Boolean fromEmployer) {
		this.fromEmployer = fromEmployer;
	}

	public Boolean getSeen() {
		return seen;
	}

	public void setSeen(Boolean seen) {
		this.seen = seen;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

}
