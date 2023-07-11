package com.java.springboot.InstaL.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.springboot.InstaL.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
	//add a method to sort by last name
	public List<Employee> findAllByOrderByLastNameAsc();
	
}
