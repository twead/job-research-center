package com.szakdolgozat.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szakdolgozat.entity.Employer;
import com.szakdolgozat.repository.EmployerRepository;

@Service
public class EmployerService extends UserService {

	private EmployerRepository employerRepository;

	@Autowired
	public EmployerService(EmployerRepository employerRepository) {
		this.employerRepository = employerRepository;
	}

	public Optional<Employer> findEmployerById(Long id) {
		return employerRepository.findById(id);
	}

	public void saveEmployee(Employer employer) {
		employerRepository.save(employer);
	}

	public Employer findEmployerByUserId(Long id) {
		return employerRepository.findByUserId(id);
	}

	public boolean existsEmployerByUserId(Long id) {
		return employerRepository.existsByUserId(id);
	}

	public boolean isValidated(String email) {
		return employerRepository.isValidated(email);
	}

}
