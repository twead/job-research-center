package com.szakdolgozat.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szakdolgozat.dto.AdvertisementDto;
import com.szakdolgozat.dto.NewAdvertisementDto;
import com.szakdolgozat.dto.NumberOfRecordsDto;
import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.repository.AdvertisementRepository;
import com.szakdolgozat.repository.EmployerRepository;
import com.szakdolgozat.repository.UserRepository;

@Service
public class AdvertisementService {

	private AdvertisementRepository advertisementRepository;
	private EmployerRepository employerRepository;
	private UserRepository userRepository;
	private UserService userService;

	@Autowired
	public AdvertisementService(UserService userService, AdvertisementRepository advertisementRepository,
			UserRepository userRepository, EmployerRepository employerRepository) {
		this.userService = userService;
		this.advertisementRepository = advertisementRepository;
		this.userRepository = userRepository;
		this.employerRepository = employerRepository;
	}

	public void addAdvertisement(String email, NewAdvertisementDto newAdvertisementDto) {

		Advertisement advertisement = new Advertisement(newAdvertisementDto.getTitle(), newAdvertisementDto.getType(),
				newAdvertisementDto.getCity(), newAdvertisementDto.getPayment(), newAdvertisementDto.getDescription());

		User user = userService.findUserByEmail(email)
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));

		advertisement.setDateOfUpload(new Date());
		advertisement.setEmployer(user.getEmployer());
		user.getEmployer().getJobs().add(advertisement);
		advertisementRepository.save(advertisement);
		userService.saveUser(user);

	}

	public List<Advertisement> getAllAdvertisement() {
		return advertisementRepository.findAll();

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
		numbers.setAdvertisementNumber((int) advertisementRepository.count());
		numbers.setEmployerNumber((int) employerRepository.count());
		return numbers;
	}

}
