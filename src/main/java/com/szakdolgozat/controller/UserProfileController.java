package com.szakdolgozat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szakdolgozat.dto.UpdateEmailDto;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.service.AdminDashboardService;
import com.szakdolgozat.service.UserService;

@RestController
@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_EMPLOYER')")
@RequestMapping("/api/profile")
@CrossOrigin
public class UserProfileController {

	private UserService userService;
	private AdminDashboardService dashboardService;

	@Autowired
	public UserProfileController(UserService userService, AdminDashboardService dashboardService) {
		this.userService = userService;
		this.dashboardService = dashboardService;
	}

	@GetMapping("/details/{email}")
	public ResponseEntity<User> getProfileDetails(@PathVariable(value = "email") String email) {
		User user = userService.findUserByEmail(email).get();
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PutMapping("/update/{email}")
	public ResponseEntity<User> updateProfile(@PathVariable(value = "email") String email,
			@Valid @RequestBody User userDetails) {
		User user = userService.findUserByEmail(email).get();
		return new ResponseEntity(dashboardService.setUserByDetails(user, userDetails), HttpStatus.OK);
	}

	@PostMapping("/update_email/{email}")
	public ResponseEntity<?> updateEmail(@PathVariable(value = "email") String email,
			@Valid @RequestBody UpdateEmailDto updateEmail) {
		dashboardService.updateEmail(email, updateEmail);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("/password-update/{email}")
	public ResponseEntity updatePassword(@PathVariable(value = "email") String email,
			@Valid @RequestBody String newPassword) {
		User user = userService.findUserByEmail(email).get();
		dashboardService.setPassword(user, newPassword);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping(path = "/update_email_activation/{code}")
	public ResponseEntity updateEmailActivation(@PathVariable("code") String code) {
		dashboardService.updateEmailActivation(code);
		return new ResponseEntity(HttpStatus.OK);
	}

}
