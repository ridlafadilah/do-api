package com.dongkap.security.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.security.social.OAuth2UserInfoDto;
import com.dongkap.security.common.SocialProvider;
import com.dongkap.security.dao.CorporateRepo;
import com.dongkap.security.dao.RoleRepo;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.ContactUserEntity;
import com.dongkap.security.entity.CorporateEntity;
import com.dongkap.security.entity.RoleEntity;
import com.dongkap.security.entity.SettingsEntity;
import com.dongkap.security.entity.UserEntity;
import com.dongkap.security.exception.OAuth2AuthenticationProcessingException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

@Service("tokenVerifierService")
public class TokenVerifierImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private CorporateRepo corporateRepo;
	
	@Value("${do.corporate-id.default}")
	private String corporateId;

    @Autowired
    private AccessTokenImplService accessTokenService;

	private static final String ROLE_END = "ROLE_END";
	
	private static final HttpTransport transport = new NetHttpTransport();
	private static final JacksonFactory jsonFactory = new JacksonFactory();

	public OAuth2AccessToken tokenVerifier(String token, String provider, String clientIdProvider) throws Exception {
		OAuth2UserInfoDto oAuth2UserInfo = null;
		if (provider.equalsIgnoreCase(SocialProvider.GOOGLE.getProviderType())) {
			provider = SocialProvider.GOOGLE.getProviderType();
			oAuth2UserInfo = tokenVerifierGoogle(token, clientIdProvider);
		} else {
			throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + provider + " is not supportd yet.");
		}
		if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<UserEntity> userEntityOptional = this.userRepo.findByEmail(oAuth2UserInfo.getEmail());
        UserEntity userEntity;
        if(userEntityOptional.isPresent()) {
        	userEntity = userEntityOptional.get();
            if(!userEntity.getProvider().equals(provider)) {
                throw new OAuth2AuthenticationProcessingException(ErrorCode.ERR_SYS0401.name());
            }
            if(!userEntity.isEnabled()) {
            	throw new OAuth2AuthenticationProcessingException(ErrorCode.ERR_SYS0411.name());
            } else if(!userEntity.isAccountNonExpired()) {
            	throw new OAuth2AuthenticationProcessingException(ErrorCode.ERR_SYS0412.name());            	
            } else if(!userEntity.isAccountNonLocked()) {
            	throw new OAuth2AuthenticationProcessingException(ErrorCode.ERR_SYS0413.name());            	
            } else if(!userEntity.isCredentialsNonExpired()) {
            	throw new OAuth2AuthenticationProcessingException(ErrorCode.ERR_SYS0414.name());            	
            } else
            	userEntity = doUpdateUser(userEntity, oAuth2UserInfo);
        } else {
        	userEntity = doRegisterUser(provider, oAuth2UserInfo);
        }
        userEntity.getAttributes().putAll(oAuth2UserInfo.getAttributes());

        return this.accessTokenService.grantAuthDefault(userEntity);
    }
	
	private OAuth2UserInfoDto tokenVerifierGoogle(String token, String clientIdProvider) throws Exception {
		OAuth2UserInfoDto oAuth2UserInfo = null;
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
			    .setAudience(Collections.singletonList(clientIdProvider))
			    .build();
		try {
			GoogleIdToken idToken = verifier.verify(token);
			if (idToken == null) {
				throw new InvalidTokenException("Token was not recognised");
			}
			oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(SocialProvider.GOOGLE.getProviderType(), idToken.getPayload());
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e){
			e.printStackTrace();
	    }
        return oAuth2UserInfo;
    }

    private UserEntity doRegisterUser(String provider, OAuth2UserInfoDto oAuth2UserInfo) {
    	UserEntity userEntity = new UserEntity();
        userEntity.setUsername(oAuth2UserInfo.getId());
        userEntity.setPassword("N/A");
        userEntity.setEmail(oAuth2UserInfo.getEmail());
        userEntity.setProvider(provider);
        userEntity.setAuthorityDefault(ROLE_END);
        CorporateEntity corporate = this.corporateRepo.findByCorporateId(this.corporateId);
        userEntity.getCorporates().add(corporate);
		RoleEntity role = this.roleRepo.findByAuthority(ROLE_END);
        userEntity.getRoles().add(role);
        ContactUserEntity contactUserEntity = new ContactUserEntity();
        contactUserEntity.setName(oAuth2UserInfo.getName());
        contactUserEntity.setImage(oAuth2UserInfo.getImageUrl());
        contactUserEntity.setUser(userEntity);
        userEntity.setContactUser(contactUserEntity);
		SettingsEntity settingsEntity = new SettingsEntity();
		settingsEntity.setUser(userEntity);
		userEntity.setSettings(settingsEntity);
        return this.userRepo.saveAndFlush(userEntity);
    }

    private UserEntity doUpdateUser(UserEntity userEntity, OAuth2UserInfoDto oAuth2UserInfo) {
    	if(userEntity.getUsername() == null)
    		userEntity.setUsername(oAuth2UserInfo.getId());
    	if(userEntity.getContactUser().getName() == null)
    		userEntity.getContactUser().setName(oAuth2UserInfo.getName());
    	if(userEntity.getContactUser().getImage() == null)
    		userEntity.getContactUser().setImage(oAuth2UserInfo.getImageUrl());
        return this.userRepo.saveAndFlush(userEntity);
    }

}
