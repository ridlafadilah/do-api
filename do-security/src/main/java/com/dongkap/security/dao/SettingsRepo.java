package com.dongkap.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.security.entity.SettingsEntity;

public interface SettingsRepo extends JpaRepository<SettingsEntity, String>, JpaSpecificationExecutor<SettingsEntity> {

	SettingsEntity findByUser_Username(String username);

}