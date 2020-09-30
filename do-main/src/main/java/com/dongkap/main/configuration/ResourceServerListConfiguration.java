package com.dongkap.main.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.dongkap.file.configuration.ResourceServerFileAdapter;
import com.dongkap.general.configuration.ResourceServerGeneralAdapter;
import com.dongkap.master.configuration.ResourceServerMasterAdapter;
import com.dongkap.notification.configuration.ResourceServerNotificationAdapter;
import com.dongkap.security.configuration.ResourceServerProfileAdapter;
import com.dongkap.security.configuration.ResourceServerSecurityAdapter;

@Configuration
public class ResourceServerListConfiguration {
	
    @Autowired
    private TokenStore tokenStore;
	
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
	
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	protected ResourceServerConfiguration securityResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerSecurityAdapter(tokenStore, accessDeniedHandler, authenticationEntryPoint)));
		resource.setOrder(-13);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration profileResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerProfileAdapter(tokenStore, accessDeniedHandler, authenticationEntryPoint)));
		resource.setOrder(-14);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration masterResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerMasterAdapter(tokenStore, accessDeniedHandler, authenticationEntryPoint)));
		resource.setOrder(-15);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration notificationResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerNotificationAdapter(tokenStore, accessDeniedHandler, authenticationEntryPoint)));
		resource.setOrder(-17);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration generalResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerGeneralAdapter(tokenStore, accessDeniedHandler, authenticationEntryPoint)));
		resource.setOrder(-18);
		return resource;
	}

	@Bean
	protected ResourceServerConfiguration fileResources() {
		ResourceServerConfiguration resource = new ResourceServerConfiguration() {
			public void setConfigurers(List<ResourceServerConfigurer> configurers) {
				super.setConfigurers(configurers);
			}
		};
		resource.setConfigurers(Arrays.<ResourceServerConfigurer> asList(new ResourceServerFileAdapter(tokenStore, accessDeniedHandler, authenticationEntryPoint)));
		resource.setOrder(-19);
		return resource;
	}
	
}
