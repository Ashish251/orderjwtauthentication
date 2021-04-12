package com.returnorder.jwtauthentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.returnorder.jwtauthentication.helper.JwtUtil;
import com.returnorder.jwtauthentication.model.JwtRequest;
import com.returnorder.jwtauthentication.model.JwtResponse;
import com.returnorder.jwtauthentication.model.UserModel;
import com.returnorder.jwtauthentication.service.CustomUserDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class JwtController {

	private Logger log = LoggerFactory.getLogger(JwtController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	private String token = "random";

	public JwtController(AuthenticationManager authenticationManager2,
			CustomUserDetailsService customUserDetailsService2, JwtUtil jwtUtil2) {
		this.authenticationManager = authenticationManager2;
		this.customUserDetailsService=customUserDetailsService2;
		this.jwtUtil=jwtUtil2;
	}

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@ApiOperation(value = "Used to generate token", notes = "This method generates the token for valid username and password.")
	public ResponseEntity<?> generateToken(@ApiParam(value = "JwtRequest Model which has the user details.", required = true) @RequestBody JwtRequest jwtRequest) throws Exception {
		log.info("JwtController :: generateToken :: \token");
		try {
			Authentication authenticate = this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Username Not Found !!!");
			
		} catch (BadCredentialsException b) {
			b.printStackTrace();
			throw new Exception("Bad Credentials !!!");
			
		}
		
		//fine area - true credentials
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		UserModel userModel = this.customUserDetailsService.getUserByName(jwtRequest.getUsername());
		
		token  = this.jwtUtil.generateToken(userDetails);
		log.debug("Token : {}",token);
		
		return ResponseEntity.ok(new JwtResponse(token,userModel));
		

	}
	
}
