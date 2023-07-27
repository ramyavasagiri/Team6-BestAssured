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

import Cucumber.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class AssignmentSteps {
	private Response response;
	   
    private RequestSpecification httpRequestPOST;
    private RequestSpecification httpRequestPUT;
    private RequestSpecification httpRequestGET;
    private RequestSpecification httpRequestDELETE;
    String urlToDelete;
  //Read data file
    private JSONObject GetTestDataObject(int index_to_get) throws FileNotFoundException
    {
    	File f = new File("/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/assignmentData.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		JSONArray dataObjects = data.getJSONArray("data");
		
		Random r = new Random();
		
		String assignemen_name_random_value = "Jul23-Team6-BestAssured-SDET96_ASN_" + r.nextInt(0, 10000);
	
		
		JSONObject firstObject = dataObjects.getJSONObject(index_to_get);
		
		firstObject.put("assignmentName", assignemen_name_random_value);
		
		return firstObject;
    }
    
    //Positive: 201 created
    @Given("User creates POST Request for the LMS API endpoint-ASN")
    public void user_creates_post_request_for_the_lms_api_endpoint_ass() {
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
		RestAssured.basePath="/assignments";
		httpRequestPOST=RestAssured.given().log().all();
    }

    @When("User sends HTTPS Request and  request Body-ASN")
    public void user_sends_https_request_and_request_body_ass() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedAssignmentObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}
    

    @Then("User receives {int} Created Status with response body-ASN")
    public void user_receives_created_status_with_response_body_ass(Integer int1) {
    	
    	
    	Assert.assertEquals(201,response.getStatusCode());
    	
    	//Retrieve data from response
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	
    	int assignmentId = response.body().path("assignmentId");
    	scenarioContext.setContext("assignmentId", assignmentId);
    	System.out.println("******************assignmentId Id is " + assignmentId);
    }

    
    //GET All assignments
    
    @Given("User creates GET Request for the LMS API endpoint-Assgnment")
    public void user_creates_get_request_for_the_lms_api_endpoint_assgnment() {
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	    RestAssured.basePath="/assignments";
	    httpRequestGET=RestAssured.given();
    }

    @When("User sends HTTPS GET Request-Assignment")
    public void user_sends_https_request_assignment() {
    	response=httpRequestGET.when().get();
    }

    @Then("User receives {int} OK Status Status-Assignment")
    public void user_receives_ok_status_assignment1(Integer responseCode) {
    	Assert.assertEquals(200,response.getStatusCode());
    }
    //
    @Then("User receives {int} OK Status with response body-Assignment")
    public void user_receives_ok_status_with_response_body_assignment(Integer int1) {
      
    	Assert.assertEquals(200,response.getStatusCode());
    }
    //
    @Then("User receives {int} OK Status-Assignment")
    public void user_receives_ok_status_assignment(Integer int1) {
       
    	Assert.assertEquals(200,response.getStatusCode());
    }
    
    

    //Get by assignment ID
    
    @Given("User creates GET Request for the LMS API endpoint-AssignmentID")
    public void user_creates_get_request_for_the_lms_api_endpoint_assignment_id() {
    	
     ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
   	 int assignmentId = (int)scenarioContext.getContext("assignmentId");
        
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	    RestAssured.basePath="/assignments/"+assignmentId;
	    httpRequestGET=RestAssured.given();
    	
    }
    
    @Given("User creates GET Request for the LMS API endpoint-BATCH ID-Assignment")
    public void user_creates_get_request_for_the_lms_api_endpoint_batch_id() {
    	System.out.println("Scenario: Getting Assignment by BATCH ID");
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    RestAssured.basePath="/assignments/batch/6481";
    httpRequestGET=RestAssured.given();
    }




    @Given("GET Request for the LMS API endpoint with InvalidBatch Id-Assignment")
    public void get_request_for_the_lms_api_endpoint_with_invalid_batch_id_assignment() {
    	 RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    	 RestAssured.basePath="/assignments/3290";
    	 httpRequestGET=RestAssured.given();
    }



    
    //PUT

    @Given("User creates PUT Request for the LMS API endpoint  and Valid Assignment Id-Assignment")
    public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_assignment_id_assignment() {
    	 RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    	 ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	 int assignmentId = (int)scenarioContext.getContext("assignmentId");
    	
 	     RestAssured.basePath="/assignments/"+assignmentId;
 	     httpRequestPUT=RestAssured.given().log().all();
    }

    @When("User sends HTTPS Request and request Body with mandatory and additional  fields-Assignment")
    public void user_sends_https_request_and_request_body_with_mandatory_and_additional_fields_assignment() {
    	ScenarioContext scenariocontext = TestContext.getInstance().getScenarioContext();
   	    JSONObject savedAssignmentObject = (JSONObject) scenariocontext.getContext("savedAssignmentObject");
   	 
   	    savedAssignmentObject.put("Comment", "This comment is updated");
     	response = 
    			given()
    			.spec(httpRequestPUT)
    			.contentType("application/json")
    			.body(savedAssignmentObject.toString())
    			.log().all()
    			.put();
    }

   
    
    //Delete

    @Given("User creates DELETE Request for the LMS API endpoint and valid Assignment Id-Assignment")
    public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_assignment_id_assignment() {
     ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
   	  int assignmentId = (int)scenarioContext.getContext("assignmentId");
   	  urlToDelete = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/assignments/"+assignmentId;
    }

    @When("User sends HTTPS Request-ValidAssignmentID-Assignment")
    public void user_sends_https_request_valid_assignment_id_assignment() {
    	response = RestAssured
		  		.given()
		  		.contentType("application/json")
		  		.log().all()
		  		.delete(urlToDelete);
    }

    


    
    
}