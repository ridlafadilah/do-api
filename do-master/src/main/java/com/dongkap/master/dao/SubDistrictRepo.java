package com.dongkap.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.master.entity.SubDistrictEntity;

public interface SubDistrictRepo extends JpaRepository<SubDistrictEntity, String>, JpaSpecificationExecutor<SubDistrictEntity> {
	
}