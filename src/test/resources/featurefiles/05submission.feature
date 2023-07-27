#Author: Anupama

Feature: Positive and negative scenarios for Assignment Submit module
  
Background: User sets Authoization to No  Auth.

 

   @Tag1[POST_user_already_exist]
    Scenario: Check if user able to create a submission with valid endpoint and request body SUB
  
    Given User creates POST Request for the LMS API endpoint SUB
    
    When User sends HTTPS Request and  request Body-SUB
    
    Then User receives 400 Status with response body SUB
    
    @Tag1[POST_valid_201_success]
    
    
    Scenario: Check if user able to create a submission with valid endpoint and request body new user SUB
  
    Given User creates POST Request for the LMS API endpoint SUB
    
    When User sends HTTPS Request and new request Body-SUB
    
    Then User receives 201 success Created Status with response body SUB
    
    
    @Tag3[POST_missingMandatoryFields]
    Scenario: Check if user able to create a submission missing mandatory fields in request body SUB
  
    Given User creates POST Request for the LMS API endpoint SUB
    
    When User calls SubmitAssignmentMissingMandatoryField with POST http request SUB
    
    Then User receives 400 Bad Request Status SUB
    
     @Tag4[GET_All_Submissions]
    Scenario: Check if user able to retrieve all submission with valid LMS API endpoint SUB
    
    Given User creates Request for all submissions the LMS API endpoint SUB
    
    When User calls with http GET request SUB
    
    Then User receives 200 OK Status code SUB
    
    
     @Tag5[GET_validAssignmentID]
    Scenario: Check if user able to retrieve a grades with valid Assignment ID
    
    Given User creates Request for the LMS API endpoint
 
    When User calls with http GET request SUB 
    
    Then User receives 200 OK Status code SUB
    
    
       @Tag4[GET_All_Submissions]
    Scenario: Check if user able to retrieve all submission with valid LMS API endpoint SUB
    
    Given User creates Request for all submissions the LMS API endpoint SUB
    
    When User calls with http GET request SUB
    
    Then User receives 200 Status code SUB
    
    
    
    @Tag15[PUT_validSubmissionID]
    Scenario: Check if user able to update a submission with valid  submission ID and mandatory request body SUB
    
    Given User creates PUT Request for resubmit for the LMS API endpoint with valid request body and all mandatory fields SUB
 
    When User calls UpdateSubmissionResubmitById with Id with PUT http request SUB
    
    Then User receives 200 Status code SUB
    
    @Tag21[DELETE_validSubID]
    Scenario: Check if user able to delete a submission with valid submission Id SUB
    
    Given User creates delete Request for the LMS API endpoint SUB
    
    When User calls DeleteSubmissionBySubmissionId with Id with DELETE http request SUB
    
    Then User receives 200 Status code SUB
    
    
    @Tag[Delete_USER_from_Storage]
    
    Scenario: Delete user from user module
    
    Given User creates delete Request for the LMS API endpoint for user module
    
    When User with Id with DELETE http request SUB from user module
    
    Then User receives 200 Status code SUB
