package com.toystore.ecomm.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.authentication.model.TenantInfo;
import com.toystore.ecomm.authentication.repository.TenantRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private TenantRepository tenantRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TenantInfo tenantInfo = tenantRepository.findByTenantUsername(userName);
        
        if(null == tenantInfo){
            throw new UsernameNotFoundException("Invalid user name or password.");
        }
        return new org.springframework.security.core.userdetails.User(tenantInfo.getTenantUsername(), tenantInfo.getTenantPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ADMIN"));
    }
}
