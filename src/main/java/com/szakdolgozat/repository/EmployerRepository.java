package com.szakdolgozat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.szakdolgozat.entity.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
	public Employer findByUserId(Long id);

	public boolean existsByUserId(Long id);
}
