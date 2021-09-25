package com.szakdolgozat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.szakdolgozat.entity.Role;
import com.szakdolgozat.enums.RoleName;
import com.szakdolgozat.service.RoleService;

@Component
public class CreateRoles implements CommandLineRunner {

	@Autowired
	RoleService roleService;

	@Override
	public void run(String... args) throws Exception {

		
		 /*Role roleAdmin = new Role(RoleName.ROLE_ADMIN); Role roleEmployer = new
		 Role(RoleName.ROLE_EMPLOYER); Role roleUser = new
		 Role(RoleName.ROLE_EMPLOYEE); roleService.save(roleAdmin);
		 roleService.save(roleEmployer); roleService.save(roleUser);
		 */

	}
}