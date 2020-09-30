package com.dongkap.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongkap.security.entity.ContactUserEntity;

public interface ContactUserRepo extends JpaRepository<ContactUserEntity, String>, JpaSpecificationExecutor<ContactUserEntity> {

	ContactUserEntity findByUser_Username(String username);
	
	@Query("SELECT cu FROM UserEntity u JOIN u.contactUser cu JOIN FETCH cu.personalInfo pi WHERE LOWER(u.username) = :username OR LOWER(u.email) = :username")
	ContactUserEntity loadPersonalDataByUsername(@Param("username") String username);

}