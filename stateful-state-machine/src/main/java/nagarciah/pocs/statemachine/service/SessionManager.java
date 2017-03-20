package nagarciah.pocs.statemachine.service;

import org.springframework.stereotype.Service;

@Service
public class SessionManager {
	
	public String getSession(String id){
		return "Hola " + id;
	}

}
