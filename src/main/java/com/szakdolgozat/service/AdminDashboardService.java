package com.szakdolgozat.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szakdolgozat.dto.UpdateEmailDto;
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
		user.setLoginVerification(userDetails.getLoginVerification());
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

	public void updateEmail(String email, UpdateEmailDto updateEmail) {
		User user = userService.findUserByEmail(email)
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));

		if (userService.existsUserByEmail(updateEmail.getEmail()))
			throw new ApiRequestException("A megadott email cím már létezik!");

		user.setUpdateEmail(updateEmail.getEmail());
		user.setUpdateEmailVerificationCode(generatedKey(16));
		emailService.sendEmailUpdateVerification(user);
		userRepository.save(user);
	}

	private String generatedKey(int length) {
		Random random = new Random();
		char[] code = new char[length];
		for (int i = 0; i < code.length; i++) {
			code[i] = (char) ('a' + random.nextInt(26));
		}
		return new String(code);
	}

	public void updateEmailActivation(String code) {
		User user = userRepository.findByUpdateEmailVerificationCode(code)
				.orElseThrow(() -> new ApiRequestException("Nem sikerült módosítani az email címet!"));

		if (userRepository.existsByEmail(user.getUpdateEmail()))
			throw new ApiRequestException("Már létezik ez az email cím, mint elsődleges email cím");

		user.setEmail(user.getUpdateEmail());
		user.setUpdateEmail(null);
		user.setUpdateEmailVerificationCode(null);
		userService.saveUser(user);
	}

}
