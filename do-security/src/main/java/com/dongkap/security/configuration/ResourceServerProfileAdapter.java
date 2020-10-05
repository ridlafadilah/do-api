package com.dongkap.security.configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dongkap.common.utils.ResourceCode;

public class ResourceServerProfileAdapter extends ResourceServerConfigurerAdapter {

    private TokenStore tokenStore;
    private AccessDeniedHandler accessDeniedHandler;
    private AuthenticationEntryPoint authenticationEntryPoint;
    
    private String resourceId = ResourceCode.PROFILE.getResourceId();
    
    public ResourceServerProfileAdapter() {}
    public ResourceServerProfileAdapter(TokenStore tokenStore, AccessDeniedHandler accessDeniedHandler, AuthenticationEntryPoint authenticationEntryPoint) {
		this.tokenStore = tokenStore;
		this.accessDeniedHandler = accessDeniedHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
	}

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // @formatter:off
        resources
        	.resourceId(resourceId)
        	.tokenStore(tokenStore)
        	.authenticationEntryPoint(authenticationEntryPoint)
        	.accessDeniedHandler(accessDeniedHandler);
        // @formatter:on
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .csrf().disable()
        .requestMatchers()
        	.antMatchers("/api/"				+resourceId+ "/**").and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET,"/api/" 	+resourceId+ "/vw/get/**")
    		.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.GET,"/api/" 	+resourceId+ "/vw/param/**")
    		.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/vw/post/**")
        	.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/vw/param/**")
        	.access("#oauth2.hasScope('read')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/trx/add/**")
        	.access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/trx/post/**")
        	.access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/trx/delete/**")
        	.access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.PUT,"/api/"		+resourceId+ "/trx/put/**")
        	.access("#oauth2.hasScope('write')")
        .antMatchers(HttpMethod.GET,"/api/" 	+resourceId+ "/vw/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('ROLE_END'))")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/vw/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('ROLE_END'))")
        .antMatchers(HttpMethod.POST,"/api/"	+resourceId+ "/trx/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('ROLE_END'))")
        .antMatchers(HttpMethod.DELETE,"/api/"	+resourceId+ "/trx/auth/**")
        	.access("#oauth2.hasScope('trust') and not(hasRole('ROLE_END'))")
        .anyRequest().denyAll();
        // @formatter:on
    }
}
