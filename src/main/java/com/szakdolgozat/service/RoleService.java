package com.szakdolgozat.service;

import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.szakdolgozat.entity.Role;
import com.szakdolgozat.enums.RoleName;
import com.szakdolgozat.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Optional<Role> getByRoleName(RoleName roleName){
		return roleRepository.findByRoleName(roleName);
	}
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
}