package com.cdk.employee.service;

import java.util.List;
import java.util.UUID;

import com.cdk.employee.model.Employee;
import com.cdk.employee.response.Response;

public interface EmaService {

	List<Employee> getAllEmployees();
	Response insertEmployee(Employee employee);
	String updateEmployee(Employee employee);
	String deleteEmployee(UUID employeeId);
	List<Employee> getEmployeeByFirstName(String firstName);
	List<Employee> getEmployeeByFirstNameAndPhoneType(String firstName, String phoneType);
	List<Employee> getEmployeeByFirstNameOrPhoneType(String firstName, String phoneType);
	List<Employee> getEmployeeByNotFirstName(String firstName);
	List<Employee> getEmployeeBySalary();
	List<Employee> getEmployeeBySalaryWithIncrement();
	List<Employee> getAllEmployeesWithFreshResource();
	List<Employee> getAllEmployeesWithOldAge();
	
}
