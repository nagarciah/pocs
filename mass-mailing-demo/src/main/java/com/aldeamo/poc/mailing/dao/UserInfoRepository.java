package com.aldeamo.poc.mailing.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aldeamo.poc.mailing.model.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

	public Optional<UserInfo> findByUsername(String username);
	public UserInfo findOneByCustomerId(String customerId);
	public UserInfo findOneBySenderToken(String senderToken); 
	
}
