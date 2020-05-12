package com.dongkap.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.security.entity.FunctionEntity;

public interface FunctionRepo extends JpaRepository<FunctionEntity, String>, JpaSpecificationExecutor<FunctionEntity> {

}