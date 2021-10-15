package com.szakdolgozat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.repository.AdvertisementRepository;
import com.szakdolgozat.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AdvertisementRepository advertisementRepository;

	public Optional<User> findUserById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public boolean existsUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public Optional<User> userActivation(String code) {
		return userRepository.findByActivation(code);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public User findUserByResetPasswordCode(String code) {
		return userRepository.findByResetPasswordCode(code);
	}

	public List<User> findAllUserByRole(String role) {
		return userRepository.findAllByRoleAndIsEnabled(role, true);
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public void deleteEmployeeById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található"));
		user.setEnabled(false);
		userRepository.save(user);
	}

	public void deleteEmployerById(Long id) {
		List<Advertisement> advertisements = new ArrayList<>();

		User user = userRepository.findById(id)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található"));

		advertisements = advertisementRepository.findAllByEmployerId(user.getEmployer().getId());
		advertisements.forEach(advertisement -> {
			advertisement.setAvailable(false);
			advertisementRepository.save(advertisement);
		});
		user.setEnabled(false);
		user.getEmployer().setValidated(false);
		userRepository.save(user);
	}

}
