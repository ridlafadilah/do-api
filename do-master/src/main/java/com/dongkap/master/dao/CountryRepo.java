package com.dongkap.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.master.entity.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, String>, JpaSpecificationExecutor<CountryEntity> {
	
}