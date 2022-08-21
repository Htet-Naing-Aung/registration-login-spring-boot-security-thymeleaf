package net.javaguides.springboot.service;

import java.util.Arrays;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User save(User user) {

		User registeredUser = new User();
		registeredUser.setFirstName(user.getFirstName());
		registeredUser.setLastName(user.getLastName());
		registeredUser.setEmail(user.getEmail());
		registeredUser.setPassword(passwordEncoder.encode(user.getPassword()));
		registeredUser.setCreatedBy(user.getCreatedBy());
		registeredUser.setCreatedDate(user.getCreatedDate());
		registeredUser.setImage(user.getImage());

		return userRepository.save(registeredUser);
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userRepository.findByEmail(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("Invalid username or password!");
//		}
//
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//				mapRolesAuthorities(user.getRoles()));
//	}

//	private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
//		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findAll(pageable);
	}

	@Override
	public User getUserByID(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if (optional.isPresent()) {
			user = optional.get();
		} else {
			throw new RuntimeException(" User not found for ID : " + id);
		}
		return user;
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<User> getByKeyword(String keyword) {
		return userRepository.findByKeyword(keyword);
	}

	@Override
	public User getUserByEmail(String loginEmail) {
		return userRepository.findByEmail(loginEmail);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}
