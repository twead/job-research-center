package com.szakdolgozat.controller;

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

import com.szakdolgozat.dto.LoginUser;
import com.szakdolgozat.dto.NewUser;
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
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser){		
		JwtDto jwtDto = registrationService.setAuthenticationAndToken(loginUser);
		return new ResponseEntity(jwtDto,HttpStatus.OK);		
	}
	
	@PostMapping(path = "/registration", produces = {MimeTypeUtils.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> addUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult){	
		registrationService.SaveUserAndRole(newUser);		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/activation/{code}")
	public ResponseEntity activation(@PathVariable("code") String code) {
		registrationService.userActivation(code);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
}
