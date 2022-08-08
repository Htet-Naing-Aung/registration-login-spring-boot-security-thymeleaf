package net.javaguides.springboot.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Value("${uploadDir}")
	private String uploadFolder;

	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration";
	}
	
	/*
	@PostMapping
	public String registerUserAccount(@Valid User validUser, BindingResult bindingResult, Model model, HttpServletRequest request,
				@RequestParam("image") MultipartFile file) throws IOException {
		boolean thereAreErrors = bindingResult.hasErrors();
		if (thereAreErrors) {
			model.addAttribute("user", user);
			return "registration";
		}

		// to show when created new user DateTimeFormatter dateTimeFormatter =
		DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String createdDate = dateTimeFormatter.format(now);
		user.setCreatedDate(createdDate);

		// to show who created new user Authentication authentication =
		SecurityContextHolder.getContext().getAuthentication();
		String loginEmail = authentication.getName();
		User getUserByEmail = userService.getUserByEmail(loginEmail);
		String createdUserName = getUserByEmail.getFirstName() + " " + getUserByEmail.getLastName();
		user.setCreatedBy(createdUserName);

		userService.save(user);

		return "redirect:/registration?success";

	}
	*/

	// Registration with profile//
	@PostMapping
	public String registerUserAccount(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email,
			@RequestParam("password") String password, Model model, HttpServletRequest request,
			final @RequestParam("image") MultipartFile file) {
		
		try {
			// String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				return ("Sorry! Filename contains invalid path sequence " + fileName + ", " + HttpStatus.BAD_REQUEST);
			}
			String[] firstNames = firstName.split(",");
			String[] lastNames = lastName.split(",");
			String[] emails = email.split(",");
			log.info("First Name: " + firstNames[0] + " " + filePath);
			log.info("Last Name: " + lastNames[0]);
			log.info("Email: " + emails);
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setPassword(password);
			user.setImage(imageData);

			// to show when created new user
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String createdDate = dateTimeFormatter.format(now);
			user.setCreatedDate(createdDate);

			// to show who created new user
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String loginEmail = authentication.getName();
			User getUserByEmail = userService.getUserByEmail(loginEmail);
			String createdUserName = getUserByEmail.getFirstName() + " " + getUserByEmail.getLastName();
			user.setCreatedBy(createdUserName);

			userService.save(user);

			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
			return ("redirect:/registration?success");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			return ("So Bad" + HttpStatus.BAD_REQUEST);
		}
	}
	// Registration with profile//

	@GetMapping("/user/editForm/{id}")
	public String editUserForm(@PathVariable(value = "id") long id, Model model) {
		User user = userService.getUserByID(id);
		model.addAttribute("user", user);
		return "edit_user";
	}

	@PostMapping("/edit/user/{id}")
	public String updateUser(@PathVariable(value = "id") long id, @Valid User user, BindingResult bindingResult,
			Model model) {

		boolean thereAreErrors = bindingResult.hasErrors();
		if (thereAreErrors) {
			model.addAttribute("user", user);
			return "edit_user";
		}
		// to show who updated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loginEmail = authentication.getName();
		User getUserByEmail = userService.getUserByEmail(loginEmail);
		String updatedUserName = getUserByEmail.getFirstName() + " " + getUserByEmail.getLastName();

		// to show when updated user
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String updatedDate = dateTimeFormatter.format(now);

		User existingUser = userService.getUserByID(user.getId());
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setUpdatedBy(updatedUserName);
		existingUser.setUpdatedDate(updatedDate);

		// save updated user object
		userService.updateUser(existingUser);

		// get updated all users to show again in index form
		List<User> listUsers = userService.getAllUsers();
		model.addAttribute("listUsers", listUsers);
		return "redirect:/?success";
	}

	@GetMapping("/delete/user/{id}")
	public String deleteUser(@PathVariable(value = "id") long id) {
		this.userService.deleteUserById(id);
		return "redirect:/";
	}

}
