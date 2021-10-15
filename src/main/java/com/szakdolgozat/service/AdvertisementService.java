package com.szakdolgozat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szakdolgozat.dto.AdvertisementDto;
import com.szakdolgozat.dto.NewAdvertisementDto;
import com.szakdolgozat.dto.NumberOfRecordsDto;
import com.szakdolgozat.dto.RequestDto;
import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.entity.Application;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.repository.AdvertisementRepository;
import com.szakdolgozat.repository.ApplicationRepository;
import com.szakdolgozat.repository.EmployerRepository;
import com.szakdolgozat.repository.UserRepository;

@Service
public class AdvertisementService {

	private AdvertisementRepository advertisementRepository;
	private ApplicationRepository applicationRepository;
	private EmployerRepository employerRepository;
	private UserRepository userRepository;
	private UserService userService;

	@Autowired
	public AdvertisementService(UserService userService, AdvertisementRepository advertisementRepository,
			ApplicationRepository applicationRepository, UserRepository userRepository,
			EmployerRepository employerRepository) {
		this.userService = userService;
		this.advertisementRepository = advertisementRepository;
		this.applicationRepository = applicationRepository;
		this.userRepository = userRepository;
		this.employerRepository = employerRepository;
	}

	public void addAdvertisement(String email, NewAdvertisementDto newAdvertisementDto) {

		Advertisement advertisement = new Advertisement(newAdvertisementDto.getTitle(), newAdvertisementDto.getType(),
				newAdvertisementDto.getCity(), newAdvertisementDto.getPayment(), newAdvertisementDto.getDescription());

		User user = userService.findUserByEmail(email)
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));

		advertisement.setAvailable(true);
		advertisement.setDateOfUpload(new Date());
		advertisement.setEmployer(user.getEmployer());
		user.getEmployer().getJobs().add(advertisement);
		advertisementRepository.save(advertisement);
		userService.saveUser(user);
	}

	public void updateAdvertisement(Long id, Advertisement updatedAdvertisement) {

		Advertisement advertisement = advertisementRepository.findById(updatedAdvertisement.getId())
				.orElseThrow(() -> new ApiRequestException("Nem létezik a hirdetés!"));

		advertisement.setTitle(updatedAdvertisement.getTitle());
		advertisement.setType(updatedAdvertisement.getType());
		advertisement.setCity(updatedAdvertisement.getCity());
		advertisement.setPayment(updatedAdvertisement.getPayment());
		advertisement.setDescription(updatedAdvertisement.getDescription());
		advertisementRepository.save(advertisement);
	}

	public List<Advertisement> getAllAdvertisement() {
		return advertisementRepository.findAllByAvailable(true);
	}

	public AdvertisementDto getAdvertisementDetails(Long id) {

		AdvertisementDto advertisementDto = new AdvertisementDto();
		Advertisement advertisement = advertisementRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("A keresett álláshirdetés nem található"));
		advertisementDto.setAdvertisement(advertisement);

		Long employerId = advertisementDto.getAdvertisement().getEmployer().getId();
		User user = userRepository.findByEmployerId(employerId);
		advertisementDto.setUser(user);
		return advertisementDto;
	}

	public NumberOfRecordsDto getNumberOfRecords() {
		NumberOfRecordsDto numbers = new NumberOfRecordsDto();
		numbers.setAdvertisementNumber((int) advertisementRepository.countByAvailable(true));
		numbers.setEmployerNumber((int) employerRepository.countByValidated(true));
		return numbers;
	}

	public List<Advertisement> getAllMyAdvertisement(String email, RequestDto requestDto) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található!"));
		List<Advertisement> allAdvertisement = advertisementRepository
				.findAllByEmployerIdAndAvailable(user.getEmployer().getId(), requestDto.isAvailable());
		return allAdvertisement;
	}

	public Advertisement getMyAdvertisement(String email, RequestDto advertisementIdDto) {
		User user = userService.findUserByEmail(email)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található!"));
		Advertisement advertisement = advertisementRepository
				.findByIdAndEmployerId(advertisementIdDto.getId(), user.getEmployer().getId())
				.orElseThrow(() -> new ApiRequestException("A keresett hirdetés nem található!"));
		return advertisement;
	}

	public void deleteAdvertisementById(Long id) {
		List<Application> applications = new ArrayList<>();
		Advertisement advertisement = advertisementRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("A keresett álláshirdetés nem található"));
		applications = applicationRepository.findAllByAdvertisementId(advertisement.getId());
		applicationRepository.deleteAll(applications);
		advertisement.setAvailable(false);
		advertisementRepository.save(advertisement);
	}

}
