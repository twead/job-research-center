package com.szakdolgozat.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;

@Entity
@Table(name = "advertisements")
public class Advertisement implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(length = 40)
	private String title;
	@NotNull
	private String type;
	private int payment;
	@NotNull
	@Column(columnDefinition = "TEXT")
	private String description;
	@Nullable
	@Column(length = 35)
	private String city;
	@NotNull
	private Date dateOfUpload;
	@NotNull
	private boolean available;

	@ManyToOne
	@JsonBackReference("advertisementJson")
	private Employer employer;

	@OneToMany(mappedBy = "advertisement")
	@JsonManagedReference("applicationJson")
	private List<Application> applications;

	public Advertisement() {
	}

	public Advertisement(String title, String type, String city, int payment, String description) {
		this.title = title;
		this.type = type;
		this.city = city;
		this.payment = payment;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateOfUpload() {
		return dateOfUpload;
	}

	public void setDateOfUpload(Date date) {
		this.dateOfUpload = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

}
