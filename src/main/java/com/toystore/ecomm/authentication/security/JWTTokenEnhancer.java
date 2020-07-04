package com.toystore.ecomm.authentication.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.toystore.ecomm.authentication.model.TenantInfo;
import com.toystore.ecomm.authentication.repository.TenantRepository;

import java.util.HashMap;
import java.util.Map;

public class JWTTokenEnhancer implements TokenEnhancer {
	@Autowired
	private TenantRepository tenantRepository;
	
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	String userName = ((User)authentication.getPrincipal()).getUsername();
    	TenantInfo tenantInfo = tenantRepository.findByTenantUsername(userName);
    	
        Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("db_url", tenantInfo.getTenantDBInfo().getTenantDBUrl());
        additionalInfo.put("db_name", tenantInfo.getTenantDBInfo().getTenantDBName());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        
        return accessToken;
    }
}
