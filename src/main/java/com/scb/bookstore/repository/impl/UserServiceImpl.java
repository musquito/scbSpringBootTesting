package com.scb.bookstore.repository.impl;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scb.bookstore.model.user.User;
import com.scb.bookstore.repository.UserRepository;
import com.scb.bookstore.repository.UserService;


@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public User findByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User save(User user) {
		User newUser = new User();
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setDateOfBirth(user.getDateOfBirth());
		return userRepository.save(newUser);
	}
}
