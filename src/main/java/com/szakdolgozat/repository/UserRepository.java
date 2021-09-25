package com.szakdolgozat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.szakdolgozat.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

	Optional<User> findByActivation(String code);
	
	public User findByEmployerId(Long id);

	User findByResetPasswordCode(String code);
	
	@Query(value = "select * from users join roles on roles.id = role_id where roles.role_name = ?1", nativeQuery = true)
	List<User> findAllByRole(String role);
	
}