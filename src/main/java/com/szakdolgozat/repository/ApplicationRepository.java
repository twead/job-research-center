package com.szakdolgozat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.szakdolgozat.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

	public List<Application> findAllByUserId(Long id);

}
