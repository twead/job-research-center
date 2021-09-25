package com.szakdolgozat.dto;

import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.entity.User;

public class AdvertisementDto {

	private User user;
	private Advertisement advertisement;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

}
