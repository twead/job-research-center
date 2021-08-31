package com.szakdolgozat.security.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.szakdolgozat.entity.User;
import com.szakdolgozat.service.UserService;
import com.szakdolgozat.security.entity.UserPrincipal;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	private UserService userService;

	@Autowired
	public UserDetailsServiceImp(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userService.findUserByEmail(email).get();
		return UserPrincipal.build(user);
	}

}