package com.szakdolgozat.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szakdolgozat.dto.ApplicationAndEmployeeDto;
import com.szakdolgozat.dto.ApplicationDto;
import com.szakdolgozat.dto.NewApplicationDto;
import com.szakdolgozat.dto.RequestDto;
import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.entity.Application;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.repository.AdvertisementRepository;
import com.szakdolgozat.repository.ApplicationRepository;
import com.szakdolgozat.repository.UserRepository;
import com.szakdolgozat.exception.ApiRequestException;

@Service
public class ApplicationService {

	private UserService userService;
	private UserRepository userRepository;
	private ApplicationRepository applicationRepository;
	private AdvertisementRepository advertisementRepository;

	@Autowired
	public ApplicationService(UserService userService, UserRepository userRepository,
			ApplicationRepository applicationRepository, AdvertisementRepository advertisementRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
		this.applicationRepository = applicationRepository;
		this.advertisementRepository = advertisementRepository;

	}

	public void addApplication(String employeeEmail, NewApplicationDto newApplicationDto) {
		Advertisement advertisement = advertisementRepository.findById(newApplicationDto.getAdvertisementId())
				.orElseThrow(() -> new ApiRequestException("A keresett álláshirdetés nem található!"));
		User employee = userRepository.findByEmail(employeeEmail)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található!"));

		Application application = new Application();

		application.setComment(newApplicationDto.getComment());
		application.setDateOfApplication(new Date());
		application.setPdf(newApplicationDto.getPdfName());
		application.setAvailable(true);

		application.setAdvertisement(advertisement);
		application.setUser(employee);
		employee.getApplications().add(application);
		advertisement.getApplications().add(application);

		applicationRepository.save(application);
		advertisementRepository.save(advertisement);
		userService.saveUser(employee);
	}

	public List<ApplicationDto> getAllMyApplication(String email) {
		List<ApplicationDto> applications = new ArrayList<>();

		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található!"));
		List<Application> application = applicationRepository.findAllByUserId(user.getId());

		application.forEach(row -> {
			ApplicationDto currentApplication = new ApplicationDto();
			currentApplication.setAdvertisement(row.getAdvertisement());
			currentApplication.setApplication(row);
			applications.add(currentApplication);
		});
		return applications;
	}

	public ApplicationDto getApplicationDetails(String email, RequestDto requestDto) {
		ApplicationDto applicationDto = new ApplicationDto();

		User user = userService.findUserByEmail(email)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található"));

		Application application = applicationRepository.findByIdAndUserId(requestDto.getId(), user.getId())
				.orElseThrow(() -> new ApiRequestException("A keresett állásjelentkezés nem található"));

		applicationDto.setApplication(application);
		applicationDto.setAdvertisement(application.getAdvertisement());

		Long employerId = applicationDto.getAdvertisement().getEmployer().getId();
		User employer = userRepository.findByEmployerId(employerId);
		applicationDto.setUser(employer);
		return applicationDto;
	}

	public List<ApplicationAndEmployeeDto> getAllApplicationAndEmployee(Long advertisementId) {
		List<ApplicationAndEmployeeDto> applicationAndEmployee = new ArrayList<>();
		List<Application> applications = applicationRepository.findAllByAdvertisementId(advertisementId);

		applications.forEach(application -> {
			ApplicationAndEmployeeDto currentDto = new ApplicationAndEmployeeDto();
			currentDto.setApplication(application);
			currentDto.setUser(application.getUser());
			applicationAndEmployee.add(currentDto);
		});
		return applicationAndEmployee;
	}

	public ApplicationAndEmployeeDto getApplicationAndEmployeeDetails(String email, RequestDto requestDto) {
		ApplicationAndEmployeeDto applicationAndEmployee = new ApplicationAndEmployeeDto();
		Application application = applicationRepository.findById(requestDto.getId())
				.orElseThrow(() -> new ApiRequestException("A keresett állásjelentkezés nem található"));
		;
		User employer = application.getAdvertisement().getEmployer().getUser();

		if (employer.getEmail().equals(email)) {
			applicationAndEmployee.setUser(application.getUser());
			applicationAndEmployee.setEmployer(employer);
			applicationAndEmployee.setAdvertisement(application.getAdvertisement());
			applicationAndEmployee.setApplication(application);
		} else
			throw new ApiRequestException("Nincs megfelelő jogosultság a megtekintéséhez!");
		return applicationAndEmployee;
	}

	public void deleteApplicationById(Long id) {
		Application application = applicationRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("A keresett állásjelentkezés nem található"));
		application.setAvailable(false);
		applicationRepository.save(application);
	}

}
