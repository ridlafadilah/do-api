package com.dongkap.panic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.panic.entity.LocationEntity;
import com.dongkap.panic.entity.Point;

public interface LocationRepo extends JpaRepository<LocationEntity, String>, JpaSpecificationExecutor<LocationEntity> {

	LocationEntity findByCoordinate(Point coordinate);

}