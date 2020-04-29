package com.cdk.employee.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.cdk.employee.model.Employee;
import com.cdk.employee.model.SearchCriteria;
import com.cdk.employee.repository.EmaRepository;
import com.cdk.employee.repository.EmployeeSpecs;
import com.cdk.employee.response.Response;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmaServiceImpl implements EmaService,DisposableBean, InitializingBean {

	private final EmaRepository emaRepo;

	@Override
	public List<Employee> getAllEmployees() {
		return emaRepo.findAll();
	}

	@Override
	public Response insertEmployee(Employee employee) {
		try {
			emaRepo.save(employee);
		} catch (Exception error) {
			log.error("Employee Insertion Failed : ", error);
			return new Response().setSuccessfulCount(0)
					.setFailedIds(Arrays.asList(employee.getEmployeeId().toString()));
		}

		return new Response().setSuccessfulCount(1).setFailedIds(Collections.emptyList());
	}

	@Override
	public String updateEmployee(Employee employee) {
		try {
			emaRepo.save(employee);
			return "Employee Updation Successfull";
		} catch (DataIntegrityViolationException error) {
			log.error("Employee Insertion Failed : ", error);
			return "Employee Updation Failed";
		}

	}

	@Override
	public String deleteEmployee(UUID employeeId) {
		try {
			emaRepo.deleteById(employeeId);
			return "Employee Deletion Successfull";
		} catch (Exception error) {
			log.error("Employee Deletion Failed : ", error);
			return "Employee Deletion Failed";
		}
	}
	
	// Bean initialization code
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Initializing method of EmployeeService bean is invoked!");
	}

	// Bean destruction code
	@Override
	public void destroy() throws Exception {
		log.info("Destroy method of EmployeeService bean is invoked!");
	}
	
	@PreDestroy
	public void preDestroy(){
		log.info("Pre Destroy method of EmployeeService bean is invoked");
	}

	//LIKE
	@Override
	public List<Employee> getEmployeeByFirstName(String firstName) {
		EmployeeSpecs specs = new EmployeeSpecs(new SearchCriteria("firstName", ":" , firstName));
		return emaRepo.findAll(specs);
	}

	//WHERE and AND and JOIN
	@Override
	public List<Employee> getEmployeeByFirstNameAndPhoneType(String firstName, String phoneType) {
		EmployeeSpecs specsFirstName = new EmployeeSpecs(new SearchCriteria("firstName", ":" , firstName));
		
		return emaRepo.findAll(Specification.where(specsFirstName).and(EmployeeSpecs.getPhoneType(phoneType)));
	}
	
	//WHERE and OR and JOIN
	@Override
	public List<Employee> getEmployeeByFirstNameOrPhoneType(String firstName, String phoneType) {
		EmployeeSpecs specsFirstName = new EmployeeSpecs(new SearchCriteria("firstName", ":" , firstName));
		return emaRepo.findAll(Specification.where(specsFirstName).or(EmployeeSpecs.getPhoneType(phoneType)));
	}

	// NOT
	@Override
	public List<Employee> getEmployeeByNotFirstName(String firstName) {
		
		EmployeeSpecs specsFirstName = new EmployeeSpecs(new SearchCriteria("firstName", ":" , firstName));
		return emaRepo.findAll(Specification.not(specsFirstName));
	}

	@Override
	public List<Employee> getEmployeeBySalary() {
		List<Employee> employees = emaRepo.findAll();
		return employees.stream().filter(e -> e.getSalary() < 10000.00).collect(Collectors.toList());
	}

	@Override
	public List<Employee> getEmployeeBySalaryWithIncrement() {
		List<Employee> employees = emaRepo.findAll();
		return employees.stream().map(e -> e.setSalary(e.getSalary() + e.getSalary() * 0.1))
				.collect(Collectors.toList());
	}

	@Override
	public List<Employee> getAllEmployeesWithFreshResource() {
		List<Employee> employees = emaRepo.findAll();
		
		employees.stream().forEach(emp -> {
			if (ObjectUtils.isEmpty(emp.getSkills())) {
				emp.setSkills(Arrays.asList("Fresh"));
			}
		});
		return employees;
	}

	@Override
	public List<Employee> getAllEmployeesWithOldAge() {
		LocalDate date = LocalDate.now().minusYears(40);
		EmployeeSpecs specsDate = new EmployeeSpecs(new SearchCriteria("dateOfBirth", ">" , date));
		return emaRepo.findAll(Specification.where(specsDate));
	}
}
