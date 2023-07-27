Feature: Positive and negative scenarios for User module
Background: User sets authorisation to no authorisation


  @usermoduleTest1_POSTwithvalidendpoint
  Scenario: Create a User with valid endpoint and request body USR
    Given User sends POST request to create a new user with mandatory fields USR
    When User sends CreateUserinUserModule with POST HTTP request USR
    Then User receives 201 OK Created status code USRM
    
     
   #@usermoduleTest2_POSTwithinvalidPhoneNo
  #Scenario: User is not able to create a User with already existing phonenumber   
    #Given User sends POST request to create a new user with already exiting userPhoneNumber
    #When User sends HTTP POST existingph request USRM
    #Then User receives 400 Bad Request status code USRM
#	
#	@usermoduleTest3_POSTwithmissingMandatoryFields
  #Scenario: User is not able to create a User with missing mandatory fields   
    #Given User sends POST request for creating a user with missing mandatory fields in the request body USRM
    #When User sends HTTP request USRM
    #Then User receives 400 Bad Request status code USRM
    
	@usermoduleTest4_GETAllUsers
  Scenario: User is able to get all User data with valid LMS api endpoint AllUsers   
    Given User creates GET request for LMS api endpoint AllUsers USRM
    When User sends HTTP GET request USRM
    Then User receives 200 OK status code USRM

	@usermoduleTest5_GETValidUserID
  Scenario: User is able to retrieve a User data with valid UserID   
    Given User creates GET request for LMS api endpoint with valid UserId USRM
    When User sends HTTP GET request USRM
    Then User receives 200 OK status code USRM

  @usermoduleTest6_GETInvalidUserID
  Scenario: User is not able to retrieve User data with invalid UserID   
    Given User creates GET request for LMS api endpoint with invalid UserId USRM
    When User sends HTTP GET request USRM
    Then User receives 404 Not Found status code USRM

	@usermoduleTest7_GETAllStaff
  Scenario: User is able to retrieve all staff user data with valid All Staff endpoint   
    Given User creates GET request for LMS api endpoint AllStaff USRM
    When User sends HTTP GET request USRM
    Then User receives 200 OK status code USRM

	@usermoduleTest8_GETValidUserRoles
  Scenario: User is able to retrieve Users roles data with valid User Roles endpoint   
    Given User creates GET request for LMS api endpoint User Roles USRM
    When User sends HTTP GET request USRM
    Then User receives 200 OK status code USRM
    #
  #@usermoduleTest9_PUTValidUserID
  #Scenario: User is able to update a User with valid UserID endpoint   
    #Given User sends PUT request to LMS api endpoint with valid User Id USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 200 OK status code USRM
#
#	@usermoduleTest10_PUTInvalidUserID
  #Scenario: User is not able to update a User with invalid UserID   
    #Given User sends PUT request to LMS api endpoint with invalid User Id USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 404 NotFound status code USRM
#
#	@usermoduleTest11_PUTMissingFieldsUserID
  #Scenario: User is not able to update a User with valid UserID and missing mandatory fields in request body   
    #Given User sends PUT request with valid UserID and missing mandatory fields in request body USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 400 Bad request status code USRM
#
#	@usermoduleTest12_PUTValidIDUserRoleStatus
  #Scenario: User is able to update a User Role Status with valid UserID   
    #Given User sends PUT request to LMS api endpoint User Role Status with valid User Id USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 200 OK status code USRM
#
#	@usermoduleTest13_PUTInvalidIDUserRoleStatus
  #Scenario: User is not able to update a User Role Status with invalid UserID   
    #Given User sends PUT request to LMS api endpoint User Role Status with invalid User Id USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 404 Not Found status code USRM
   #
  #@usermoduleTest14_PUTMissingFieldsUserRoleStatusID
  #Scenario: User is not able to update a User Role Status with valid UserID and missing mandatory fields in request body   
    #Given User sends PUT request with missing mandatory fields in request body for User Role Status endpoint USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 400 Bad Request status code USRM
#
 #@usermoduleTest15_AssignValidUserIDtoProgram
  #Scenario: User is able to successfully assign a valid User ID and request body to Program or Batch   
    #Given User sends PUT request to assign user to Program or Batch with valid Id and request body USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 200 OK status code USRM
#
#	@usermoduleTest16_InvalidIDAssigntoProgram
  #Scenario: User is not able to assign a User to Program or Batch with an invalid User ID and request body
    #Given User sends PUT request to assign user to Program or Batch with invalid Id and request body USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 404 Not Found status code USRM
   #
  #@usermoduleTest17_MissingFieldsAssignUsertoProgram
  #Scenario: User is not able to assign a User to Program or Batch with valid UserID and missing mandatory fields in request body   
       #Given User sends PUT request to assign user to Program or Batch with valid Id and missing mandatory fields USRM
    #When User sends PUT HTTP request USRM
    #Then User receives 400 Bad Request status code USRM
   #
     
  