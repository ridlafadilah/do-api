package com.dongkap.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.master.entity.ProvinceEntity;

public interface ProvinceRepo extends JpaRepository<ProvinceEntity, String>, JpaSpecificationExecutor<ProvinceEntity> {
	
}