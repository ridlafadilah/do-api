package com.dongkap.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.dongkap.master.entity.LocaleEntity;

public interface LocaleRepo extends JpaRepository<LocaleEntity, String>, JpaSpecificationExecutor<LocaleEntity> {

	LocaleEntity findByLocaleCode(String localeCode);
	
	@Modifying
	@Query("UPDATE LocaleEntity l SET l.localeDefault = false WHERE l.localeDefault = true")
	void changeLocaleDefault();

}