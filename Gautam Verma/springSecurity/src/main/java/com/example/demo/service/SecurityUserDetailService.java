package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Component
public class SecurityUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not present"));
		return user;
	}

	public void createUser(UserDetails user) {
		userRepository.save((User) user);
	}

}
