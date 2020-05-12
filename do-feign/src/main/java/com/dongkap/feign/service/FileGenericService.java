package com.dongkap.feign.service;

import org.springframework.core.io.Resource;

import com.dongkap.feign.dto.file.FileMetadataDto;

public interface FileGenericService {
	
	public FileMetadataDto putFile(String filePath, String filename, byte[] fileContent) throws Exception;
	
	public Resource getFile(String checksum, String path) throws Exception;
	
	public FileMetadataDto getFileInfo(String checksum) throws Exception;
	
	public void removeAllFiles(String path) throws Exception;

}
