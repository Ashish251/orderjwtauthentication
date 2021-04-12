package com.returnorder.jwtauthentication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.returnorder.jwtauthentication.exception.UserNotFoundException;
import com.returnorder.jwtauthentication.model.UserModel;
import com.returnorder.jwtauthentication.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService, UserService {

	private Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository2) {
		this.userRepository = userRepository2;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("CustomUserDetailsService :: loadUserByUsername");
		UserModel user = getUserByName(username); 
		
		if(user!=null) {
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found !!!");
		}
	}

	@Override
	public List<UserModel> getAllUsers() {
		log.info("CustomUserDetailsService :: getAllUsers");
		List<UserModel> list = userRepository.findAll();
		return list;
	}

	@Override
	public UserModel getUserByName(String username) {
		log.info("LoadUSerDetailsService :: getUserByName");
		
		
		UserModel user = null;
		if (username != null) {
			try {
				Optional<UserModel> optional = userRepository.findById(username);
				if (!optional.isPresent()) {
					throw new UserNotFoundException("User Not Found");
				} else {
					user = optional.get();
				}
			} catch (UserNotFoundException e) {
				e.getMessage();
			}

		}
		return user;
	}

	@Override
	public UserModel createUser(UserModel userToSave) {
		if(userToSave!=null) {
			userRepository.save(userToSave);			
		}
		return userToSave;
	}
	
	

	

}
