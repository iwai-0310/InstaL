package com.java.springboot.InstaL.dao;

import com.java.springboot.InstaL.entity.*;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao {
	private EntityManager entityManager;

	public UserDaoImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public User findByUserName(String theUserName) {
		//read from database using username
		TypedQuery<User> theQuery=entityManager.createQuery("from User where userName=:uName",User.class);
		theQuery.setParameter("uName", theUserName);
		
		User theUser=null;
		try {
			theUser=theQuery.getSingleResult();
		}catch(Exception e) {
			theUser=null;
		}
		return theUser;
	}
}
