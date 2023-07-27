@tag
Feature: positive and negative scenarios for program module
  
Background:User sets Authoization to No  Auth.
  
  @programTest1_POST_validendpoint
  Scenario: Check if user able to create a program with valid endpoint and request 
   body (non existing values)
    Given User creates POST Request for the LMS API endpoint program
    When User sends HTTP POST request for valid program
    Then User receives 201 Created Status with response body for program
    
    
 @programTest2_POST_existing_program_name
  Scenario: Check if user able to create a program with existing program name
  valid endpoint and request body existing values in program name  Program
    Given User creates POST Request for the LMS API endpoint program
    When User sends HTTPS Request and  request Body with mandatory , additional  fields for existing Program                                
    Then User receives 400 Bad Request Status with message and boolean success details for Program
   
  @programTest3_POST_missingfields
    Scenario: Check if user able to create a program missing mandatory fields in request body Program
    Given User creates POST Request for the LMS API endpoint program
    When User sends http POST request for missing mandatory Program
    Then User receives 400 Bad Request Status with message and boolean success details for Program
    
  @programTest4_GET_AllPrograms
    Scenario: Check if user able to retrieve all programs with valid LMS Program
    Given User creates GET Request for the LMS API endpoint Program
    When User sends http GET request for Program
    Then User receives 200 OK Status with response body for Program
    
  @programTest5_GET_validProgramID
      Scenario: Check if user able to retrieve a program with valid program ID and LMS API 
      Given User creates GET Request for the LMS API endpoint Program
      When User sends http GET request for Program
      Then User receives 200 OK Status with response body for Program
      
  @programTest6_GET_invalidProgramID
      Scenario: User creates GET Request for the LMS API endpoint Program
      Given User creates GET Request for the LMS API endpoint Program
      When User sends http GET request for Program
      Then User receives 404 Not Found Status with message and boolean success details for Program
      
   @programTest7_PUT_ByvalidID   
      Scenario: Check if user able to update a program with valid programID and mandatory request body 
      Given User creates PUT Request for the LMS API endpoint  and Valid programID Program
      When User sends HTTPS PUT Request  and  request Body with mandatory , additional  fields for Program
      Then User receives 200 OK Status with updated value in response body for Program
      
   @programTest8_PUT_ByinvalidID  
      Scenario: Check if user able to update a program with invalid programID and mandatory request body
      Given User creates PUT PUT Request for the LMS API endpoint  and inValid programID Program
      When User sends HTTPS Request  and  request Body with mandatory,additional  fields for Program
      Then User receives 404 Not Found Status with message and boolean success details for Program
      
    @programTest9_PUT_missingfield
      Scenario: Check if user able to update a program  missing mandatory fields in request body  
      Given User creates PUT Request for the LMS API endpoint  and Valid programID Program
      When User sends HTTPS PUT Request  and request Body  (missing mandatory fields) for Program
      Then User receives 400 Bad Request Status with message and boolean success details for Program
      
     @programTest10_PUT_Byvalidname 
      Scenario: Check if user able to update a program with  Valid program Name and request body
      Given User creates PUT Request for the LMS API endpoint  and Valid program Name Program
      When User sends HTTPS PUT Request  and  request Body with mandatory fields for Program
      Then User receives 200 OK Status with updated value in response body for Program
      
      @programTest11_PUT_Byinvalidname
      Scenario: Check if user able to update a program with  invalid program Name and request body
      Given User creates PUT Request for the LMS API endpoint  and inValid program Name Program
      When User sends HTTPS PUT Request  and  request Body with mandatory fields for Program
      Then User receives 404 Not Found Status with message and boolean success details for Program
      
      @programTest12_PUT_missingfields
      Scenario: Check if user able to update a program using valid program name - missing mandatory fields in request body 
      Given User creates PUT Request for the LMS API endpoint  and  Valid program Name for Program
      When User sends HTTPS PUT Request  and request Body for Program
      Then User receives 400 Bad Request Status with message and boolean success details for Program
      
      #@programTest13_DELETE_validname
      #Scenario: Check if user able to delete a program with valid programName
      #Given User creates DELETE Request for the LMS API endpoint  and  valid programName Program
      #When User sends HTTPS DELETE Request Program
      #Then User receives 200 Ok status with message Program
      #
      #@programTest14_DELETE_invalidname
      #Scenario: Check if user able to delete a program with valid LMS API,invalid programName
      #Given User creates DELETE Request for the LMS API endpoint  and  invalid programName Program
      #When User sends HTTPS DELETE Request Program
      #Then User receives 404 Not Found Status with message and boolean success details Program
      #
      #@programTest15_DELETE_validID
      #Scenario: Check if user able to delete a program with valid program ID Program
      #Given User creates DELETE Request for the LMS API endpoint  and  valid program ID Program
      #When User sends HTTPS DELETE Request Program
      #Then User receives 200 Ok status with message Program
      #
      #@programTest16_DELETE_invalidID
      #Scenario: Check if user able to delete a program with valid LMS API,invalid program ID 
      #Given User creates DELETE Request for the LMS API endpoint  and  invalid program ID Program
      #When User sends HTTPS DELETE Request Program
      #Then User receives 404 Not Found Status with message and boolean success details Program
      #
#
#
#
#
    