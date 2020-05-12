package com.dongkap.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Configuration
public class SpelConfiguration {

	@Bean
	public ExpressionParser getExpressionParser() {
		return new SpelExpressionParser();
	}
	
	@Bean
	public StandardEvaluationContext getEvalutionContext() {
		return new StandardEvaluationContext();
	}

}
