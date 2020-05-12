package com.dongkap.file.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.feign.dto.file.FileMetadataDto;
import com.dongkap.feign.service.ProfileService;
import com.dongkap.file.dao.FileMetadataRepo;
import com.dongkap.file.entity.FileMetadataEntity;

@Service("photoProfileService")
public class PhotoProfileImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("fileMetadataRepo")
	private FileMetadataRepo fileMetadataRepo;

	@Autowired
	private FileGenericImplService fileGenericService;

	@Autowired
	private ProfileService profileService;

	@Transactional
	public ApiBaseResponse putFile(String filePath, String filename, byte[] fileContent, Authentication authentication, String locale) throws Exception {
		FileMetadataEntity fileExist = fileMetadataRepo.findByLocation(filePath);
		FileMetadataDto fileMetadataDto = null;
		String checksum = null;
		try {
			fileMetadataDto = fileGenericService.putFile(filePath, filename, fileContent);
			checksum = fileMetadataDto.getChecksum();
		} catch (DataIntegrityViolationException e) {
			checksum = fileExist.getChecksum();
			LOGGER.warn(e.getMessage());
		}
		Map<String, String> url = new HashMap<String, String>();
		url.put("url", checksum);
		this.profileService.doUpdatePhoto(url, authentication, locale);
		
		if (fileExist != null) {
			if (!fileExist.getChecksum().equals(checksum)) {
				fileMetadataRepo.deleteByChecksum(fileExist.getChecksum());
			    File currentFile = new File(filePath, fileExist.getChecksum());
			    currentFile.delete();
			}
		}
		ApiBaseResponse response = new ApiBaseResponse();
		response.getRespStatusMessage().put("checksum", checksum);
		return response;
	}
	
}