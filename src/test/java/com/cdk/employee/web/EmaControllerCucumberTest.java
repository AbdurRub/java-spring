package com.cdk.employee.web;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.cdk.employee.EmployeeApplication;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;




@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty"},
	    features = {"src/test/resources/feature/Controller.feature"},
	    glue = {"com.cdk.employee"})
public class EmaControllerCucumberTest {

}

