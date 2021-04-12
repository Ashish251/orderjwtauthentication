package com.returnorder.jwtauthentication.service;

import java.util.List;

import com.returnorder.jwtauthentication.model.UserModel;

public interface UserService {

	List<UserModel> getAllUsers();
	UserModel getUserByName(final String username);
	UserModel createUser(final UserModel userToSave);
	
	
}
