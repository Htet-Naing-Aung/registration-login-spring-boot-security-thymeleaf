//package net.javaguides.springboot.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import net.javaguides.springboot.model.Post;
//import net.javaguides.springboot.repository.PostRepository;
//
//@Service
//public class PostServiceImpl implements PostService{
//
//	@Autowired
//	private PostRepository postRepository;
//
//	@Override
//	public Post save(Post post) {
//		return postRepository.save(post);
//	}
//
//	@Override
//	public List<Post> getAllPost() {
//		return postRepository.findAll();
//	}
//
//	@Override
//	public Optional<Post> getPostImageById(Long id) {
//		return postRepository.findById(id);
//	}
//
//}
