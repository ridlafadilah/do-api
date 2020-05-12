package com.dongkap.panic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.panic.entity.DeviceEntity;

public interface DeviceRepo extends JpaRepository<DeviceEntity, String>, JpaSpecificationExecutor<DeviceEntity> {

}