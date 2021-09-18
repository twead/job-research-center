package com.szakdolgozat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.szakdolgozat.entity.Job;

@Repository
public interface AdvertisementRepository extends JpaRepository<Job, Long>{

}
