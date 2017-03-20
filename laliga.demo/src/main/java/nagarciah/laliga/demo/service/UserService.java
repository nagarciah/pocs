package nagarciah.laliga.demo.service;

import nagarciah.laliga.demo.dao.UserDAO;
import nagarciah.laliga.demo.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private static Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	protected UserDAO userDAO;

	public void add(User user) {
		this.userDAO.insert(user);
		log.info("Usuario creado: " + user.getFirstname());
	}
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
