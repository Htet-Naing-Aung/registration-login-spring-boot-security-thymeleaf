package net.javaguides.springboot.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.javaguides.springboot.model.User;

public interface UserService extends UserDetailsService {
	
	User save(User user);
	
	List<User> getAllUsers();
	
	Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	User getUserByID(long id);
	
	User updateUser(User user);
	
	void deleteUserById(long id);
	
	public List<User> getByKeyword(String keyword);

	User getUserByEmail(String loginEmail);
	
}
