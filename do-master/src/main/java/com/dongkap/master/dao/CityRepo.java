package com.dongkap.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.master.entity.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, String>, JpaSpecificationExecutor<CityEntity> {
	
}