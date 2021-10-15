package com.szakdolgozat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.szakdolgozat.entity.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

	public List<Application> findAllByUserId(Long id);

	public List<Application> findAllByAdvertisementId(Long id);

	public Optional<Application> findByIdAndUserId(Long applicationId, Long userId);

}
