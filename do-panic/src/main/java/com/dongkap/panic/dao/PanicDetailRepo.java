package com.dongkap.panic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.panic.entity.PanicDetailEntity;

public interface PanicDetailRepo extends JpaRepository<PanicDetailEntity, String>, JpaSpecificationExecutor<PanicDetailEntity> {

}