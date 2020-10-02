package com.dongkap.file.service;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.exceptions.SystemErrorException;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.file.FileMetadataDto;
import com.dongkap.feign.service.FileGenericService;
import com.dongkap.file.dao.FileMetadataRepo;
import com.dongkap.file.entity.FileMetadataEntity;
import com.dongkap.file.utils.FileUtils;

@Service("fileGenericService")
public class FileGenericImplService implements FileGenericService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("fileMetadataRepo")
	private FileMetadataRepo fileMetadataRepo;

	@Autowired
	private FileUtils fileUtils;
	
    @Value("${do.file.path.tmp}")
    protected String filePath;

	@Transactional(noRollbackFor = { ConstraintViolationException.class }, propagation = Propagation.REQUIRES_NEW)
	public FileMetadataDto putFile(String filePath, String filename, byte[] fileContent) throws Exception {
		if (filePath.isEmpty()) filePath = this.filePath;
		FileMetadataDto fileMetadataDto = fileUtils.writeFile(filePath, filename, fileContent);
		FileMetadataEntity fileMetadata = new FileMetadataEntity();
		fileMetadata.setChecksum(fileMetadataDto.getChecksum());
		fileMetadata.setExtension(fileMetadataDto.getExtension());
		fileMetadata.setFileDate(fileMetadataDto.getFileDate());
		fileMetadata.setFileType(fileMetadataDto.getFileType());
		fileMetadata.setFullname(fileMetadataDto.getFullname());
		fileMetadata.setFullPath(fileMetadataDto.getFullPath());
		fileMetadata.setLocation(fileMetadataDto.getLocation());
		fileMetadata.setShortname(fileMetadataDto.getShortname());
		fileMetadata.setSize(fileMetadataDto.getSize());
		fileMetadataRepo.saveAndFlush(fileMetadata);
		return fileMetadataDto;
	}
	
	public FileMetadataDto getFileInfo(String checksum) throws Exception {
		FileMetadataEntity fileMetadata = fileMetadataRepo.findByChecksum(checksum);
		if(fileMetadata != null) {
			FileMetadataDto fileMetadataDto = new FileMetadataDto();
			fileMetadataDto.setChecksum(fileMetadata.getChecksum());
			fileMetadataDto.setExtension(fileMetadata.getExtension());
			fileMetadataDto.setFileDate(fileMetadata.getFileDate());
			fileMetadataDto.setFileType(fileMetadata.getFileType());
			fileMetadataDto.setFullname(fileMetadata.getFullname());
			fileMetadataDto.setFullPath(fileMetadata.getFullPath());
			fileMetadataDto.setLocation(fileMetadata.getLocation());
			fileMetadataDto.setShortname(fileMetadata.getShortname());
			fileMetadataDto.setSize(fileMetadata.getSize());
			return fileMetadataDto;			
		} else {
			throw new SystemErrorException(ErrorCode.ERR_SYS0404);
		}
	}
	
	public Resource getFile(String checksum, String path) throws Exception {
		try {
			Path file = Paths.get(path).resolve(checksum);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				LOGGER.error("Fail to load file {} or file does not exist", checksum);
				throw new SystemErrorException(ErrorCode.ERR_SYS0404);
			}
		} catch (MalformedURLException e) {
			LOGGER.error("Fail to load file {}", checksum);
			throw new SystemErrorException(ErrorCode.ERR_SYS0001);
		}
	}
	
	public void removeAllFiles(String path) throws Exception {
		try {
			String[] files = new File(path).list();
			for(String s: files){
			    File currentFile = new File(path,s);
			    currentFile.delete();
			}			
		} catch (NullPointerException e) {}
	}
	
}