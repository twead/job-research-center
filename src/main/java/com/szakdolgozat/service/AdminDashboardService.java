package com.szakdolgozat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.repository.UserRepository;

@Service
@Transactional
public class AdminDashboardService {
	private UserService userService;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private EmailService emailService;

	@Autowired
	public AdminDashboardService(UserService userService, UserRepository userRepository,
			PasswordEncoder passwordEncoder, EmailService emailService) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.emailService = emailService;
	}

	public User setUserByDetails(User user, User userDetails) {

		user.setName(userDetails.getName());
		user.setDateOfBorn(userDetails.getDateOfBorn());
		user.setPhoneNumber(userDetails.getPhoneNumber());

		userRepository.save(user);
		return user;
	}

	public User updateUserInDashboard(Long id, User userDetails) {

		User user = userService.findUserById(id)
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));

		return setUserByDetails(user, userDetails);
	}

	public void setPassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	public void setValidationToEmployer(Long id) {
		User user = userService.findUserById(id)
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));
		user.getEmployer().setValidated(true);
		userService.saveUser(user);
		emailService.sendEmailVerificationToEmployerAboutValidation(user);
	}
}
