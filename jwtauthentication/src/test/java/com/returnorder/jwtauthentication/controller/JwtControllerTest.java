package com.returnorder.jwtauthentication.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import javax.naming.AuthenticationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.returnorder.jwtauthentication.controller.JwtController;
import com.returnorder.jwtauthentication.helper.JwtUtil;
import com.returnorder.jwtauthentication.model.JwtRequest;
import com.returnorder.jwtauthentication.model.UserModel;
import com.returnorder.jwtauthentication.repository.UserRepository;
import com.returnorder.jwtauthentication.service.CustomUserDetailsService;


class JwtControllerTest {

	@InjectMocks
	private JwtController jwtController;

	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtUtil jwtUtil;
	
	@Autowired 
	private UserRepository userRepository;

	@Mock
	private CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(userRepository);
	
	UserDetails userDetails;
	UserModel userModel;

	private Authentication authenticate;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		jwtController=new JwtController(authenticationManager, customUserDetailsService, jwtUtil);
	}
	
	
	  @Test 
	  void testGenerateToken() throws Exception 
	  {
		  UserModel user = new UserModel("UID01G", "Gaurav", "Gaurav123"); 
		  UserDetails loadUser = customUserDetailsService.loadUserByUsername("Gaurav"); 
		  UserDetails  userDetails = new User(user.getUserId(),user.getPassword(),new ArrayList<>()); 
		  JwtRequest jwtRequest = new JwtRequest(user.getUsername(), user.getPassword());
		  Mockito.when(customUserDetailsService.loadUserByUsername(user.getUsername())).
		  thenReturn(userDetails);
		  Mockito.when(jwtUtil.generateToken(loadUser)).thenReturn("token"); 
		  ResponseEntity<?> login = jwtController.generateToken(jwtRequest); 
		  assertEquals(200,  login.getStatusCodeValue());
	  
	  }
	 
	  @Test
		void testLoadUserByUsernameReturningNull() throws Exception{
		  authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("ABC", "ABC"));
		  System.out.println(authenticate);
		}

	  @Test
		void testLoadUserByUsernameWrong(){
			Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("ABC", "ABC"))).thenThrow(BadCredentialsException.class);
		}
}
