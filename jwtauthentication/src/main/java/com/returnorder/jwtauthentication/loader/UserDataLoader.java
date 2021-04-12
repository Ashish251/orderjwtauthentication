package com.returnorder.jwtauthentication.loader;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.returnorder.jwtauthentication.model.UserModel;
import com.returnorder.jwtauthentication.service.UserService;



@Component
public class UserDataLoader implements CommandLineRunner {
	
	private Logger log = LoggerFactory.getLogger(UserDataLoader.class);
	
	
	private UserService userService;
	
	public UserDataLoader(UserService userService) {
		this.userService = userService;
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("UserDataLoader :: run()");
		
		List<UserModel> users = userService.getAllUsers();
		if(users != null && users.size() == 0) {
			loadUsers();
		}
	}
	
	
	public void loadUsers() {
		log.info("UserDataLoader :: loadUsers()");
		
		UserModel user1 = new UserModel("UID01G", "Gaurav", "Gaurav123");
		userService.createUser(user1);
		
		UserModel user2 = new UserModel("UID02S", "Shrey", "Shrey123");
		userService.createUser(user2);
		
		UserModel user3 = new UserModel("UID03D", "Darsh", "Darsh123");
		userService.createUser(user3);
		
		UserModel user4 = new UserModel("UID04R", "Ritansh", "Ritansh123");
		userService.createUser(user4);

		UserModel user5 = new UserModel("UID05A", "Ashish", "Ashish123");
		userService.createUser(user5);
		
	}
}
