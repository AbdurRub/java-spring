package com.cdk.employee.web;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cdk.employee.model.Employee;
import com.cdk.employee.model.EmployeeType;
import com.cdk.employee.model.Phone;
import com.cdk.employee.model.Phone.Type;
import com.cdk.employee.service.EmaService;
import com.cdk.employee.web.EmaController;
import com.google.gson.Gson;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration
@WebMvcTest(EmaController.class)
public class EmaControllerStepDefinitions{
	
	private static final Integer SUCCESS_CODE = 200;
	private static final String EMPLOYEE_ID = "c787d5bf-adde-4ef9-8356-7cae8dee714e";
	private static final String BASE_URL = "/employee";

	private Employee employee;
	
	@MockBean
	private EmaService emaService;
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc; 
	private MvcResult response;
	
	private Gson gson;
	
	@Before
    public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		}
	@Given("^Get mock employee object")
	public void get_mock_employee_object() throws Throwable {
		gson = new Gson(); 
		employee = mockEmployee();
	}

	@When("^Insert employee$")
	public void insert_employee() throws Throwable {
		response = mockMvc
				.perform(MockMvcRequestBuilders.post(BASE_URL + "/post").contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(employee)))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}
	
	@Given("^Get employee$")
	public void get_employee() throws Throwable {
	    response = mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/get"))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}
	
	@When("^Update employee$")
	public void update_employee() throws Throwable {
		response = mockMvc
				.perform(MockMvcRequestBuilders.put(BASE_URL + "/update").contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(mockEmployee())))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}
	
	@Given("^Delete employee$")
	public void delete_employee() throws Throwable {
		response = mockMvc
				.perform(MockMvcRequestBuilders.delete(BASE_URL + "/delete?employeeId=" + EMPLOYEE_ID))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
	}
	@Then("^Get success status code$") 
	public void get_success_status_code() throws Throwable {
		assertEquals(SUCCESS_CODE, response.getResponse().getStatus());
	}
	
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
