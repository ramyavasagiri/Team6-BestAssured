package stepDefinitions;
//import batchMethods.batchMethods;

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
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

public class BatchSteps {
	
	
    private Response response;
   
    private RequestSpecification httpRequestPOST;
    private RequestSpecification httpRequestPUT;
    private RequestSpecification httpRequestGET;
    private RequestSpecification httpRequestDELETE;
    
    String urlToDelete;
    
    
   //Read data file
    private JSONObject GetTestDataObject(int index_to_get) throws FileNotFoundException
    {
    	File f = new File("/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/batchData.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		JSONArray dataObjects = data.getJSONArray("data");
		
		Random r = new Random();
		
		String batch_description_random_value = "Jul23-Team6-BestAssured-SDET96_" + r.nextInt(0, 10000);
		String batch_name_random_value = "Jul23-Team6-BestAssured-RestAssured_" + r.nextInt(0, 10000);
		
		JSONObject firstObject = dataObjects.getJSONObject(index_to_get);
		firstObject.put("batchDescription", batch_description_random_value); 
		firstObject.put("batchName", batch_name_random_value);
		
		return firstObject;
    }
    
    // Valid post request- Expected 201 : Created
    
    @Given("User creates POST Request for the LMS API endpoint")
    public void user_creates_post_request_for_the_lms_api_endpoint() {
    	
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	    RestAssured.basePath="/batches";
	    httpRequestPOST=RestAssured.given().log().all();
	    
      
    }

    @When("User sends HTTPS Request and  request Body with mandatory, additional  fields")
    public void user_sends_https_request_and_request_body_with_mandatory_additional_fields() throws FileNotFoundException {
    	
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObjectbatch", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
    	
       
    }
    
   
    @Then("User receives {int} Created Status with response body")
    public void user_receives_created_status_with_response_body(Integer int1) {
    	
    	Assert.assertEquals(response.getStatusCode(), 201);
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	
    	int batchId = response.body().path("batchId");
    	scenarioContext.setContext("batchId", batchId);
    	System.out.println("******************Batch Id is " + batchId);
    }
    
  // Invalid post request- Create batch which already exist
    
    @When("User sends HTTPS Request and request Body with mandatory, additional  fields with existing batch name")
    public void user_sends_https_request_and_request_body_with_mandatory_additional_fields_Existingbatchname() {
        
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	JSONObject savedObject = (JSONObject)scenarioContext.getContext("savedObjectbatch");
    	
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(savedObject.toString())
    			.log().all()
    			.post();
    }

    @Then("User receives {int} Bad Request Status with message and boolean success details")
    public void user_receives_bad_request_status_with_message_and_boolean_success_details(Integer responseCode) {
    	Assert.assertEquals(responseCode.intValue(), 400);
    }

    
    
    @When("User sends HTTPS Request and request Body with mandatory, additional  fields-existing value in Batch Name")
    public void user_sends_https_request_and_request_body_with_mandatory_additional_fields_existing_value_in_Batch_Name() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
    }

   
    
    

