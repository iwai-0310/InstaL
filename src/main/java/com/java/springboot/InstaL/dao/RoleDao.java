package com.java.springboot.InstaL.dao;

import com.java.springboot.InstaL.entity.Role;

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);

}
