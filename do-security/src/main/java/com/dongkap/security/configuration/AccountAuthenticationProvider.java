package com.dongkap.security.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dongkap.security.service.UserDetailsImplService;

@Component
public class AccountAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDetailsImplService userDetailsService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
        if (token.getCredentials() == null || userDetails.getPassword() == null) {
            throw new BadCredentialsException("Credentials may not be null.");
        }

        if (!passwordEncoder.matches((String) token.getCredentials(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials.");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token) throws AuthenticationException {
    	UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return userDetails;
    }

}
