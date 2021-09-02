package com.szakdolgozat.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.szakdolgozat.dto.LoginUser;
import com.szakdolgozat.dto.NewUser;
import com.szakdolgozat.entity.Employer;
import com.szakdolgozat.entity.Role;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.enums.RoleName;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.security.dto.JwtDto;
import com.szakdolgozat.security.jwt.JwtProvider;

@Service
public class RegistrationService {

	private PasswordEncoder passwordEncoder;
	private RoleService roleService;
	private UserService userService;
	private AuthenticationManager authenticationManager;
	private JwtProvider jwtProvider;

	@Autowired
	public RegistrationService(PasswordEncoder passwordEncoder, RoleService roleService, UserService userService,
			AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
		this.userService = userService;
		this.authenticationManager = authenticationManager;
		this.jwtProvider = jwtProvider;
	}

	public JwtDto setAuthenticationAndToken(LoginUser loginUser) {

		User user = userService.findUserByEmail(loginUser.getEmail())
				.orElseThrow(() -> new ApiRequestException("Hibás felhasználónév vagy jelszó!"));

		Authentication authentication;
		try {
			authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword()));
		} catch (Exception e) {
			throw new ApiRequestException("Hibás felhasználónév vagy jelszó!");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		return jwtDto;
	}

	public void SaveUserAndRole(NewUser newUser) {

		if (userService.existsUserByEmail(newUser.getEmail()))
			throw new ApiRequestException("Az email cím foglalt!");

		Role role = roleService.getByRoleName(RoleName.ROLE_EMPLOYEE).get();

		User user = new User(newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()), newUser.getName(),
				newUser.getDateOfBorn(), newUser.getPhoneNumber());

		user.setActivation(generatedKey());
		user.setRole(role);

		if (newUser.getIsEmployer()) {
			saveIfEmployer(user);
		} else
			userService.saveUser(user);

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

	private String generatedKey() {
		Random random = new Random();
		char[] code = new char[16];
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

}
