package nagarciah.laliga.demo.controller;

import nagarciah.laliga.demo.model.User;
import nagarciah.laliga.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	public String showRegister(Model model) {
		model.addAttribute("user", new User());
		return "login/register";
	}
	
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public String register(/*@ModelAttribute*/ User user, Model model){
		userService.add(user);
		model.addAttribute("result", "OK");
		return "login/register";
	}
}
