package com.employee.tracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.tracker.dao.EmployeeDAO;
import com.employee.tracker.entity.Employee;
import com.employee.tracker.service.EmployeeService;


@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	// injecting employee service
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		
		//get employees from the dao
		List<Employee> theEmployees = employeeService.getEmployees();
		
		// add the employees to the model
		theModel.addAttribute("employees", theEmployees);
		
		return "list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		
		Employee theEmployee = new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employee-form";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		// save the employee
		employeeService.saveEmployee(theEmployee);
		
		return "redirect:/employee/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
									Model theModel) {
		
		// get the employee from the service
		Employee theEmployee = employeeService.getEmployee(theId);
		
		// set employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", theEmployee);
		
		// send over to form
		return "employee-form";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId) {
		
		// delete the customer
		employeeService.deleteEmployee(theId);
		
		return "redirect:/employee/list";
	}
	
	@GetMapping("/search")
	public String searchEmployees(@RequestParam("theSearchName") String theSearchName, Model theModel) {

	        // search employees from the service
	        List<Employee> theEmployees = employeeService.searchEmployees(theSearchName);
	                
	        // add the employees to the model
	        theModel.addAttribute("employees", theEmployees);

	        return "list-employees";    
	}
	
	@GetMapping(value = {"/sortByFirstName", "sortByLastName", "sortByEmail"})
	public String sortBy(@RequestParam Map<String,String> requestParams, String sortingBy, Model theModel) {
		
		String first = requestParams.get("fName");
		String last = requestParams.get("lName");
		String email = requestParams.get("email");
		
		if (first != null) {
			sortingBy = requestParams.get("fName");
			
		} else if (last != null) {
			sortingBy = requestParams.get("lName");
			
		} else if (email != null) {
			sortingBy = requestParams.get("email");
		}
		
		List<Employee> theEmployees = employeeService.getEmployeesBy(sortingBy);
		
		theModel.addAttribute("employees", theEmployees);
		
		return "list-employees";
	}

}
