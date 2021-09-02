package com.szakdolgozat.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.szakdolgozat.entity.Employer;
import com.szakdolgozat.repository.EmployerRepository;

public class EmployerService extends UserService{

	private EmployerRepository employerRepository;
	
	@Autowired
	public EmployerService(EmployerRepository employerRepository) {
		this.employerRepository = employerRepository;
	}
	
	public void saveEmployee(Employer employer){
		employerRepository.save(employer);
	}
	
}
