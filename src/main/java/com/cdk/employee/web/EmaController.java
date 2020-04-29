package com.cdk.employee.web;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdk.employee.model.Employee;
import com.cdk.employee.response.Response;
import com.cdk.employee.service.EmaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "employee")
@RequiredArgsConstructor
public class EmaController {
	
	private final EmaService emaService;

	@GetMapping(path = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok().body(emaService.getAllEmployees());
	}

	@PostMapping(path = "/post")
	public ResponseEntity<Response> insertEmployees(@RequestBody Employee employee) {
		return ResponseEntity.ok().body(emaService.insertEmployee(employee));
	}

	@PutMapping(path = "/update")
	public ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
		return ResponseEntity.ok().body(emaService.updateEmployee(employee));
	}

	@DeleteMapping(path = "/delete")
	public ResponseEntity<String> deleteEmployees(@RequestParam String employeeId) {
		UUID id = UUID.fromString(employeeId);
		return ResponseEntity.ok().body(emaService.deleteEmployee(id));
	}
	
	// LIKE
	@GetMapping(path = "/firstname")
	public List<Employee> getEmployeeByFirstName(@RequestParam String firstName) {
		return emaService.getEmployeeByFirstName(firstName);
	}

	// WHERE and AND and JOIN
	@GetMapping(path = "/firstname/phonetype/and")
	public List<Employee> getEmployeeByFirstNameAndPhoneType(@RequestParam String firstName, @RequestParam String phoneType) {
		return emaService.getEmployeeByFirstNameAndPhoneType(firstName, phoneType);
	}
	
	// WHERE and OR and JOIN
	@GetMapping(path = "/firstname/phonetype/or")
	public List<Employee> getEmployeeByFirstNameOrPhoneType(@RequestParam String firstName, @RequestParam String phoneType) {
		return emaService.getEmployeeByFirstNameOrPhoneType(firstName, phoneType);
	}
	
	// NOT
	@GetMapping(path = "/firstname/not")
	public List<Employee> getEmployeeByFirstNameNothoneType(@RequestParam String firstName) {
		return emaService.getEmployeeByNotFirstName(firstName);
	}
	
	// Fetch Employees having salary not greater than 10K
	@GetMapping(path = "/employees/salary/10k")
	public List<Employee> getEmployeeBySalary() {
		return emaService.getEmployeeBySalary();
	}
	
	// Fetch Employees and give 10% Increment.
	@GetMapping(path = "/employees/salary/increment")
	public List<Employee> getEmployeeBySalaryWithIncrement() {
		return emaService.getEmployeeBySalaryWithIncrement();
	}
	
	// Fetch Employees handling fresh resources.
	@GetMapping(path = "/employees/fresh/")
	public List<Employee> getAllEmployeesWithFreshResource() {
		return emaService.getAllEmployeesWithFreshResource();
	}
	
	// Fetch Employees older than 40 Age.
	@GetMapping(path = "/employees/age/older/40")
	public List<Employee> getAllEmployeesWithOldAge() {
		return emaService.getAllEmployeesWithOldAge();
	}
	
}
