package com.returnorder.jwtauthentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.returnorder.jwtauthentication.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {

}
