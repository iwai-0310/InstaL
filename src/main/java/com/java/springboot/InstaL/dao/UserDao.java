package com.java.springboot.InstaL.dao;

import com.java.springboot.InstaL.entity.*;

public interface UserDao {
	User findByUserName(String UserName);

}
