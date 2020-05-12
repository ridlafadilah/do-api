package com.dongkap.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dongkap.security.dao.FunctionRepo;

@Service("functionService")
public class FunctionImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("functionRepo")
	private FunctionRepo functionRepo;
	
}