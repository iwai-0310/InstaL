package com.java.springboot.InstaL.service;
import com.java.springboot.InstaL.entity.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	
	public User findByUserName(String userName);
}
