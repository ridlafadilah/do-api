package com.dongkap.feign.client;

import org.springframework.http.ResponseEntity;

import com.dongkap.common.exceptions.FeignAuthException;
import com.dongkap.common.exceptions.FeignThrowException;

public class FeignCommonService<T> {


	protected T recreate(ResponseEntity<T> response) throws Exception {
		switch (response.getStatusCode().value()) {
			case 200:
				return response.getBody();
			case 401:
			case 403:
			case 407:
				throw new FeignAuthException(response.getStatusCode());
			default:
				break;
		}
		throw new FeignThrowException(response.getBody(), response.getStatusCode());
	}
}
