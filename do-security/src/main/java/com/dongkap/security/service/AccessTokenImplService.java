package com.dongkap.security.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dongkap.security.entity.UserEntity;

@Service("accessTokenService")
public class AccessTokenImplService {

	protected Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static final String GRANT_TYPE = "password";

	private static final String CLIENT_FIELDS_FOR_UPDATE = "resource_ids, scope, "
			+ "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
			+ "refresh_token_validity, additional_information, autoapprove";

	private static final String CLIENT_FIELDS = "client_secret, " + CLIENT_FIELDS_FOR_UPDATE;

	private static final String BASE_FIND_STATEMENT = "select client_id, " + CLIENT_FIELDS
			+ " from security.oauth_client_details";

	private static final String SELECT_CLIENT_DETAILS_SQL = BASE_FIND_STATEMENT + " where client_id = ?";

    @Autowired
    private DefaultTokenServices tokenServices;

    @Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("${do.client-id.web}")
	private String clientId;

	public OAuth2AccessToken grantAuthDefault(Authentication authentication) throws Exception {
        UserEntity userEntity = (UserEntity)authentication.getPrincipal();
		return grantAuthDefault(userEntity);
	}

	public OAuth2AccessToken grantAuthDefault(UserEntity userEntity) throws Exception {

        ClientDetails clientDetails;
		try {
			clientDetails = jdbcTemplate.queryForObject(SELECT_CLIENT_DETAILS_SQL, new ClientDetailsRowMapper(), clientId);
		}
		catch (EmptyResultDataAccessException e) {
			throw new NoSuchClientException("No client with requested id: " + clientId);
		}
        
        Map<String, String> authorizationParameters = new HashMap<String, String>();
        authorizationParameters.put("username", userEntity.getUsername());
        authorizationParameters.put("client_id", clientId);
        authorizationParameters.put("grant_type", GRANT_TYPE);
        
        OAuth2Request authorizationRequest = new OAuth2Request(
                authorizationParameters, clientId,
                userEntity.getAuthorities(), true, clientDetails.getScope(), clientDetails.getResourceIds(), null,
                null, null);     

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEntity, null, userEntity.getAuthorities());

        OAuth2Authentication oAuth2Authentication  = new OAuth2Authentication(
                authorizationRequest, authenticationToken);
        oAuth2Authentication.setAuthenticated(true);
        OAuth2AccessToken accessToken = tokenServices.createAccessToken(oAuth2Authentication);
		return accessToken;
	}
	
	private static class ClientDetailsRowMapper implements RowMapper<ClientDetails> {
		public ClientDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			BaseClientDetails details = new BaseClientDetails(rs.getString(1), rs.getString(3), rs.getString(4),
					rs.getString(5), rs.getString(7), rs.getString(6));
			details.setClientSecret(rs.getString(2));
			if (rs.getObject(8) != null) {
				details.setAccessTokenValiditySeconds(rs.getInt(8));
			}
			if (rs.getObject(9) != null) {
				details.setRefreshTokenValiditySeconds(rs.getInt(9));
			}
			String scopes = rs.getString(11);
			if (scopes != null) {
				details.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(scopes));
			}
			return details;
		}
	}

}
