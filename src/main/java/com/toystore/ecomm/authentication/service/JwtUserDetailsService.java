package com.toystore.ecomm.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toystore.ecomm.ptms.daorepo.model.TenantInfo;
import com.toystore.ecomm.ptms.daorepo.repository.TenantRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private TenantRepository tenantRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        TenantInfo tenantInfo = tenantRepository.findByTenantUsername(userName).get(0);
        
        if(null == tenantInfo){
            throw new UsernameNotFoundException("Invalid user name or password.");
        }
        
        System.out.println("Current logged user ROLE_" + tenantInfo.getTenantRoleInfo().getRoleName());
        return new org.springframework.security.core.userdetails.User(tenantInfo.getTenantUsername(), tenantInfo.getTenantPassword(), getAuthority((tenantInfo.getTenantRoleInfo().getRoleName()).trim()));
    }

    private List<SimpleGrantedAuthority> getAuthority(String roleName) {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_" + roleName));
    }
}
