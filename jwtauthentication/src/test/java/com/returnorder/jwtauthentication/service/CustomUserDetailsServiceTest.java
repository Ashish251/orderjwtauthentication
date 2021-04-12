package com.returnorder.jwtauthentication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.returnorder.jwtauthentication.model.UserModel;
import com.returnorder.jwtauthentication.repository.UserRepository;
import com.returnorder.jwtauthentication.service.CustomUserDetailsService;

class CustomUserDetailsServiceTest {

	private static CustomUserDetailsService customUserDetailsService;
	
	private static UserModel userOne;
	private static UserModel userTwo;
	private static UserModel userThree;
	
	@Mock
	private UserRepository userRepository;
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		customUserDetailsService = new CustomUserDetailsService(userRepository);
		loadUsers();
	}
	
	private void loadUsers(){
		userOne = new UserModel("USI02S", "Shrey", "Shrey123");
		userTwo = new UserModel("USI03A", "Ashish", "Ashish123");
		userThree = new UserModel("USI05R", "Ritansh", "Ritansh123");
	}

	@Test
	void testLoadUserByUsername() {
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(userThree));
		customUserDetailsService.loadUserByUsername("Ritansh");
	}
	
	@Test
	void testLoadUserByUsernameReturningNull(){
		assertThrows(UsernameNotFoundException.class, ()->{
			customUserDetailsService.loadUserByUsername("ABC");
		},"User not found !!!");
	}

	@Test
	void testGetAllUsers() {
		Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(userOne,userTwo,userThree));
		List<UserModel> user = customUserDetailsService.getAllUsers();
		assertNotNull(user);
		assertEquals(3, user.size());
	}

	@Test
	void testGetUserByName() {
		Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(Optional.of(userThree));
		UserModel user = customUserDetailsService.getUserByName("Ritansh");
		assertNotNull(user);
		assertEquals(user.getUsername(), "Ritansh");
	}

	@Test
	void testCreateUser() {
		UserModel userToSave = new UserModel("USI06AB", "ABC", "ABC123");
		UserModel userFromDB = new UserModel("USI06AB", "ABC", "ABC123");
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(userFromDB);
		UserModel createdUser  = customUserDetailsService.createUser(userToSave);
		assertNotNull(createdUser);
		assertEquals("ABC", createdUser.getUsername());
	}

}
