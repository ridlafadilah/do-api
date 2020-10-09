package com.dongkap.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongkap.security.entity.FunctionEntity;

public interface FunctionRepo extends JpaRepository<FunctionEntity, String>, JpaSpecificationExecutor<FunctionEntity> {

	@Modifying
	@Query("DELETE FROM FunctionEntity f WHERE f.roleId = :roleId AND f.menuId IN"
			+ "(SELECT f.menuId FROM FunctionEntity f WHERE f.menu.type = :type)")
	void deleteFunctionRoleByType(@Param("roleId") String roleId, @Param("type") String type);

}