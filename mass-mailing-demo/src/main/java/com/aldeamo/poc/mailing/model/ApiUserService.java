package com.aldeamo.poc.mailing.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aldeamo.poc.mailing.dao.UserInfoRepository;

@Service
public class ApiUserService {
	
	@Autowired
	UserInfoRepository userInfoRepository;

	public UserInfo findOneByCustomerId(String customerId) {
		return userInfoRepository.findOneByCustomerId(customerId);
	}
	
}
