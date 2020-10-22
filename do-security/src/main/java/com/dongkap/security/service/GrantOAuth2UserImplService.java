package com.dongkap.security.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dongkap.common.utils.AuthorizationProvider;
import com.dongkap.common.utils.ErrorCode;
import com.dongkap.feign.dto.security.social.OAuth2UserInfoDto;
import com.dongkap.security.dao.CorporateRepo;
import com.dongkap.security.dao.RoleRepo;
import com.dongkap.security.dao.UserRepo;
import com.dongkap.security.entity.ContactUserEntity;
import com.dongkap.security.entity.CorporateEntity;
import com.dongkap.security.entity.RoleEntity;
import com.dongkap.security.entity.SettingsEntity;
import com.dongkap.security.entity.UserEntity;
import com.dongkap.security.exception.OAuth2AuthenticationProcessingException;

@Service("grantOAuth2UserService")
public class GrantOAuth2UserImplService extends DefaultOAuth2UserService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static final String ROLE_END = "ROLE_END";

    @Autowired
    private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private CorporateRepo corporateRepo;
	
	@Value("${do.corporate-id.default}")
	private String corporateId;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return doProcessOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

	private OAuth2User doProcessOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfoDto oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<UserEntity> userEntityOptional = this.userRepo.findByEmail(oAuth2UserInfo.getEmail());
        UserEntity userEntity;
        if(userEntityOptional.isPresent()) {
        	userEntity = userEntityOptional.get();
            if(!userEntity.getProvider().equals(AuthorizationProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()).toString())) {
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
        	userEntity = doRegisterUser(oAuth2UserRequest, oAuth2UserInfo);
        }
        userEntity.getAttributes().putAll(oAuth2User.getAttributes());

        return userEntity;
    }

    private UserEntity doRegisterUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfoDto oAuth2UserInfo) {
    	UserEntity userEntity = new UserEntity();
        userEntity.setUsername(oAuth2UserInfo.getId());
        userEntity.setPassword("N/A");
        userEntity.setEmail(oAuth2UserInfo.getEmail());
        userEntity.setProvider(AuthorizationProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()).toString());
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
    	if(userEntity.getContactUser().getName() == null)
    		userEntity.getContactUser().setName(oAuth2UserInfo.getName());
    	if(userEntity.getContactUser().getImage() == null)
    		userEntity.getContactUser().setImage(oAuth2UserInfo.getImageUrl());
        return this.userRepo.saveAndFlush(userEntity);
    }

}
