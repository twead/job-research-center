package com.szakdolgozat.controller;

import java.util.List;

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

import com.szakdolgozat.dto.AdvertisementDto;
import com.szakdolgozat.dto.ApplicationDto;
import com.szakdolgozat.dto.NewApplicationDto;
import com.szakdolgozat.dto.RequestDto;
import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.service.AdvertisementService;
import com.szakdolgozat.service.ApplicationService;

@RestController
@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RequestMapping("/employee/advertisement")
@CrossOrigin
public class EmployeeController {

	private AdvertisementService advertisementService;
	private ApplicationService applicationService;

	@Autowired
	public EmployeeController(AdvertisementService advertisementService, ApplicationService applicationService) {
		this.advertisementService = advertisementService;
		this.applicationService = applicationService;
	}

	@GetMapping("/list_advertisement")
	public ResponseEntity<List> getAllAdvertisement() {
		List<Advertisement> advertisementList = advertisementService.getAllAdvertisement();
		return new ResponseEntity(advertisementList, HttpStatus.OK);
	}

	@GetMapping("/details_advertisement/{id}")
	public ResponseEntity<AdvertisementDto> getAdvertisementDetails(@PathVariable(value = "id") Long advertisementId) {
		AdvertisementDto advertisementDto = advertisementService.getAdvertisementDetails(advertisementId);
		return new ResponseEntity(advertisementDto, HttpStatus.OK);
	}

	@PostMapping(path = "/apply/{email}")
	public ResponseEntity<NewApplicationDto> addApplication(@PathVariable(value = "email") String email,
			@Valid @RequestBody NewApplicationDto newApplicationDto, BindingResult bindingResult) {
		applicationService.addApplication(email, newApplicationDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@GetMapping("/list_applications/{email}")
	public ResponseEntity<List> getAllMyApplication(@PathVariable(value = "email") String email) {
		List<ApplicationDto> applicationList = applicationService.getAllMyApplication(email);
		return new ResponseEntity(applicationList, HttpStatus.OK);
	}

	@PostMapping("/details_application/{email}")
	public ResponseEntity<ApplicationDto> getApplicationDetails(@PathVariable(value = "email") String email,
			@RequestBody RequestDto requestDto) {
		ApplicationDto applicationDto = applicationService.getApplicationDetails(email, requestDto);
		return new ResponseEntity(applicationDto, HttpStatus.OK);
	}

}
