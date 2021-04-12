package com.returnorder.jwtauthentication.model;

public class JwtResponse {
	String token;
	UserModel userModel;

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String token, UserModel userModel) {
		super();
		this.token = token;
		this.userModel = userModel;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", userModel=" + userModel + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	

}
