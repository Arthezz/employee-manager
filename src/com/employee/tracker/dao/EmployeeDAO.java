package com.employee.tracker.dao;

import java.util.List;
import com.employee.tracker.entity.Employee;

public interface EmployeeDAO {
	
	public List<Employee> getEmployees();

	public void saveEmployee(Employee theEmployee);

	public Employee getEmployee(int theId);

	public void deleteEmployee(int theId);

	public List<Employee> searchEmployees(String theSearchName);

	public List<Employee> getEmployeesBy(String sortingBy);

}
