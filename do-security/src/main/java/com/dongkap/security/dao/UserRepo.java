package com.dongkap.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongkap.security.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

	@Query("SELECT u FROM UserEntity u JOIN FETCH u.roles r JOIN FETCH u.contactUser p JOIN FETCH u.settings s WHERE LOWER(u.username) = :user OR LOWER(u.email) = :user")
	UserEntity loadByUsername(@Param("user") String user);

	@Query("SELECT u FROM UserEntity u WHERE LOWER(u.username) = :user OR LOWER(u.email) = :user")
	UserEntity loadByUser(@Param("user") String user);

	@Query("SELECT u FROM UserEntity u WHERE LOWER(u.username) = :username OR LOWER(u.email) = :email")
	UserEntity loadByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

	UserEntity findByUsername(String username);

}