    @When("User sends HTTPS Request and request Body  \\(missing mandatory fields)")
    public void user_sends_https_request_and_request_body_missing_mandatory_fields() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
    }

    @When("User sends HTTPS Request and request Body  \\(missing mandatory fields-BatchName)")
    public void user_sends_https_request_and_request_body_missing_mandatory_fields_batch_name() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(3);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
    }

    @When("User sends HTTPS Request and request Body  \\(missing mandatory fields-ProgramID)")
    public void user_sends_https_request_and_request_body_missing_mandatory_fields_program_id() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(4);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();;
    }

    @When("User sends HTTPS Request and request Body  \\(missing mandatory fields-BatchStatus)")
    public void user_sends_https_request_and_request_body_missing_mandatory_fields_batch_status() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(5);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();;
    }

    @When("User sends HTTPS Request and request Body  \\(missing mandatory fields-NoOfClasses)")
    public void user_sends_https_request_and_request_body_missing_mandatory_fields_no_of_classes() {
    	JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(6);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObject", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
    }
    
    
    // Get All Batches : Expected 200 OK
	
	@Given("User creates GET Request for the LMS API endpoint")
	public void user_creates_get_request_for_the_lms_api_endpoint() {
	    
		RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	    RestAssured.basePath="/batches";
	    httpRequestGET=RestAssured.given();
		
		
	    
	}
	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		response=httpRequestGET.when().get();
		
	   
	}
	@Then("User receives {int} OK Status with response body")
	public void user_receives_ok_status_with_response_body(Integer responseCode) {
		 
		Assert.assertEquals(responseCode.intValue(), 200);
	    
	} 

	// Get batch with batch ID : Expected 200 OK

	@Given("User creates GET Request for the LMS API endpoint with valid Batch ID")
	public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_id() {
		
		RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	    RestAssured.basePath="/batches/batchId/6547";
	    httpRequestGET=RestAssured.given();
	   
	}
	
	//Get batch with Invalid batch ID : Expected 404 not found
	

     @Given("User creates GET Request for the LMS API endpoint with Invalid Batch ID")
     public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_batch_id() {
    
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
  	    RestAssured.basePath="/batches/batchId/7444";
  	    httpRequestGET=RestAssured.given().log().all();
    
     }

     @Then("User receives {int} Not Found Status with response body")
     public void user_receives_not_found_status_with_response_body(Integer responseCode) {
    
    	 Assert.assertEquals(responseCode.intValue(), 404);
     }


     //Check if user able to retrieve a batch with valid BATCH NAME
     
     @Given("User creates GET Request for the LMS API endpoint with valid Batch name")
     public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_batch_name() {
    	 
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
   	    RestAssured.basePath="/batches/batchName/team6-BestAssured-Batch1";
   	    httpRequestGET=RestAssured.given().log().all();
        
     }

     @Given("User creates GET Request for the LMS API endpoint with Invalid BATCH NAME")
     public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_BATCH_NAME() {
    
    	RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
  	    RestAssured.basePath="/batches/batchId/NO10000";
  	    httpRequestGET=RestAssured.given().log().all();
    
     }
     
     
     
    
     @Given("User creates GET Request for the LMS API endpoint with valid Program ID")
     public void user_creates_get_request_for_the_lms_api_endpoint_with_valid_program_id() {
    	 RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    	    RestAssured.basePath="/batches/7494";
    	    httpRequestGET=RestAssured.given().log().all();
     }

     @Given("User creates GET Request for the LMS API endpoint with invalid Program ID")
     public void user_creates_get_request_for_the_lms_api_endpoint_with_invalid_program_id() {
    	 RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    	    RestAssured.basePath="/batches/14999";
    	    httpRequestGET=RestAssured.given().log().all();
     }
     
  // PUT - valid data
     
     @Given("User creates PUT Request for the LMS API endpoint  and Valid batch Id")
     public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_batch_id() {
    	 RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    	 ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	 int batchId = (int)scenarioContext.getContext("batchId");
    	
 	     RestAssured.basePath="/batches/"+batchId;
 	     httpRequestPUT=RestAssured.given().log().all();
    	 
     }

     @When("User sends PUT HTTPS Request and request Body with mandatory, additional fields")
     public void user_sends_put_https_request_and_request_body_with_mandatory_additional_fields() {
         
    	 
    	 ScenarioContext scenariocontext = TestContext.getInstance().getScenarioContext();
    	 JSONObject savedObject = (JSONObject) scenariocontext.getContext("savedObjectbatch");
    	 
    	savedObject.put("batchStatus", "Inactive");
     	response = 
     			given()
     			.spec(httpRequestPUT)
     			.contentType("application/json")
     			.body(savedObject.toString())
     			.log().all()
     			.put();
     }
     
     // PUT - invalid batch

     @Given("User creates PUT Request for the LMS API endpoint  and invalid batch Id")
     public void user_creates_put_request_for_the_lms_api_endpoint_and_invalid_batch_id() {
    	 RestAssured.basePath="/batches/7444";
  	     httpRequestPUT=RestAssured.given().log().all();
     }

     @When("User sends PUT HTTPS Request and request Body with mandatory, additional fields and invalid batch Id")
     public void user_sends_put_https_request_and_request_body_with_mandatory_additional_fields_and_invalid_batch_id() {
        
    	 response = 
      			given()
      			.spec(httpRequestPUT)
      			.contentType("application/json")
      			//.body(savedObject.toString())
      			.log().all()
      			.put();
     }


     @Then("User receives {int} Not Found with response body")
     public void user_receives_not_found_with_response_body(Integer responseCode) {
    	 Assert.assertEquals(responseCode.intValue(), 404);
        
     }
     
  // PUT - Missing mandatory

     @Given("User creates PUT Request for the LMS API endpoint  and Valid batch Id missing mandatory")
     public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_batch_id_missing_mandatory() {
        
    	 ScenarioContext scenariocontext = TestContext.getInstance().getScenarioContext();
    	 JSONObject savedObject = (JSONObject) scenariocontext.getContext("savedObjectbatch");
    	 
    	savedObject.put("batchName", "");
     	response = 
     			given()
     			.spec(httpRequestPUT)
     			.contentType("application/json")
     			.body(savedObject.toString())
     			.log().all()
     			.put();
     }

    
     @When("User sends PUT HTTPS Request and request Body with mandatory, additional fields and missing mandatory")
     public void user_sends_put_https_request_and_request_body_with_mandatory_additional_fields_and_missing_mandatory() {
        
    	 response = 
      			given()
      			.spec(httpRequestPUT)
      			.contentType("application/json")
      			//.body(savedObject.toString())
      			.log().all()
      			.put();
     }

  

    //Delete valid
  
     
     @Given("User creates DELETE Request for batch the LMS API endpoint  and  valid batch ID")
     public void user_creates_delete_request_for_batch_the_lms_api_endpoint_and_valid_batch_ID() {
    	 ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	 int batchId = (int)scenarioContext.getContext("batchId");
    	  urlToDelete = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/batches/"+batchId;
  		
  		
     }

     @When("User sends HTTPS delete Request for batch")
     public void user_sends_https_delete_request_for_batch() {

    	 response = RestAssured
    		  		.given()
    		  		.contentType("application/json")
    		  		.log().all()
    		  		.delete(urlToDelete);
     }
     
    

     @Then("User receives {int} OK status with message")
     public void user_receives_ok_status_with_message(Integer responseCode) {
         // Write code here that turns the phrase above into concrete actions
    	 Assert.assertEquals(responseCode.intValue(), 200);
     }
     
     
     // Delete invalid
     
     @Given("User creates DELETE Request for batch the LMS API endpoint  and  non existant BatchID")
     public void user_creates_delete_request_for_batch_the_lms_api_endpoint_and_non_existant_batch_id() {
         // Write code here that turns the phrase above into concrete actions
    	 ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	 int batchId = (int)scenarioContext.getContext("batchId");
    	 urlToDelete = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms/batches/"+batchId;
   		
     }

     @When("User sends HTTPS Request for batch")
     public void user_sends_https_request_for_batch() {
         // Write code here that turns the phrase above into concrete actions
    	 response = RestAssured
 		  		.given()
 		  		.contentType("application/json")
 		  		.log().all()
 		  		.delete(urlToDelete);
     } 
     
     
     @Then("User receives {int} Not Found Status with message and boolean success details")
     public void user_receives_not_found_status_with_message_and_boolean_success_details(Integer responseCode) {
    	 Assert.assertEquals(responseCode.intValue(), 404);
    	 }



}// Class ends
