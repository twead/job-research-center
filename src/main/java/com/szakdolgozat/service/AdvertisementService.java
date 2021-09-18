package com.szakdolgozat.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.szakdolgozat.dto.NewAdvertisementDto;
import com.szakdolgozat.entity.Job;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.repository.AdvertisementRepository;

@Service
public class AdvertisementService {

	private AdvertisementRepository advertisementRepository;
	private UserService userService;

	@Autowired
	public AdvertisementService(UserService userService, AdvertisementRepository advertisementRepository) {
		this.userService = userService;
		this.advertisementRepository = advertisementRepository;
	}

	public void addAdvertisement(String email, NewAdvertisementDto newAdvertisementDto) {

		Job advertisement = new Job(newAdvertisementDto.getTitle(), newAdvertisementDto.getType(),
				newAdvertisementDto.getCity(), newAdvertisementDto.getPayment(), newAdvertisementDto.getDescription());

		User user = userService.findUserByEmail(email)
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));

		advertisement.setDateOfUpload(new Date());
		advertisement.setEmployer(user.getEmployer());
		user.getEmployer().getJobs().add(advertisement);
		advertisementRepository.save(advertisement);
		userService.saveUser(user);

	}
}
