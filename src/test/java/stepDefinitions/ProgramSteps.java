


package stepDefinitions;

//import java.io.File;
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

public class ProgramSteps {
	private Response response;
	   
    private RequestSpecification httpRequestPOST;
    private RequestSpecification httpRequestPUT;
    private RequestSpecification httpRequestGET;
    private RequestSpecification httpRequestDELETE;
    String urlToDelete;
  //Read data file
    private JSONObject GetTestDataObject(int index_to_get) throws FileNotFoundException
    {
    	File f = new File("/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/programData.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		JSONArray dataObjects = data.getJSONArray("data");
		
		Random r = new Random();
		
		String program_description_random_value = "Jul23-Team6-BestAssured-SDET96_" + r.nextInt(0, 10000);
		String program_name_random_value = "Jul23-Team6-BestAssured-RestAssured_" + r.nextInt(0, 10000);
		
		JSONObject firstObject = dataObjects.getJSONObject(index_to_get);
		firstObject.put("programDescription", program_description_random_value); 
		firstObject.put("programName", program_name_random_value);
		
		return firstObject;
    }
    
    //Test1: Positive
	@Given("User creates POST Request for the LMS API endpoint program")
	public void user_creates_post_request_for_the_lms_api_endpoint() {
		RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
		RestAssured.basePath="/saveprogram";
		httpRequestPOST=RestAssured.given().log().all();
				
	}

	@When("User sends HTTP POST request for valid program")
	public void user_sends_http_request_for_p1() {
		JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObjectprogram", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}

	@Then("User receives {int} Created Status with response body for program")
	public void User_receives_Created_Status_with_response_body_for_program(Integer responseCode) {
		//Assert.assertEquals(response.getStatusCode(), 201);
		
		Assert.assertEquals(responseCode.intValue(), 201);
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	int programId = response.body().path("programId");
    	scenarioContext.setContext("programId", programId);
    	System.out.println("******************program Id is " + programId);
	}


	//test2
	
	@When("User sends HTTPS Request and  request Body with mandatory , additional  fields for existing Program")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields_for_existing_program() {
		JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	//scenarioContext.setContext("savedObjectprogram", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}




	@Then("User receives {int} Bad Request Status with message and boolean success details for Program")
	public void user_receives_bad_request_status_with_message_and_boolean_success_details_for_program(Integer responseCode) {
	    
		Assert.assertEquals(responseCode.intValue(), 400);
		//Assert.assertEquals(404,responseCode.intValue());
	}

	//Test3 
	@When("User sends http POST request for missing mandatory Program")
	public void user_sends_http_request_for_missing_mandatory_program() {
		JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObjectprogram", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}
	

	//GET
	/*
	@Given("User creates GET Request for the LMS API endpoint Program")
	public void user_creates_get_request_for_the_lms_api_endpoint_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends http GET request for Program")
	public void user_sends_http_get_request_for_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User receives {int} OK Status with response body for Program")
	public void user_receives_ok_status_with_response_body_for_program(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User receives {int} Not Found Status with message and boolean success details for Program")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_for_program(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
*/  	@Given("User creates GET Request for the LMS API endpoint Program")
public void user_creates_get_request_for_the_lms_api_endpoint_program() {
RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    RestAssured.basePath="/allPrograms";
    httpRequestGET=RestAssured.given();

   	}
@When("User sends http GET request for Program")
public void user_sends_http_get_request_for_program() {
response=httpRequestGET.when().get();

   	}
@Then("User receives {int} OK Status with response body for Program")
public void user_receives_ok_status_with_response_body_for_program(Integer responseCode) {
Assert.assertEquals(responseCode.intValue(), 200);

 	}
@Then("User receives {int} Not Found Status with message and boolean success details for Program")
public void user_receives_not_found_status_with_message_and_boolean_success_details_for_program(Integer responseCode) {
{
	Assert.assertEquals(responseCode.intValue(), 404);
}

}
//PUT
/*
	@Given("User creates PUT Request for the LMS API endpoint  and Valid programID Program")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_id_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends HTTPS PUT Request  and  request Body with mandatory , additional  fields for Program")
	public void user_sends_https_put_request_and_request_body_with_mandatory_additional_fields_for_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User receives {int} OK Status with updated value in response body for Program")
	public void user_receives_ok_status_with_updated_value_in_response_body_for_program(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates PUT PUT Request for the LMS API endpoint  and inValid programID Program")
	public void user_creates_put_put_request_for_the_lms_api_endpoint_and_in_valid_program_id_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends HTTPS Request  and  request Body with mandatory,additional  fields for Program")
	public void user_sends_https_request_and_request_body_with_mandatory_additional_fields_for_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends HTTPS PUT Request  and request Body  \\(missing mandatory fields) for Program")
	public void user_sends_https_put_request_and_request_body_missing_mandatory_fields_for_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates PUT Request for the LMS API endpoint  and Valid program Name Program")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_name_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends HTTPS PUT Request  and  request Body with mandatory fields for Program")
	public void user_sends_https_put_request_and_request_body_with_mandatory_fields_for_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates PUT Request for the LMS API endpoint  and inValid program Name Program")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_in_valid_program_name_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates PUT Request for the LMS API endpoint  and  Valid program Name for Program")
	public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_name_for_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends HTTPS PUT Request  and request Body for Program")
	public void user_sends_https_put_request_and_request_body_for_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	} 
	
	*/

//PUT
	
@Given("User creates PUT Request for the LMS API endpoint  and Valid programID Program")
public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_id_program() {
RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
 ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
 int programId = (int)scenarioContext.getContext("programId");
  RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
  RestAssured.basePath="/putprogram/”+programId";
  httpRequestPUT=RestAssured.given().log().all();

	}
@When("User sends HTTPS PUT Request  and  request Body with mandatory , additional  fields for Program")
public void user_sends_https_put_request_and_request_body_with_mandatory_additional_fields_for_program() {
ScenarioContext scenariocontext = TestContext.getInstance().getScenarioContext();
 JSONObject savedObject = (JSONObject) scenariocontext.getContext("savedObjectprogram");
 
savedObject.put("programDescription", "Jul23-Team6-BestReassured-SDET");
	response = 
			given()
			.spec(httpRequestPUT)
			.contentType("application/json")
			.body(savedObject.toString())
			.log().all()
			.put();

	}
@Then("User receives {int} OK Status with updated value in response body for Program")
public void user_receives_ok_status_with_updated_value_in_response_body_for_program(Integer responseCode) {
Assert.assertEquals(responseCode.intValue(), 200);

	}
@Given("User creates PUT PUT Request for the LMS API endpoint  and inValid programID Program")
public void user_creates_put_put_request_for_the_lms_api_endpoint_and_in_valid_program_id_program() {
  	 RestAssured.basePath="/putprogram/789467";
   httpRequestPUT=RestAssured.given().log().all();

}
@When("User sends HTTPS Request  and  request Body with mandatory,additional  fields for Program")
public void user_sends_https_request_and_request_body_with_mandatory_additional_fields_for_program() {
      
 response = 
			given()
			.spec(httpRequestPUT)
			.contentType("application/json")
			//.body(savedObject.toString())
			.log().all()
			.put();

}
@When("User sends HTTPS PUT Request  and request Body  \\(missing mandatory fields) for Program")
public void user_sends_https_put_request_and_request_body_missing_mandatory_fields_for_program() {
response = 
			given()
			.spec(httpRequestPUT)
			.contentType("application/json")
			//.body(savedObject.toString())
			.log().all()
			.put();

}
@Given("User creates PUT Request for the LMS API endpoint  and Valid program Name Program")
public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_name_program() {
RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
 ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
 String programName = (String)scenarioContext.getContext("programName");
   
 RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
 RestAssured.basePath="/putprogram/”+programId";
  //RestAssured.basePath="/putprogram/”+programName;
  httpRequestPUT=RestAssured.given().log().all();


}
@When("User sends HTTPS PUT Request  and  request Body with mandatory fields for Program")
public void user_sends_https_put_request_and_request_body_with_mandatory_fields_for_program() {
ScenarioContext scenariocontext = TestContext.getInstance().getScenarioContext();
 JSONObject savedObject = (JSONObject) scenariocontext.getContext("savedObjectprogram");
 
 String programname="Jul23Team6Bestnotassured";
 savedObject.put("programName", programname);
	response = 
			given()
			.spec(httpRequestPUT)
			.contentType("application/json")
			.body(savedObject.toString())
			.log().all()
			.put();

	}
@Given("User creates PUT Request for the LMS API endpoint  and inValid program Name Program")
public void user_creates_put_request_for_the_lms_api_endpoint_and_in_valid_program_name_program() {
RestAssured.basePath="/putprogram/”+programName";
   httpRequestPUT=RestAssured.given().log().all();
}
//missing fields
@Given("User creates PUT Request for the LMS API endpoint  and  Valid program Name for Program")
public void user_creates_put_request_for_the_lms_api_endpoint_and_valid_program_name_for_program() {
	
	RestAssured.basePath="/putprogram/”+programName";
	   httpRequestPUT=RestAssured.given().log().all();

	}

@When("User sends HTTPS PUT Request  and request Body for Program")
public void user_sends_https_put_request_and_request_body_for_program() {
response = 
			given()
			.spec(httpRequestPUT)
			.contentType("application/json")
			//.body(savedObject.toString())
			.log().all()
			.put();

}


	@Given("User creates DELETE Request for the LMS API endpoint  and  valid programName Program")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_name_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("User sends HTTPS DELETE Request Program")
	public void user_sends_https_delete_request_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User receives {int} Ok status with message Program")
	public void user_receives_ok_status_with_message_program(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid programName Program")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_program_name_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("User receives {int} Not Found Status with message and boolean success details Program")
	public void user_receives_not_found_status_with_message_and_boolean_success_details_program(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  valid program ID Program")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_valid_program_id_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Given("User creates DELETE Request for the LMS API endpoint  and  invalid program ID Program")
	public void user_creates_delete_request_for_the_lms_api_endpoint_and_invalid_program_id_program() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}



	
}

