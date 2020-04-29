package com.cdk.employee.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdk.employee.model.Employee;
import com.cdk.employee.model.EmployeeType;
import com.cdk.employee.model.Phone;
import com.cdk.employee.model.Phone.Type;
import com.cdk.employee.service.EmaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.AUTO_CONFIGURED)
public class EmaControllerIntegrationTest {
	
	private static final Integer SUCCESS_CODE = 200;
	private static final String EMPLOYEE_ID = "c787d5bf-adde-4ef9-8356-7cae8dee714e";

	@Autowired
	private EmaService emaService;
	private EmaController emaController;
    
	@Before
    public void setup() {
		emaController = new EmaController(emaService);
		}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		ResponseEntity<?> response = emaController.getAllEmployees();
		assertEquals(SUCCESS_CODE, response.getStatusCodeValue());
	  }
	
	@Test
	public void testInsertEmployee() throws Exception {
        Employee mockEmployee = mockEmployee();
		ResponseEntity<?> response = emaController.insertEmployees(mockEmployee);
		assertEquals(SUCCESS_CODE, response.getStatusCodeValue());
	}
	
	
	@Test
	public void testUpdateEmployee() throws Exception {
        Employee mockEmployee = mockEmployee();
		ResponseEntity<?> response = emaController.updateEmployee(mockEmployee);
		assertEquals(SUCCESS_CODE, response.getStatusCodeValue());
	}
	
//	@Test
//	public void testDeleteEmployee() throws Exception {
//		ResponseEntity<?> response = emaController.deleteEmployees(EMPLOYEE_ID);
//		assertEquals(SUCCESS_CODE, response.getStatusCodeValue());
//	}
	
	private Employee mockEmployee() {
		return new Employee()
				.setAge(1)
				.setDateOfBirth("2020-03-13")
				.setEmployeeType(EmployeeType.REGULAR)
				.setExperience("3")
				.setFirstName("Atta")
				.setLastName("Shahid")
				.setHourlyRate(3.00)
				.setHours(2)
				.setSalary(200.00)
				.setPhoneNumbers(Arrays.asList(new Phone().setPhoneNumber("03").setType(Type.WORK)))
				.setSkills(Arrays.asList("Big Data", "Python"));
	}
}
