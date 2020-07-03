package com.toystore.ecomm.authentication.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.toystore.ecomm.authentication.model.TenantInfo;

@Repository
public interface TenantRepository extends CrudRepository<TenantInfo, Integer> {
	//List<TenantInfo> findByTenantUsernameAndPassword(String tenantUsername, String tenantPassword);
	List<TenantInfo> findByTenantId(Integer tenantId);
	
	List<TenantInfo> findByTenantName(String tenantName);
	TenantInfo findByTenantUsername(String tenantUsername);
	List<TenantInfo> findByTenantEmail(String tenantEmail);
	List<TenantInfo> findByTenantVerified(String tenantVerified);
	List<TenantInfo> findByTenantVerificationCode(String tenantVerificationCode);
}