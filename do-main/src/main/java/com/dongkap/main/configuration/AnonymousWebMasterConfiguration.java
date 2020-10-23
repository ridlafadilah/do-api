package com.dongkap.main.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dongkap.common.utils.AnonymousPrecedenceOrder;
import com.dongkap.common.utils.ResourceCode;

@Configuration
@Order(AnonymousPrecedenceOrder.MASTER)
public class AnonymousWebMasterConfiguration extends WebSecurityConfigurerAdapter {
	
	private static final String OPENAPI_PATH_MASTER_VIEW = "/oa/"+ResourceCode.MASTER.getResourceId()+"/vw/**";

	@Autowired
	private AuthorizationServerEndpointsConfiguration endpoints;

	@Autowired
	private ClientDetailsService clientDetailsService;

	@Autowired
	private List<AuthorizationServerConfigurer> configurers = Collections.emptyList();
	
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
		AuthorizationServerSecurityConfigurer configurer = new AuthorizationServerSecurityConfigurer();
		FrameworkEndpointHandlerMapping handlerMapping = endpoints.oauth2EndpointHandlerMapping();
		http.setSharedObject(FrameworkEndpointHandlerMapping.class, handlerMapping);
		configure(configurer);
		http.apply(configurer);

		if (!endpoints.getEndpointsConfigurer().isUserDetailsServiceOverride()) {
			UserDetailsService userDetailsService = http.getSharedObject(UserDetailsService.class);
			endpoints.getEndpointsConfigurer().userDetailsService(userDetailsService);
		}
		// @formatter:off
		http
	        .csrf()
		    	.disable()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
        	.authorizeRequests()
	        	.antMatchers(OPENAPI_PATH_MASTER_VIEW).authenticated()
        .and()
        	.requestMatchers()
            	.antMatchers(OPENAPI_PATH_MASTER_VIEW)
        .and()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        	.oauth2Login();
		// @formatter:on
		http.setSharedObject(ClientDetailsService.class, clientDetailsService);
    }

	protected void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
		for (AuthorizationServerConfigurer configurer : configurers) {
			configurer.configure(oauthServer);
		}
	}

}
