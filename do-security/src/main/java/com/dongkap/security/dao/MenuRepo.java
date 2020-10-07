package com.dongkap.security.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongkap.security.entity.MenuEntity;

public interface MenuRepo extends JpaRepository<MenuEntity, String>, JpaSpecificationExecutor<MenuEntity> {
	
	@Query("SELECT m FROM MenuEntity m JOIN FETCH m.function f WHERE f.role.authority = :role ORDER BY m.orderingStr ASC")
	List<MenuEntity> loadAllMenuByRole(@Param("role") String role);
	
	@Query("SELECT m FROM MenuEntity m JOIN FETCH m.function f WHERE m.type = :type AND f.role.authority = :role ORDER BY m.orderingStr ASC")
	List<MenuEntity> loadTypeMenuByRole(@Param("role") String role, @Param("type") String type);

	List<MenuEntity> findByType(String type);

	@Query("SELECT m FROM MenuEntity m JOIN FETCH m.menuI18n ml WHERE m.type = :type AND ml.locale = :locale AND m.level =:level ORDER BY m.orderingStr ASC")
	List<MenuEntity> loadRootMenuByTypeLevelI18n(@Param("type") String type, @Param("locale") String locale, @Param("level") Integer level);
	
	@Query("SELECT m FROM MenuEntity m JOIN FETCH m.function f JOIN FETCH m.menuI18n ml WHERE f.role.authority = :role AND ml.locale = :locale ORDER BY m.orderingStr ASC")
	List<MenuEntity> loadAllMenuByRoleI18n(@Param("role") String role, @Param("locale") String locale);
	
	@Query("SELECT m FROM MenuEntity m JOIN FETCH m.function f JOIN FETCH m.menuI18n ml WHERE m.type = :type AND f.role.authority = :role AND ml.locale = :locale ORDER BY m.orderingStr ASC")
	List<MenuEntity> loadTypeMenuByRoleI18n(@Param("role") String role, @Param("locale") String locale, @Param("type") String type);
	
}