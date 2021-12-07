package com.szakdolgozat.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.szakdolgozat.dto.LoginUserDto;
import com.szakdolgozat.dto.LoginVerificationDto;
import com.szakdolgozat.dto.NewUserDto;
import com.szakdolgozat.entity.Employer;
import com.szakdolgozat.entity.Role;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.enums.RoleName;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.repository.UserRepository;
import com.szakdolgozat.security.dto.JwtDto;
import com.szakdolgozat.security.jwt.JwtProvider;

@Service
public class RegistrationService {

	private PasswordEncoder passwordEncoder;
	private RoleService roleService;
	private UserService userService;
	private UserRepository userRepository;
	private AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;
	private EmailService emailService;

	@Autowired
	public RegistrationService(PasswordEncoder passwordEncoder, RoleService roleService, UserService userService,
			UserRepository userRepository, AuthenticationManager authenticationManager, JwtProvider jwtProvider,
			EmailService emailService) {
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
		this.userService = userService;
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
		this.emailService = emailService;
	}

	public LoginVerificationDto login(LoginUserDto loginUserDto) {

		LoginVerificationDto verificationDto = new LoginVerificationDto();

		User user = userService.findUserByEmail(loginUserDto.getEmail())
				.orElseThrow(() -> new ApiRequestException("Hibás felhasználónév vagy jelszó!"));

		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
		} catch (Exception e) {
			throw new ApiRequestException("Hibás felhasználónév vagy jelszó!");
		}

		if (user.getLoginVerification()) {
			user.setLoginVerificationCode(generatedKey(5));
			userRepository.save(user);
			emailService.sendLoginVerificationToUser(user);
			verificationDto.setEmail(user.getEmail());
			verificationDto.setPassword(loginUserDto.getPassword());
		}
		return verificationDto;
	}

	public JwtDto setAuthenticationAndTokenWithoutVerification(LoginUserDto loginUserDto) {

		User user = userService.findUserByEmail(loginUserDto.getEmail())
				.orElseThrow(() -> new ApiRequestException("Hibás felhasználónév vagy jelszó!"));

		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(), loginUserDto.getPassword()));
		} catch (Exception e) {
			throw new ApiRequestException("Hibás felhasználónév vagy jelszó!");
		}

		if (user.getLoginVerification()) {
			user.setLoginVerificationCode(generatedKey(5));
			userRepository.save(user);
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		return jwtDto;
	}

	public JwtDto setAuthenticationAndTokenWithVerification(LoginVerificationDto verificationDto) {

		User user = userService.findUserByEmail(verificationDto.getEmail())
				.orElseThrow(() -> new ApiRequestException("Hibás felhasználónév vagy jelszó!"));

		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(verificationDto.getEmail(), verificationDto.getPassword()));
		} catch (Exception e) {
			throw new ApiRequestException("Hibás felhasználónév vagy jelszó!");
		}

		if (!user.getLoginVerificationCode().equals(verificationDto.getLoginVerificationCode().toLowerCase())) {
			throw new ApiRequestException("Megerősítő kód nem megfelelő!");
		}

		user.setLoginVerificationCode(null);
		userRepository.save(user);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		return jwtDto;
	}

	public void SaveUserAndRole(NewUserDto newUserDto) {

		if (userService.existsUserByEmail(newUserDto.getEmail()))
			throw new ApiRequestException("Az email cím foglalt!");

		Role role = roleService.getByRoleName(RoleName.ROLE_EMPLOYEE).get();

		User user = new User(newUserDto.getEmail(), passwordEncoder.encode(newUserDto.getPassword()),
				newUserDto.getName(), newUserDto.getDateOfBorn(), newUserDto.getPhoneNumber());

		user.setActivation(generatedKey(16));
		user.setLoginVerification(true);
		user.setLoginVerificationCode(null);
		user.setUpdateEmail(null);
		user.setUpdateEmailVerificationCode(null);
		user.setRole(role);

		if (newUserDto.getIsEmployer()) {
			saveIfEmployer(user);
			emailService.sendEmailVerificationToUserOrEmployer(user, true);
		} else {
			userService.saveUser(user);
			emailService.sendEmailVerificationToUserOrEmployer(user, false);
		}

	}

	private void saveIfEmployer(User user) {

		Role role = roleService.getByRoleName(RoleName.ROLE_EMPLOYER).get();
		user.setRole(role);

		Employer employer = new Employer();
		user.setEmployer(employer);

		employer.setValidated(false);
		employer.setUser(user);

		userService.saveUser(user);

	}

	private String generatedKey(int length) {
		Random random = new Random();
		char[] code = new char[length];
		for (int i = 0; i < code.length; i++) {
			code[i] = (char) ('a' + random.nextInt(26));
		}
		return new String(code);
	}

	public void userActivation(String code) {
		User user = userService.userActivation(code)
				.orElseThrow(() -> new ApiRequestException("Nem sikerült aktiválni az email címet!"));

		user.setEnabled(true);
		user.setActivation("");
		userService.saveUser(user);
	}

	public User findUserByEmail(String email) {
		return userService.findUserByEmail(email).get();
	}

	public void sendForgotPasswordMessage(User user) {
		user.setResetPasswordCode(generatedKey(16));
		userService.saveUser(user);
		emailService.sendForgotPasswordEmail(user);
	}

	public User findUserByResetPasswordCode(String code) {
		return userService.findUserByResetPasswordCode(code);
	}

	public User findUserById(Long id) {
		return userService.findUserById(id).get();
	}

	public void updateForgotPassword(User user, String newPassword) {
		user.setPassword(passwordEncoder.encode(newPassword));
		user.setResetPasswordCode("");
		userService.saveUser(user);
	}

}
