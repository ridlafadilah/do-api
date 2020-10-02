package com.dongkap.file.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.feign.dto.file.FileMetadataDto;
import com.dongkap.file.service.FileGenericImplService;

@RestController
@RequestMapping("/api/file")
public class FileEvidenceController extends BaseControllerException {

	@Autowired
	private FileGenericImplService fileGenericService;
	
    @Value("${do.file.path.evidence}")
    protected String path;

	@RequestMapping(value = "/vw/get/evidence/v.1/{checksum}/{user}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)	
	public ResponseEntity<Resource> downloadFileEvidence(Authentication authentication,
			@PathVariable(required = true) String checksum,
			@PathVariable(required = true) String user,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		String path = this.path.concat(user);
		return new ResponseEntity<Resource>(this.fileGenericService.getFile(checksum, path), HttpStatus.OK);
	}

	@RequestMapping(value = "/vw/get/evidence-info/v.1/{checksum}", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)	
	public ResponseEntity<FileMetadataDto> getFileMetadata(Authentication authentication,
			@PathVariable(required = true) String checksum,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		return new ResponseEntity<FileMetadataDto>(this.fileGenericService.getFileInfo(checksum), HttpStatus.OK);
	}
	
}
