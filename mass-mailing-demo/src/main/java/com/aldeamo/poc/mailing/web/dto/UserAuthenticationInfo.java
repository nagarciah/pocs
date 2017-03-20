package com.aldeamo.poc.mailing.web.dto;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.aldeamo.poc.mailing.model.UserInfo;

/**
 * Clase utilizada por Spring Security para contener la información
 * adicional del usuario obtenida durante la autenticación.
 * 
 * @author nelson
 *
 */
public class UserAuthenticationInfo extends User {
	
	private static final long serialVersionUID = 6064789776312580298L;
	
	private UserInfo userInfo;

	public UserAuthenticationInfo(UserInfo userInfo) {
		super(	userInfo.getUsername(),
				userInfo.getPassword(),
				AuthorityUtils.createAuthorityList("TODO Rol"));
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo(){
		return this.userInfo;
	}
}