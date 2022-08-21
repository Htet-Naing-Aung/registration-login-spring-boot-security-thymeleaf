//package net.javaguides.springboot.controller;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Optional;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import net.javaguides.springboot.model.Post;
//import net.javaguides.springboot.model.User;
//import net.javaguides.springboot.service.PostService;
//import net.javaguides.springboot.service.UserService;
//
//@Controller
//@RequestMapping("/posting")
//public class PostController {
//
//	@Autowired
//	private PostService postService;
//
//	@Autowired
//	private UserService userService;
//
//	@Value("${uploadDir}")
//	private String uploadFolder;
//
//	private final Logger log = LoggerFactory.getLogger(this.getClass());
//
//	@GetMapping
//	public String showPostingForm(Model model) {
//		model.addAttribute("post", new Post());
//		return "posting";
//	}
//
//	@PostMapping
//	public String createPost(@RequestParam("status") String status, Model model, HttpServletRequest request,
//			final @RequestParam("image") MultipartFile file) {
//		try {
//			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
//			log.info("uploadDirectory:: " + uploadDirectory);
//			String fileName = file.getOriginalFilename();
//			String filePath = Paths.get(uploadDirectory, fileName).toString();
//			log.info("FileName: " + fileName);
//			if (fileName == null || fileName.contains("..")) {
//				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
//				return ("Sorry! Filename contains invalid path sequence " + fileName + ", " + HttpStatus.BAD_REQUEST);
//			}
//			try {
//				File dir = new File(uploadDirectory);
//				if (!dir.exists()) {
//					log.info("Folder Created");
//					dir.mkdirs();
//				}
//				// Save the file locally
//				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
//				stream.write(file.getBytes());
//				stream.close();
//			} catch (Exception e) {
//				log.info("in catch");
//				e.printStackTrace();
//			}
//			byte[] imageData = file.getBytes();
//
//			Post post = new Post();
//			post.setStatus(status);
//			post.setImage(imageData);
//
//			// to set user ID who is posted
//			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//			String loginEmail = authentication.getName();
//			User getUserByEmail = userService.getUserByEmail(loginEmail);
//			post.setUser(getUserByEmail);
//
//			// to show when created new user
//			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//			LocalDateTime now = LocalDateTime.now();
//			String createdDate = dateTimeFormatter.format(now);
//			post.setCreatedDate(createdDate);
//
//			postService.save(post);
//
//			log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
//			return ("redirect:/posting?success");
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.info("Exception: " + e);
//			return ("So Bad" + HttpStatus.BAD_REQUEST);
//		}
//	}
//
//	@GetMapping("/post/allPost")
//	public String displayAllPost(Model model) {
//		List<Post> allPost = postService.getAllPost();
//		model.addAttribute("allPost", allPost);
//		return "all_post";
//	}
//
//	@GetMapping("/post_image/display/{id}")
//	@ResponseBody
//	public void showPostImage(@PathVariable("id")Long id, HttpServletResponse response,
//			Optional<Post> post) throws ServletException, IOException {
//		log.info("Id :: " + id);
//		post = postService.getPostImageById(id);
//		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
//		response.getOutputStream().write(post.get().getImage());
//		response.getOutputStream().close();
//	}
//
//}
