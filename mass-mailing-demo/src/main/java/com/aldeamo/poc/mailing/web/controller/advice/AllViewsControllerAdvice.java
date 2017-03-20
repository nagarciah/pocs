package com.aldeamo.poc.mailing.web.controller.advice;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.aldeamo.poc.mailing.model.UserInfo;
import com.aldeamo.poc.mailing.web.dto.UserAuthenticationInfo;

/**
 * Interceptor para todos las vistas y controladores
 * 
 * @author nelson
 */
@ControllerAdvice
public class AllViewsControllerAdvice {

	/**
	 * Obtiene la información del usuario que se consultó durante la
	 * autenticación y la asigna también a un bean de sesion (UserSession) para
	 * que pueda ser consultada y modificada con facilidad
	 * 
	 * El bean de sesión también quedará disponible para todas las vistas usando
	 * ${userInfo} en lugar de ${session.atributo}
	 * 
	 * @param authentication
	 * @return
	 */
	@ModelAttribute("userInfo")
	public UserInfo getUserInfo(Authentication authentication, HttpSession session) {
		UserInfo userInfo = null;

		if (session.getAttribute("userInfo") != null) {
			userInfo = (UserInfo) session.getAttribute("userInfo");
		} else if (authentication != null) {
			UserAuthenticationInfo principal = (UserAuthenticationInfo) authentication.getPrincipal();
			userInfo = principal.getUserInfo();
			session.setAttribute("userInfo", userInfo);
		}

		return userInfo;
	}
}
