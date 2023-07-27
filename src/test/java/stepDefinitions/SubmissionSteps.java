package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Assert;

import Cucumber.ScenarioContext;
import Cucumber.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class SubmissionSteps {
	private Response response;
	   
    private RequestSpecification httpRequestPOST;
    private RequestSpecification httpRequestPUT;
    private RequestSpecification httpRequestGET;
    private RequestSpecification httpRequestDELETE;
    String urlToDelete;
  //Read data file
    private JSONObject GetTestDataObject(int index_to_get) throws FileNotFoundException
    {
    	File f = new File("/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/submissionData.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		JSONArray dataObjects = data.getJSONArray("data");
		
		//Random r = new Random();
		
		//String assignemen_name_random_value = "Jul23-Team6-BestAssured-SDET96_ASN_" + r.nextInt(0, 10000);
	
       // ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	
    	//scenarioContext.setContext("savedSubmissionObject", testData);
    	
    	
		
		JSONObject firstObject = dataObjects.getJSONObject(index_to_get);
		
		
		
	
		
		return firstObject;
    }
    
    // Test1 : Same user tries to resubmit 400 expected
    
    @Given("User creates POST Request for the LMS API endpoint SUB")
    public void user_creates_post_request_for_the_lms_api_endpoint_SUB() {
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
		RestAssured.basePath="/assignmentsubmission";
		httpRequestPOST=RestAssured.given().log().all();
    }

    @When("User sends HTTPS Request and  request Body-SUB")
    public void user_sends_https_request_and_request_body_ass() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedSubmissionObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}
    

    @Then("User receives {int} Status with response body SUB")
    public void user_receives_status_with_response_body_SUB(Integer int1) {
    	
    	
    	Assert.assertEquals(400,response.getStatusCode());
    	
    	
    	//Retrieve data from response
    	
    	/*
   	 {
   			"message": "Assignment with ID 3936 already submitted by student U9350. Please visit 'Submissions' to resubmit assignment!",
   			"success": false
     }
   	 */
    	//ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	
    	String assignment_sub_message = response.body().path("message");
    	Assert.assertEquals("Assignment with ID 3936 already submitted by student U9350. Please visit 'Submissions' to resubmit assignment!",assignment_sub_message);
    	
    	System.out.println("******"+assignment_sub_message);
    	
    }
    
    
    //Test2 :Positive: Successful creation of submission
    
    @When("User sends HTTPS Request and new request Body-SUB")
    public void user_sends_https_request_and_new_request_body_sub() {
    	
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	
    	String userId = (String)scenarioContext.getContext("userId");
		
       
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(1);
			  testData.put("userId", userId);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	scenarioContext.setContext("savedSubmissionObject", testData);
    
    	
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}
    

    	
    

    @Then("User receives {int} success Created Status with response body SUB")
    public void user_receives_success_created_status_with_response_body_sub(Integer int1) {
    	
    	Assert.assertEquals(201,response.getStatusCode());
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	int assignment_sub_Id = response.body().path("submissionId");
    	scenarioContext.setContext("submissionId", assignment_sub_Id);
    	System.out.println("******************Assignment submissionId Id is " + assignment_sub_Id);
    	
        
    }
    
    //Test 3: Post submission with missing mandatory fields
    
    

    @When("User calls SubmitAssignmentMissingMandatoryField with POST http request SUB")
    public void user_calls_submit_assignment_missing_mandatory_field_with_post_http_request_sub() {
    	
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedSubmissionObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}
        
    

    @Then("User receives {int} Bad Request Status SUB")
    public void user_receives_bad_request_status_sub(Integer int1) {
    	
    	Assert.assertEquals(400,response.getStatusCode());
       
    }
    
    //GET: ALL submissions
    @Given("User creates Request for all submissions the LMS API endpoint SUB")
    public void user_creates_request_for_all_submissions_the_lms_api_endpoint_sub() {
    	
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	    RestAssured.basePath="/assignmentsubmission";
	    httpRequestGET=RestAssured.given();
        
    }

    @When("User calls with http GET request SUB")
    public void user_calls_with_http_request_sub() {
    	response=httpRequestGET.when().get();
    }

    @Then("User receives {int} OK Status code SUB")
    public void user_receives_OK_status_code_SUB(Integer int1) {
    	
    	Assert.assertEquals(200,response.getStatusCode());
        
    }
    
    @Then("User receives {int} Status code SUB")
    public void user_receives_status_code_SUB(Integer int1) {
    	
    	Assert.assertEquals(200,response.getStatusCode());
        
    }
    
    // PUT - valid
   

    @Given("User creates PUT Request for resubmit for the LMS API endpoint with valid request body and all mandatory fields SUB")
    public void user_creates_put_request_for_resubmit_for_the_lms_api_endpoint_with_valid_request_body_and_all_mandatory_fields_SUB() {
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    	RestAssured.basePath="/assignmentsubmission/1562";
 	    httpRequestPUT=RestAssured.given().log().all();
 	    
    }

    @When("User calls UpdateSubmissionResubmitById with Id with PUT http request SUB")
    public void user_calls_update_submission_resubmit_by_id_with_id_with_put_http_request_SUB() {
    	response = 
      			given()
      			.spec(httpRequestPUT)
      			.contentType("application/json")
      			//.body(savedObject.toString())
      			.log().all()
      			.put();
    }
    
    // Delete submission
    
    @Given("User creates delete Request for the LMS API endpoint SUB")
    public void user_creates_delete_request_for_the_lms_api_endpoint_sub() {
    ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
   	int submissionId = (int)scenarioContext.getContext("submissionId");
   	 urlToDelete = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/"+submissionId;
    }

    @When("User calls DeleteSubmissionBySubmissionId with Id with DELETE http request SUB")
    public void user_calls_delete_submission_by_submission_id_with_id_with_delete_http_request_sub() {
    	response = RestAssured
 		  		.given()
 		  		.contentType("application/json")
 		  		.log().all()
 		  		.delete(urlToDelete);
    }


    // End of Collection
    
    //Delete User from USER module
    
    
    @Given("User creates delete Request for the LMS API endpoint for user module")
    public void user_creates_delete_request_for_the_lms_api_endpoint_for_user_module() {
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
       	String userId = (String)scenarioContext.getContext("userId");
       	 urlToDelete = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignmentsubmission/"+userId;;
    }

    @When("User with Id with DELETE http request SUB from user module")
    public void user_with_id_with_delete_http_request_sub_from_user_module() {
    	response = RestAssured
 		  		.given()
 		  		.contentType("application/json")
 		  		.log().all()
 		  		.delete(urlToDelete);
    }
    
}//end of class 
