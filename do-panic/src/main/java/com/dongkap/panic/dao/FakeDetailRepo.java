package com.dongkap.panic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.panic.entity.FakeDetailEntity;

public interface FakeDetailRepo extends JpaRepository<FakeDetailEntity, String>, JpaSpecificationExecutor<FakeDetailEntity> {

}