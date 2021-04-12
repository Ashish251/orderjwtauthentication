package com.returnorder.jwtauthentication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class UserModel {
	
	@Column(name="uid")
	private String userId;
	@Id
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;

	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserModel(String userId, String username, String password) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}

	
}
