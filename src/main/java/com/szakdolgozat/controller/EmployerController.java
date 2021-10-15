package com.szakdolgozat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szakdolgozat.dto.ApplicationAndEmployeeDto;
import com.szakdolgozat.dto.NewAdvertisementDto;
import com.szakdolgozat.dto.RequestDto;
import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.service.AdvertisementService;
import com.szakdolgozat.service.ApplicationService;
import com.szakdolgozat.service.EmployerService;

@RestController
@PreAuthorize("hasRole('ROLE_EMPLOYER')")
@RequestMapping("/employer/advertisement")
@CrossOrigin
public class EmployerController {

	private EmployerService employerService;
	private AdvertisementService advertisementService;
	private ApplicationService applicationService;

	@Autowired
	public EmployerController(EmployerService employerService, AdvertisementService advertisementService,
			ApplicationService applicationService) {
		this.employerService = employerService;
		this.advertisementService = advertisementService;
		this.applicationService = applicationService;
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

	@PostMapping("/list_my_advertisements/{email}")
	public ResponseEntity<List> getAllMyAdvertisement(@PathVariable(value = "email") String email,
			@RequestBody RequestDto requestDto) {
		List<Advertisement> advertisementList = advertisementService.getAllMyAdvertisement(email, requestDto);
		return new ResponseEntity(advertisementList, HttpStatus.OK);
	}

	@PostMapping("/get_my_advertisement/{email}")
	public ResponseEntity<Advertisement> getMyAdvertisement(@PathVariable(value = "email") String email,
			@RequestBody RequestDto requestDto) {
		Advertisement advertisement = advertisementService.getMyAdvertisement(email, requestDto);
		return new ResponseEntity(advertisement, HttpStatus.OK);
	}

	@GetMapping("/get_applications_and_employees/{id}")
	public ResponseEntity<List> getAllApplicationAndEmployee(@PathVariable(value = "id") Long advertisementId) {
		List<ApplicationAndEmployeeDto> applicationList = applicationService
				.getAllApplicationAndEmployee(advertisementId);
		return new ResponseEntity(applicationList, HttpStatus.OK);
	}

	@PostMapping("/get_application_and_employee_details/{email}")
	public ResponseEntity<ApplicationAndEmployeeDto> getApplicationAndEmployeeDetails(
			@PathVariable(value = "email") String email, @RequestBody RequestDto requestDto) {
		ApplicationAndEmployeeDto employee = applicationService.getApplicationAndEmployeeDetails(email, requestDto);
		return new ResponseEntity(employee, HttpStatus.OK);
	}

	@PutMapping(path = "/update_advertisement/{id}")
	public ResponseEntity<?> updateAdvertisement(@PathVariable(value = "id") Long advertisementId,
			@Valid @RequestBody Advertisement advertisement, BindingResult bindingResult) {
		advertisementService.updateAdvertisement(advertisementId, advertisement);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/advertisement/delete/{id}")
	public ResponseEntity deleteAdvertisement(@PathVariable(value = "id") Long advertisementId) {
		advertisementService.deleteAdvertisementById(advertisementId);
		return new ResponseEntity(HttpStatus.OK);
	}

	@DeleteMapping("/application/delete/{id}")
	public ResponseEntity deleteApplication(@PathVariable(value = "id") Long applicationId) {
		applicationService.deleteApplicationById(applicationId);
		return new ResponseEntity(HttpStatus.OK);
	}

}
