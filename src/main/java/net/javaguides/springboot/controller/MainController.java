package net.javaguides.springboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;

@Controller
public class MainController {

	@Autowired
	public UserService userService;

//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}

	@GetMapping("/")
	public String home(Model model) {
		List<User> listUsers = userService.getAllUsers();
		model.addAttribute("listUsers", listUsers);
		return "index";
		//return findPaginated(1, "firstName", "asc", model);
	}

}
