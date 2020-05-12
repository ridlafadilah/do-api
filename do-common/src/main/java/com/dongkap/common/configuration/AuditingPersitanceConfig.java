package com.dongkap.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.dongkap.common.security.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingPersitanceConfig {

	@Bean
	public AuditorAware<String> auditorAware(){
		return new AuditorAwareImpl();
	}
}
