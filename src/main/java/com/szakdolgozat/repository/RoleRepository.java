package com.szakdolgozat.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.szakdolgozat.entity.Role;
import com.szakdolgozat.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {
	Optional<Role> findByRoleName(RoleName roleName);
}