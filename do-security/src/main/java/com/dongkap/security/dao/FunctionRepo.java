package com.dongkap.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongkap.security.entity.FunctionEntity;

public interface FunctionRepo extends JpaRepository<FunctionEntity, String>, JpaSpecificationExecutor<FunctionEntity> {

	@Modifying
	@Query("DELETE FROM FunctionEntity f WHERE f.roleId = :roleId AND f.menuId IN (:menuIds)")
	void deleteFunctionInMenus(@Param("roleId") String roleId, @Param("menuIds") List<String> menuIds);

}