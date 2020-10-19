package com.dongkap.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.security.entity.CorporateEntity;

public interface CorporateRepo extends JpaRepository<CorporateEntity, String>, JpaSpecificationExecutor<CorporateEntity> {

	CorporateEntity findByCorporateId(String corporateId);
	
}