package com.szakdolgozat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.service.AdminDashboardService;
import com.szakdolgozat.service.EmployerService;
import com.szakdolgozat.service.UserService;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/api/dashboard")
@CrossOrigin
public class AdminDashboardController {

	private UserService userService;
	private EmployerService employerService;
	private AdminDashboardService dashboardService;

	@Autowired
	public AdminDashboardController(AdminDashboardService dashboardService, UserService userService,
			EmployerService employerService) {
		this.dashboardService = dashboardService;
		this.userService = userService;
		this.employerService = employerService;
	}

	@GetMapping("/employees")
	public List<User> getAllEmployee() {
		List<User> employeeList = userService.findAllUserByRole("ROLE_EMPLOYEE");
		return employeeList;
	}

	@GetMapping("/employers")
	public List<User> getAllEmployers() {
		List<User> employerList = employerService.findAllUserByRole("ROLE_EMPLOYER");
		return employerList;
	}
	
	@GetMapping("/user/details/{id}")
	public ResponseEntity<?> getUserDetailsById(@PathVariable(value = "id") Long userId) {
		User user = userService.findUserById(userId)
				.orElseThrow(() -> new ApiRequestException("A kért felhasználó nem található!"));

		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PutMapping("/user/update/{id}")
	public ResponseEntity updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {
		User user = dashboardService.updateUserInDashboard(userId, userDetails);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@DeleteMapping("/user/delete/{id}")
	public ResponseEntity deleteUser(@PathVariable(value = "id") Long userId) {
		userService.deleteUserById(userId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/employer/set-validation/{id}")
	public ResponseEntity setValidationToEmployer(@PathVariable(value = "id") Long userId) {
		dashboardService.setValidationToEmployer(userId);
		return new ResponseEntity(HttpStatus.OK);
	}

}