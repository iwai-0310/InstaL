package com.java.springboot.InstaL.service;

import java.util.List;

import com.java.springboot.InstaL.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee findById(int theId);
	
	void save(Employee theEmployee);
	
	void deleteById(int theId);
	
}
