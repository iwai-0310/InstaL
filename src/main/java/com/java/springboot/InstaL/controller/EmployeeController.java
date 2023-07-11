package com.java.springboot.InstaL.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.springboot.InstaL.entity.Employee;
import com.java.springboot.InstaL.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService=theEmployeeService;
	}
	//mapping for signup
	@GetMapping("/showFormForSignup")
	public String showFormForSignup(Model theModel) {
		//create a  model to bind the form data
		Employee theEmployee=new Employee();
		theModel.addAttribute("employee",theEmployee);
		return "employees/signup-form";
	}
	//saving the employee data we get while submitting the signup form
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		//save the employee
		employeeService.save(theEmployee);
		//using redirect to prevent multiple same submissions
		return "redirect:/employees/list";
	}
	//add mapping for updating user info
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel) {
		//get the employee from the service
		Employee theEmployee=employeeService.findById(theId);
		//set the employee in the model to pre-populate the form
		theModel.addAttribute("employee",theEmployee);
		//send over to our form
		
		
		return "employees/signup-form";
	}
	//add mapping to delete user
	@GetMapping("delete")
	public String delte(@RequestParam("employeeId") int theId) {
		//delete the employee
		employeeService.deleteById(theId);
		//redirect to employees/list
		return "redirect:/employees/list";
		
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		//get the employees from database
		List<Employee> theEmployees=employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/list-employees";
	}
}









