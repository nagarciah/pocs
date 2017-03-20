package com.aldeamo.poc.mailing.web.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aldeamo.poc.mailing.dao.UserInfoRepository;
import com.aldeamo.poc.mailing.model.UserInfo;

@Component
public class UserService {
	
	@Autowired
	private UserInfoRepository userRepository;

	// TODO Hacer la autenticación por API, no por DB, para centralizar gestión de usuarios
	public UserInfo findByUsername(Long id){
		
		UserInfo userInfo;
		
		userInfo = userRepository.findOne(id);
		
		if(userInfo == null){
			throw new UserInfoNotFoundException("No se encontró el usuario con id: " + id);
		}
		
		return userInfo;	
	}
	
	public Optional<UserInfo> getUserInfo(String username){
		Optional<UserInfo> retorno;
		retorno = userRepository.findByUsername(username);
		
		return retorno;
	}
	
	public Optional<UserInfo> saveUserInfo(UserInfo userInfo){
		Optional<UserInfo> retorno;
		retorno = Optional.of( userRepository.saveAndFlush(userInfo) );
		
		return retorno;
	}
	
	public Collection<UserInfo> findAll(){
		Collection<UserInfo> retorno;
		retorno = userRepository.findAll();
		
		return retorno;
	}
}