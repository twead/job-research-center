package com.szakdolgozat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.szakdolgozat.entity.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

	public Integer countByAvailable(boolean available);

	public List<Advertisement> findAllByAvailable(boolean available);

	public List<Advertisement> findAllByEmployerId(Long id);

	public List<Advertisement> findAllByEmployerIdAndAvailable(Long id, boolean available);

	public Optional<Advertisement> findByIdAndEmployerId(Long advertisementId, Long employerId);

}
