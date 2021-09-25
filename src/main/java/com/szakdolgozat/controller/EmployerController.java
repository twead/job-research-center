package com.szakdolgozat.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.szakdolgozat.dto.NewAdvertisementDto;
import com.szakdolgozat.service.AdvertisementService;
import com.szakdolgozat.service.EmployerService;

@RestController
@PreAuthorize("hasRole('ROLE_EMPLOYER')")
@RequestMapping("/employer/advertisement")
@CrossOrigin
public class EmployerController {

	private EmployerService employerService;
	private AdvertisementService advertisementService;

	@Autowired
	public EmployerController(EmployerService employerService, AdvertisementService advertisementService) {
		this.employerService = employerService;
		this.advertisementService = advertisementService;
	}

	@GetMapping("/isValidated/{email}")
	public ResponseEntity<Boolean> isValidated(@PathVariable(value = "email") String email) {
		boolean isValidated = employerService.isValidated(email);
		return new ResponseEntity(isValidated, HttpStatus.OK);
	}

	@PostMapping(path = "/create/{email}")
	public ResponseEntity<?> addAdvertisement(@PathVariable(value = "email") String email,
			@Valid @RequestBody NewAdvertisementDto newAdvertisementDto, BindingResult bindingResult) {
		advertisementService.addAdvertisement(email, newAdvertisementDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
