package com.szakdolgozat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.szakdolgozat.entity.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {
	public Employer findByUserId(Long id);

	public boolean existsByUserId(Long id);

	public Integer countByValidated(boolean validated);

	@Query(value = "select employers.validated from users join employers on employers.user_id = users.id where users.email = ?1", nativeQuery = true)
	public boolean isValidated(String email);
}
