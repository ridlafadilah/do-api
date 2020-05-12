package com.dongkap.security.configuration;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dongkap.security.service.JdbcOauth2ClientDetailsService;
import com.dongkap.security.service.JdbcOauth2TokenStore;
import com.dongkap.security.service.UserImplService;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfiguration extends AuthorizationServerConfigurerAdapter{

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private UserImplService userDetailsService;
	
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    
    @Autowired
    private WebResponseExceptionTranslator<OAuth2Exception> exceptionTranslator;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {		
		super.configure(security);
		security.authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()").passwordEncoder(new BCryptPasswordEncoder(13));
	}

	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // @formatter:off
	    TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
	    tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), jwtAccessTokenConverter));
		
        endpoints
        		.exceptionTranslator(exceptionTranslator)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore())
                .setClientDetailsService(jdbcClientDetailService());
        // @formatter:on
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
    	clients.withClientDetails(jdbcClientDetailService());
        // @formatter:on
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new JdbcOauth2TokenStore(dataSource);
    }
    
    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new SecurityTokenEnhancer();
    }
    
    @Bean
    public JdbcClientDetailsService jdbcClientDetailService() {
    	return new JdbcOauth2ClientDetailsService(dataSource);
    }

}
