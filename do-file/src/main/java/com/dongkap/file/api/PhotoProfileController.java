package com.dongkap.file.api;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dongkap.common.aspect.ResponseSuccess;
import com.dongkap.common.exceptions.BaseControllerException;
import com.dongkap.common.http.ApiBaseResponse;
import com.dongkap.common.utils.SuccessCode;
import com.dongkap.file.service.FileGenericImplService;
import com.dongkap.file.service.PhotoProfileImplService;

@RestController
@RequestMapping("/api/file")
public class PhotoProfileController extends BaseControllerException {

	@Autowired
	private PhotoProfileImplService photoProfileService;

	@Autowired
	private FileGenericImplService fileGenericService;
	
    @Value("${do.file.path.image.profile}")
    protected String path;

    @ResponseSuccess(SuccessCode.OK_SCR005)
	@RequestMapping(value = "/trx/post/photo-profile/v.1", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<ApiBaseResponse> putPhotoProfile(Authentication authentication,
			@RequestPart @Valid MultipartFile photo,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		String username = authentication.getName();
		String path = this.path.concat(username);
		ApiBaseResponse res = this.photoProfileService.putFile(path, photo.getOriginalFilename(), photo.getBytes(), authentication, locale);
		return new ResponseEntity<ApiBaseResponse>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vw/get/photo-profile/v.1/{checksum}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)	
	public ResponseEntity<Resource> downloadPhotoProfile(Authentication authentication,
			@PathVariable(required = true) String checksum,
			@RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String locale) throws Exception {
		String path = this.path.concat(authentication.getName());
		return new ResponseEntity<Resource>(this.fileGenericService.getFile(checksum, path), HttpStatus.OK);
	}
	
}
