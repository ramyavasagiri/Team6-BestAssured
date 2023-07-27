@tag
Feature: Assignment

  Background: User sets Authorization to No Auth
    
    
    @assignmentTest0_POST_Assignment_POSITIVE
    Scenario: add a record with valid endpoint and request body-ASN
    
    Given User creates POST Request for the LMS API endpoint-ASN
    
    When  User sends HTTPS Request and  request Body-ASN 
    
    Then  User receives 201 Created Status with response body-ASN
    
    
    @assignmentTest1_GET_AllAssignment
    Scenario Outline: Check if user able to retrieve a record with valid LMS API
  
    Given User creates GET Request for the LMS API endpoint-Assgnment
   
    When User sends HTTPS GET Request-Assignment
    
    Then  User receives 200 OK Status-Assignment

  
  
    @assignmentTest2_GET_byAssignmentID
     
    Scenario Outline: Check if user able to retrieve a record with valid Assignment ID
  
    Given User creates GET Request for the LMS API endpoint-AssignmentID
    
    When  User sends HTTPS GET Request-Assignment
    
    Then  User receives 200 OK Status-Assignment
    
    
    @assignmentTest3_GET_byBatchID	
Scenario Outline:Check if user able to retrieve a record with valid BATCH ID	
Given User creates GET Request for the LMS API endpoint-BATCH ID-Assignment
When User sends HTTPS GET Request-Assignment
Then User receives 200 OK Status-Assignment


@assignmentTest4_GET_byInvalidBatchID
Scenario Outline: Check if user able to retrieve a record with invalid BATCH ID		
Given GET Request for the LMS API endpoint with InvalidBatch Id-Assignment
When User sends HTTPS GET Request-Assignment
Then User receives 404 OK Status-Assignment
    
    
    @assignmentTest6_PUT_Update-Assignment 
    
    Scenario Outline: Check if user able to update a record with valid AssignmentID and mandatory request body
    
    Given User creates PUT Request for the LMS API endpoint  and Valid Assignment Id-Assignment
    
    When  User sends HTTPS Request and request Body with mandatory and additional  fields-Assignment
    
    Then  User receives 200 OK Status-Assignment 
    
     @assignmentTest9_Delete-ValidAssignmentID
    
    Scenario Outline: User is able to delete a record with valid Assignment ID
    
    Given User creates DELETE Request for the LMS API endpoint and valid Assignment Id-Assignment
    
    When  User sends HTTPS Request-ValidAssignmentID-Assignment
    
    Then  User receives 200 OK Status-Assignment