//package net.javaguides.springboot.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import net.javaguides.springboot.model.User;
//import net.javaguides.springboot.service.UserService;
//
//@Controller
//public class UserSearchController {
//	@Autowired
//	private UserService userService;
//
//	@RequestMapping(path = {"/","/search"})
//	 public String home(User user, Model model, String keyword) {
//	  if(keyword!=null) {
//		  List<User> listUsers = userService.getByKeyword(keyword);
//		  model.addAttribute("listUsers", listUsers);
//	  }else {
//		  List<User> listUsers = userService.getAllUsers();
//		  model.addAttribute("listUsers", listUsers);
//	  }
//	  return "searchUser";
//	 }
//}
