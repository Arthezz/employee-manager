package com.employee.tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.tracker.dao.EmployeeDAO;
import com.employee.tracker.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	// injecting employee dao
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	@Transactional
	public List<Employee> getEmployees() {
		
		return employeeDAO.getEmployees();
	}

	@Override
	@Transactional
	public void saveEmployee(Employee theEmployee) {

		employeeDAO.saveEmployee(theEmployee);
		
	}

	@Override
	@Transactional
	public Employee getEmployee(int theId) {
		
		return employeeDAO.getEmployee(theId);
	}

	@Override
	@Transactional
	public void deleteEmployee(int theId) {
		
		employeeDAO.deleteEmployee(theId);
		
	}

	@Override
	@Transactional
	public List<Employee> searchEmployees(String theSearchName) {
		
		return employeeDAO.searchEmployees(theSearchName);
	}

	@Override
	@Transactional
	public List<Employee> getEmployeesBy(String sortingBy) {
		
		return employeeDAO.getEmployeesBy(sortingBy);
	}

}
