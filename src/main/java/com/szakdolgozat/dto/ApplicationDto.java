package com.szakdolgozat.dto;

import com.szakdolgozat.entity.Advertisement;
import com.szakdolgozat.entity.Application;
import com.szakdolgozat.entity.User;

public class ApplicationDto {

	private Application application;
	private Advertisement advertisement;
	private User user;

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Advertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
