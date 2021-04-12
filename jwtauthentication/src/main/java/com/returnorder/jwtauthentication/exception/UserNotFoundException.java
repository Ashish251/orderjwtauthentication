package com.returnorder.jwtauthentication.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundException extends Exception {

	private Logger log = LoggerFactory.getLogger(UserNotFoundException.class);
	public UserNotFoundException(String message) {
		super(message);

		log.info("UserNotFoundException :: constructor");
	}

}
