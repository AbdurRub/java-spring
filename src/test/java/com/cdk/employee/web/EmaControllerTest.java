package com.cdk.employee.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
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
import com.cdk.employee.response.Response;
import com.cdk.employee.service.EmaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(EmaController.class)
public class EmaControllerTest {
	
	private static final String BASE_URL = "/employee";
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	@MockBean
	private EmaService emaService;
    
	@Before
    public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		}
	
	@Test
	public void testGetAllEmployees() throws Exception {
		Mockito.when(emaService.getAllEmployees()).thenReturn(Arrays.asList(mockEmployee()));
	    mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/get"))
	           .andDo(MockMvcResultHandlers.print())
	           .andExpect(MockMvcResultMatchers.status().isOk());
	  }
	
	@Test
	public void testInsertEmployee() throws Exception {
        Employee mockEmployee = mockEmployee();
		Response rep = new Response().setFailedIds(Collections.emptyList()).setSuccessfulCount(1);
		Mockito.when(emaService.insertEmployee(mockEmployee)).thenReturn(rep);
		Gson gson = new Gson();

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(BASE_URL + "/post").contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(mockEmployee)))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		String responseString = mapper.writeValueAsString(rep);
		Assert.assertEquals(responseString, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testInsertEmployeeFailed() throws Exception {
		
		Response rep = new Response().setFailedIds(Arrays.asList("c787d5bf-adde-4ef9-8356-7cae8dee714e")).setSuccessfulCount(0);
		Mockito.when(emaService.insertEmployee(mockEmployee())).thenReturn(rep);
		Gson gson = new Gson();

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post(BASE_URL + "/post").contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(mockEmployee())))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		ObjectMapper mapper = new ObjectMapper();
		String responseString = mapper.writeValueAsString(rep);
		Assert.assertEquals(responseString, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {
		String response = "Update Successfull";
		Mockito.when(emaService.updateEmployee(mockEmployee())).thenReturn(response);
		Gson gson = new Gson();

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put(BASE_URL + "/update").contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(mockEmployee())))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		Assert.assertEquals(response, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testUpdateEmployeeFailed() throws Exception {
		String response = "Update Failed";
		Mockito.when(emaService.updateEmployee(mockEmployee())).thenReturn(response);
		Gson gson = new Gson();

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put(BASE_URL + "/update").contentType(MediaType.APPLICATION_JSON)
						.content(gson.toJson(mockEmployee())))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		Assert.assertEquals(response, result.getResponse().getContentAsString());
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {
		String response = "Delete Successfull";
		UUID employeeId = UUID.fromString("c787d5bf-adde-4ef9-8356-7cae8dee714e");
		Mockito.when(emaService.deleteEmployee(employeeId)).thenReturn(response);
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete(BASE_URL + "/delete?employeeId=" + employeeId))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		Assert.assertEquals(response, result.getResponse().getContentAsString());
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
