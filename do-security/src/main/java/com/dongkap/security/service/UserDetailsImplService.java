package com.dongkap.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.UserEntity;

@Service("userDetailsService")
public class UserDetailsImplService implements UserDetailsService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.loadByUsername(username.toLowerCase());
		if (user == null) throw new UsernameNotFoundException("User '" + username + "' not found.");
		return user;
	}

}
