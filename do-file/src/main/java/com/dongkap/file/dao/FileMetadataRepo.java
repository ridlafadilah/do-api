package com.dongkap.file.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dongkap.file.entity.FileMetadataEntity;

public interface FileMetadataRepo extends JpaRepository<FileMetadataEntity, String>, JpaSpecificationExecutor<FileMetadataEntity> {

	FileMetadataEntity findByChecksum(String checksum);

	FileMetadataEntity findByLocation(String location);

	@Modifying
	@Query("DELETE FROM FileMetadataEntity f WHERE f.checksum = :checksum")
	void deleteByChecksum(@Param("checksum") String checksum);

	@Modifying
	@Query("DELETE FROM FileMetadataEntity f WHERE f.location = :location")
	void deleteByLocation(@Param("location") String location);
	
}