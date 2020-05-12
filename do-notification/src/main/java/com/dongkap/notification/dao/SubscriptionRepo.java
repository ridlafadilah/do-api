package com.dongkap.notification.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongkap.notification.entity.SubscriptionEntity;

public interface SubscriptionRepo extends JpaRepository<SubscriptionEntity, String>, JpaSpecificationExecutor<SubscriptionEntity> {
	
	SubscriptionEntity findByEndpoint(String endpoint);
	
	List<SubscriptionEntity> findByUsername(String username);

}