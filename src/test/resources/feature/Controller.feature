Feature: Cucumber Feature File for Integration Tests.
  This is feature file for integration tests.

  Scenario: Insert employee into data store
    Given Get mock employee object
    When Insert employee
    Then Get success status code
  
  @FetchAllEmployees
  Scenario: Fetch all employees from datastore
    Given Get employee
    Then Get success status code
  
  @UpdateEmployee
  Scenario: Update employee
    Given Get mock employee object
    When Update employee
    Then Get success status code
    
  @DeleteEmployee
  Scenario: Delete employee
    Given  Delete employee
    Then  Get success status code