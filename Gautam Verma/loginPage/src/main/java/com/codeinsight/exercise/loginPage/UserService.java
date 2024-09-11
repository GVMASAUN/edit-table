package com.codeinsight.exercise.loginPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void registerUser(UserForm userForm) {
		User user = new User();
		user.setEmail(userForm.getEmail());
		user.setName(userForm.getName());
		user.setPassword(userForm.getPassword());
		userRepository.save(user);
	}
	
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
