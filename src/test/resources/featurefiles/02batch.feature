#Amruta

@tag
Feature: Batch

  Background: User sets Authorization to No Auth

  @batchTest1_POST_NewBatch
  Scenario: Check if user able to create a Batch with valid endpoint and request body (non existing values)
    Given User creates POST Request for the LMS API endpoint
    When User sends HTTPS Request and  request Body with mandatory, additional  fields
    Then User receives 201 Created Status with response body

  @batchTest2_POST_ExisitingBatchName
  Scenario: Check if user able to create a Batch with valid endpoint and request body-existing value in Batch Name
    Given User creates POST Request for the LMS API endpoint
    When User sends HTTPS Request and request Body with mandatory, additional  fields with existing batch name
    Then User receives 400 Bad Request Status with message and boolean success details
    
    @batchTest3_POST_MissingMandatoryFields
     
    Scenario: Check if user able to create a Batch missing mandatory fields in request body
    
    Given User creates POST Request for the LMS API endpoint
   
    When User sends HTTPS Request and request Body  (missing mandatory fields)
    
    Then User receives 400 Bad Request Status with message and boolean success details
    
    
    
    @batchTest4_POST_MissingMandatoryField-BatchName
     
    Scenario: Check if user able to create a Batch missing mandatory fields in request body
    
    Given User creates POST Request for the LMS API endpoint
   
    When User sends HTTPS Request and request Body  (missing mandatory fields-BatchName)
    
    Then User receives 400 Bad Request Status with message and boolean success details
    
    
    @batchTest5_POST_MissingMandatoryField-ProgramID
     
    Scenario: Check if user able to create a Batch missing mandatory fields in request body
    
    Given User creates POST Request for the LMS API endpoint
   
    When User sends HTTPS Request and request Body  (missing mandatory fields-ProgramID)
    
    Then User receives 400 Bad Request Status with message and boolean success details
    
    
    @batchTest6_POST_MissingMandatoryField-BatchStatus
     
    Scenario: Check if user able to create a Batch missing mandatory fields in request body
    
    Given User creates POST Request for the LMS API endpoint
   
    When User sends HTTPS Request and request Body  (missing mandatory fields-BatchStatus)
    
    Then User receives 400 Bad Request Status with message and boolean success details
    
    
    
    @batchTest8_GET_AllBatches
    
    Scenario: Check if user able to retrieve all batches with valid LMS API
  
    Given User creates GET Request for the LMS API endpoint
   
    When User sends HTTPS Request
    
    Then User receives 200 OK Status with response body
    
    
    
    @batchTest9_GET_validBatchID
     
    Scenario: Check if user able to retrieve a batch with valid BATCH ID
  
    Given User creates GET Request for the LMS API endpoint with valid Batch ID
   
    When User sends HTTPS Request
    
    Then User receives 200 OK Status with response body
    
    
    
    @batchTest10_GET_InvalidBatchID
     
    Scenario: Check if user able to retrieve a batch with invalid BATCH ID
  
    Given User creates GET Request for the LMS API endpoint with Invalid Batch ID
   
    When User sends HTTPS Request
    
    Then User receives 404 Not Found Status with response body
    
    
    
    @batchTest11_GET_validBatchName
    
    Scenario: Check if user able to retrieve a batch with valid BATCH NAME
  
    Given User creates GET Request for the LMS API endpoint with valid Batch name
   
    When User sends HTTPS Request
    
    Then User receives 200 OK Status with response body
    
    @batchTest12_GET_InvalidBatchName
      
    Scenario: Check if user able to retrieve a batch with invalid BATCH NAME
  
    Given User creates GET Request for the LMS API endpoint with Invalid BATCH NAME
   
    When User sends HTTPS Request
    
    Then User receives 404 Not Found Status with response body
    
    
     @batchTest13_GET_ProgramID
  
     Scenario: Check if user able to retrieve a batch with valid Program ID
  
     Given User creates GET Request for the LMS API endpoint with valid Program ID
   
     When User sends HTTPS Request
    
     Then User receives 200 OK Status with response body
    
    
     @batchTest14_GET_InvalidProgramID
  
     Scenario: Check if user able to retrieve a batch with invalid Program ID
  
     Given User creates GET Request for the LMS API endpoint with invalid Program ID
   
     When User sends HTTPS Request
    
     Then User receives 404 Not Found Status with response body
     
    
   
     @batchTest15_PUT_ValidBatchID
     
     Scenario: Check if user able to update a Batch with valid batchID and mandatory request body
  
     Given User creates PUT Request for the LMS API endpoint  and Valid batch Id
   
     When User sends PUT HTTPS Request and request Body with mandatory, additional fields
    
     Then User receives 200 OK Status with response body
     
     
     @batchTest16_PUT_InValidBatchID
     
     Scenario: Check if user able to update a Batch with valid batchID and mandatory request body invalid batch ID
  
     Given User creates PUT Request for the LMS API endpoint  and invalid batch Id
   
     When User sends PUT HTTPS Request and request Body with mandatory, additional fields and invalid batch Id
    
     Then User receives 404 Not Found with response body
     
     
     
     @batchTest17_PUT_ValidBatchID_Missingmandatory
     
     Scenario: Check if user able to update a Batch with valid batchID and missing mandatory fields request body
  
     Given User creates PUT Request for the LMS API endpoint  and Valid batch Id
   
     When User creates PUT Request for the LMS API endpoint  and Valid batch Id missing mandatory
    
     Then User receives 400 Bad Request Status with message and boolean success details
     
    
    
    
    
  @batchTest18_DELETE_BatchWithvalidBatchID
     
     Scenario: Check if user able to delete a Batch with valid batchID
     
     Given User creates DELETE Request for batch the LMS API endpoint  and  valid batch ID
     
     When User sends HTTPS delete Request for batch
     
     Then User receives 200 OK status with message
     
     
     
     @batchTest19_DELETE_BatchWithinvalidBatchID
     
     Scenario: Check if user able to delete a Batch with invalid BatchID
     
     Given User creates DELETE Request for batch the LMS API endpoint  and  non existant BatchID
     
     When User sends HTTPS Request for batch
     
     Then User receives 404 Not Found Status with message and boolean success details