package com.employee.tracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.employee.tracker.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	// injecting session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Employee> getEmployees() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create a query, sort by last_name
		Query<Employee> theQuery = currentSession.createQuery("from Employee order by lastName", Employee.class );
		
		// execute query and get result list
		List<Employee> employees = theQuery.getResultList();
		
		// return the results
		return employees;
	}

	@Override
	public void saveEmployee(Employee theEmployee) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the employee
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public Employee getEmployee(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrive from database using the primary key
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		return theEmployee;
	}

	@Override
	public void deleteEmployee(int theId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
		
	}

	@Override
	public List<Employee> searchEmployees(String theSearchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		 Query theQuery = null;
	        
	        // only search by name if theSearchName is not empty
	        if (theSearchName != null && theSearchName.trim().length() > 0) {

	            // search for firstName or lastNam, case insensitive
	            theQuery =currentSession.createQuery
	            		("from Employee where lower(firstName) like :theName or lower(lastName) like :theName order by lastName", Employee.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

	        }
	        else {
	            // theSearchName is empty so get all employees
	            theQuery = currentSession.createQuery("from Employee order by lastName", Employee.class);            
	        }
	        
	        // execute query and get result list
	        List<Employee> employees = theQuery.getResultList();
	                      
	        return employees;
	}

	@Override
	public List<Employee> getEmployeesBy(String sortingBy) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Employee> theQuery = null;
		
		switch(sortingBy) {
		case "First Name":
			theQuery = currentSession.createQuery("from Employee order by firstName", Employee.class );
			break;
		case "Last Name":
			theQuery = currentSession.createQuery("from Employee order by lastName", Employee.class );
			break;
		case "Email":
			theQuery = currentSession.createQuery("from Employee order by email", Employee.class );
			break;

		}
		
		List<Employee> employees = theQuery.getResultList();
		
		return employees;
	}

}
