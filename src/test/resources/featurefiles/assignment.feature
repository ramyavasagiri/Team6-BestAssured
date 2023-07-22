
@tag
Feature: Program
 
Background: User sets Authoization to No  Auth
  @tag1
  Scenario: Check if user able to retrieve all programs with valid LMS API
  
    Given User creates GET Request for the LMS API endpoint
   
    When User sends HTTPS Request
    
    Then User receives 200 OK Status with response body
    

  
