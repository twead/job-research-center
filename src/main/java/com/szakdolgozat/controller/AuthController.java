package com.szakdolgozat.controller;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szakdolgozat.dto.ForgotPasswordDto;
import com.szakdolgozat.dto.LoginUserDto;
import com.szakdolgozat.dto.NewUserDto;
import com.szakdolgozat.dto.UpdatePasswordDto;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.security.dto.JwtDto;
import com.szakdolgozat.security.jwt.JwtProvider;
import com.szakdolgozat.service.RegistrationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	private RegistrationService registrationService;
	private JwtProvider jwtProvider;

	@Autowired
	public AuthController(RegistrationService registrationService, JwtProvider jwtProvider) {
		this.registrationService = registrationService;
		this.jwtProvider = jwtProvider;
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUserDto loginUserDto) {
		JwtDto jwtDto = registrationService.setAuthenticationAndToken(loginUserDto);
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}

	@PostMapping(path = "/registration", produces = { MimeTypeUtils.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addUser(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult) {
		registrationService.SaveUserAndRole(newUserDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@GetMapping(path = "/activation/{code}")
	public ResponseEntity activation(@PathVariable("code") String code) {
		registrationService.userActivation(code);
		return new ResponseEntity(HttpStatus.OK);
	}

	@PostMapping("/refresh")
	public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
		String token = jwtProvider.refreshToken(jwtDto);
		JwtDto jwt = new JwtDto(token);
		return new ResponseEntity(jwt, HttpStatus.OK);
	}

	@PostMapping(path = "/forgot-password")
	public void forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {
		User user = registrationService.findUserByEmail(forgotPasswordDto.getEmail());
		registrationService.sendForgotPasswordMessage(user);
	}

	@GetMapping(path = "/reset-password/{code}")
	public ResponseEntity<User> resetPassword(@PathVariable("code") String code) {
		User user = registrationService.findUserByResetPasswordCode(code);
		return new ResponseEntity(user, HttpStatus.OK);
	}

	@PostMapping(path = "/update-password/{code}")
	public void updatePassword(@PathVariable("code") String code, @RequestBody UpdatePasswordDto updatePasswordDto) {
		User user = registrationService.findUserByResetPasswordCode(code);
		registrationService.updateForgotPassword(user, updatePasswordDto.getPassword());
	}

}
