package com.dongkap.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.master.entity.DistrictEntity;

public interface DistrictRepo extends JpaRepository<DistrictEntity, String>, JpaSpecificationExecutor<DistrictEntity> {
	
}