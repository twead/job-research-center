package com.szakdolgozat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szakdolgozat.entity.User;
import com.szakdolgozat.repository.UserRepository;

@Service
@Transactional
public class AdminDashboardService {
	private UserService userService;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private EmployerService employerService;

	@Autowired
	public AdminDashboardService(UserService userService, EmployerService employerService,
			UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.employerService = employerService;
		this.passwordEncoder = passwordEncoder;
	}

	public User setUserByDetails(User user, User userDetails) {

		user.setName(userDetails.getName());
		user.setDateOfBorn(userDetails.getDateOfBorn());
		user.setPhoneNumber(userDetails.getPhoneNumber());

		userRepository.save(user);
		return user;
	}

	public void setPassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}
}
