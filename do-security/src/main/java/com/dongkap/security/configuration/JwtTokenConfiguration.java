package com.dongkap.security.configuration;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import com.dongkap.common.utils.DateUtil;

@Configuration
public class JwtTokenConfiguration {
	
	@Value("${do.client-id.web}")
	private String clientIdWeb;
	
    @Value("${security.oauth2.resource.jwt.key-value}")
    private String jwtKey;
	
	@Value("${do.signature.public-key}")
	private String publicKey;
    
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        final JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				if (!accessToken.getAdditionalInformation().isEmpty()) {
					Map<String, Object> temp = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
					accessToken.getAdditionalInformation().clear();
					if(authentication.getOAuth2Request().getClientId().equals(clientIdWeb)) {
						accessToken.getAdditionalInformation().put("menus", temp.get("menus"));
						accessToken.getAdditionalInformation().put("extras", temp.get("extras"));
					}
					accessToken.getAdditionalInformation().put("server_date", DateUtil.DATE.format(new Date()));
					accessToken.getAdditionalInformation().put("authority", temp.get("authority"));
					accessToken.getAdditionalInformation().put("provider", temp.get("provider"));
					accessToken.getAdditionalInformation().put("xrkey", publicKey);
					OAuth2AccessToken newAccessToken = super.enhance(accessToken, authentication);
					newAccessToken.getAdditionalInformation().putAll(temp);
					return newAccessToken;
		        }
				return super.enhance(accessToken, authentication);
			}

			@Override
			public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
				OAuth2Authentication outh = super.extractAuthentication(map);
				outh.setDetails(map);
				return outh;
			}        
			
			
        };
        jwtAccessTokenConverter.setSigningKey(jwtKey);
        return jwtAccessTokenConverter;
    }
}
