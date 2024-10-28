package com.codeinsight.exercise.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codeinsight.exercise.DTO.ResponseDTO;
import com.codeinsight.exercise.DTO.UserDTO;
import com.codeinsight.exercise.entity.User;
import com.codeinsight.exercise.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtService jwtService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not present"));
		return user;
	}

	@Override
	public ResponseDTO registerUser(UserDTO userDTO, PasswordEncoder passwordEncoder){
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			User user = new User();
			user.setEmail(userDTO.getEmail());
			user.setName(userDTO.getEmail());
			user.setPhoneNumber(userDTO.getPhoneNumber());
			user.setRole(userDTO.getRole());
			String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
			user.setPassword(encodedPassword);
			
			User userResult = this.createUser(user);
			
			if(userResult.getId() > 0) {
				responseDTO.setStatusCode(200);
				responseDTO.setMessage("User Registered Successfully");
			}
		} catch (Exception exception) {
			responseDTO.setError(exception.getMessage());
			responseDTO.setStatusCode(500);
		}
		return responseDTO;
	}

	private User createUser(UserDetails user) {
		return userRepository.save((User) user);
	}

	@Override
	public ResponseDTO login(UserDTO userDTO, AuthenticationManager authenticationManager) {
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
			User user = userRepository.findUserByEmail(userDTO.getEmail()).orElseThrow();
			String token = jwtService.generateToken(user);
			String refreshToken = jwtService.generateRefreshToken(new HashMap<String, Object>(), user);
			
			responseDTO.setToken(token);
			responseDTO.setRefreshToken(refreshToken);
			responseDTO.setRole(user.getRole());
			responseDTO.setMessage("Successfully Logged In");
			responseDTO.setExpirationTime("24Hrs");
			responseDTO.setStatusCode(200);
		} catch (Exception exception) {
			responseDTO.setStatusCode(500);
			responseDTO.setError(exception.getMessage());
		}
		return responseDTO;
	}
}
