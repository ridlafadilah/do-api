package com.dongkap.main.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpointHandlerMapping;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dongkap.common.utils.AnonymousPrecedenceOrder;
import com.dongkap.common.utils.ResourceCode;
import com.dongkap.security.configuration.HttpCookieOAuth2AuthorizationRequestRepository;
import com.dongkap.security.configuration.OAuth2AuthenticationFailureHandler;
import com.dongkap.security.configuration.OAuth2AuthenticationSuccessHandler;
import com.dongkap.security.service.GrantOAuth2UserImplService;

@Configuration
@Order(AnonymousPrecedenceOrder.SECURITY)
@Import({ ClientDetailsServiceConfiguration.class, AuthorizationServerEndpointsConfiguration.class })
public class AnonymousWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final static String OAUTH2_PATH = "/oauth2/**";
	private final static String OPENAPI_PATH_SECURITY_VIEW = "/oa/"+ResourceCode.SECURITY.getResourceId()+"/vw/**";

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

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Autowired
    private OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    @Autowired
    private GrantOAuth2UserImplService grantOAuth2UserService;
	
	@Autowired
	public void configure(ClientDetailsServiceConfigurer clientDetails) throws Exception {
		for (AuthorizationServerConfigurer configurer : configurers) {
			configurer.configure(clientDetails);
		}
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
		AuthorizationServerSecurityConfigurer configurer = new AuthorizationServerSecurityConfigurer();
		FrameworkEndpointHandlerMapping handlerMapping = endpoints.oauth2EndpointHandlerMapping();
		http.setSharedObject(FrameworkEndpointHandlerMapping.class, handlerMapping);
		configure(configurer);
		http.apply(configurer);
		String tokenEndpointPath = handlerMapping.getServletPath("/oauth/token");
		String forceEndpointPath = handlerMapping.getServletPath("/oauth/force");
		String tokenKeyPath = handlerMapping.getServletPath("/oauth/token_key");
		String checkTokenPath = handlerMapping.getServletPath("/oauth/check_token");
		String extractTokenPath = handlerMapping.getServletPath("/oauth/extract-token");	
		String tokenVerifierPath = handlerMapping.getServletPath("/oauth/token-verifier");		
		String signupEndpointPath = handlerMapping.getServletPath("/oauth/signup");
		String forgotPasswordEndpointPath = handlerMapping.getServletPath("/oauth/forgot-password");
		String requestForgotPasswordEndpointPath = handlerMapping.getServletPath("/oauth/request-forgot-password");
		String verificationForgotPasswordEndpointPath = handlerMapping.getServletPath("/oauth/verification-forgot-password");
		String checkUserPath = handlerMapping.getServletPath("/oauth/check-user");

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
            	.antMatchers(OAUTH2_PATH).permitAll()
	        	.antMatchers(tokenEndpointPath).fullyAuthenticated()
            	.antMatchers(tokenKeyPath).access(configurer.getTokenKeyAccess())
            	.antMatchers(checkTokenPath).access(configurer.getCheckTokenAccess())
            	.antMatchers(extractTokenPath).access(configurer.getCheckTokenAccess())
            	.antMatchers(tokenVerifierPath).access(configurer.getCheckTokenAccess())

	        	.antMatchers(forceEndpointPath).authenticated()
	        	.antMatchers(signupEndpointPath).authenticated()
	        	.antMatchers(forgotPasswordEndpointPath).authenticated()
	        	.antMatchers(requestForgotPasswordEndpointPath).authenticated()
	        	.antMatchers(verificationForgotPasswordEndpointPath).authenticated()
	        	.antMatchers(checkUserPath).authenticated()
	        	.antMatchers(OPENAPI_PATH_SECURITY_VIEW).authenticated()
        .and()
        	.requestMatchers()
            	.antMatchers(
            			tokenEndpointPath, tokenKeyPath,
            			checkTokenPath, extractTokenPath,
            			forceEndpointPath, signupEndpointPath, 
            			forgotPasswordEndpointPath, requestForgotPasswordEndpointPath,
            			verificationForgotPasswordEndpointPath,
            			checkUserPath, tokenVerifierPath,
            			OAUTH2_PATH,
            			OPENAPI_PATH_SECURITY_VIEW)
        .and()
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        	.oauth2Login()
	            .authorizationEndpoint()
		            .baseUri("/oauth2/authorize")
		            .authorizationRequestRepository(cookieAuthorizationRequestRepository())
		            .and()
		        .redirectionEndpoint()
		            .baseUri("/oauth2/callback/*")
		            .and()
		        .userInfoEndpoint()
		            .userService(grantOAuth2UserService)
		            .and()
            	.successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);
		// @formatter:on
		http.setSharedObject(ClientDetailsService.class, clientDetailsService);
    }

	protected void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.accessDeniedHandler(accessDeniedHandler).authenticationEntryPoint(authenticationEntryPoint);
		for (AuthorizationServerConfigurer configurer : configurers) {
			configurer.configure(oauthServer);
		}
	}

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    // @Bean
    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
        return new HttpSessionOAuth2AuthorizationRequestRepository();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
        DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
        return accessTokenResponseClient;
    }

}
