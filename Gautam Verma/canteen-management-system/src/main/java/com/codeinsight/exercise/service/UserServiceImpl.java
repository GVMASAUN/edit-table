package com.codeinsight.exercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeinsight.exercise.DTO.UserDTO;
import com.codeinsight.exercise.entity.User;
import com.codeinsight.exercise.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not present"));
		return user;
	}
	
	@Override
	public void registerUser(UserDTO userDTO) {
		User user = new User();
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getEmail());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setRole(userDTO.getRole());
		
		String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
		user.setPassword(encodedPassword);
		
		this.createUser(user);
	}
	
	private void createUser(UserDetails user) {
		userRepository.save((User) user);
	}
}
