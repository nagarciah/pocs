package nagarciah.laliga.demo.api;

import nagarciah.laliga.demo.model.User;
import nagarciah.laliga.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
public class UserAPI {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User query(@PathVariable("id") int id){
		//userService.getById(id);
		User u = new User();
		u.setFirstname("FName");
		return u;
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseStatus( HttpStatus.CREATED ) //Opcional
	public void create(@RequestBody User user){
		userService.add(user);
	}
}
