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
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;


public class UserSteps {
	private Response response;
	   
    private RequestSpecification httpRequestPOST;
    private RequestSpecification httpRequestPUT;
    private RequestSpecification httpRequestGET;
    private RequestSpecification httpRequestDELETE;
    String urlToDelete;
  //Read data file
    private JSONObject GetTestDataObject(int index_to_get) throws FileNotFoundException
    {
    	File f = new File("/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/userData.json");
		
		FileReader fr = new FileReader(f);
		
		JSONTokener jt = new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		JSONArray dataObjects = data.getJSONArray("data");
		
		Random r = new Random();
		
		int phone_number_random_value = r.nextInt(100000000, 999999999);
		
		
		JSONObject firstObject = dataObjects.getJSONObject(index_to_get);
		
		firstObject.put("userPhoneNumber", phone_number_random_value);
		
		return firstObject;
    }
    
    //Valid:POST Create user successfully
    @Given("User sends POST request to create a new user with mandatory fields USR")
    public void user_sends_post_request_to_create_a_new_user_with_mandatory_fields_usr() {
	
		RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
		RestAssured.basePath="/users/users/roleStatus";
		httpRequestPOST=RestAssured.given().log().all();
				
	}

    @When("User sends CreateUserinUserModule with POST HTTP request USR")
    public void user_sends_create_userin_user_module_with_post_http_request_usr() {
		JSONObject testData = null;
    	try {
			  testData = GetTestDataObject(0);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	scenarioContext.setContext("savedObjectUser", testData);
    	 response = 
    			given()
    			.spec(httpRequestPOST)
    			.contentType("application/json")
    			.body(testData.toString())
    			.log().all()
    			.post();
	}
//User receives 201 OK Created status code USRM
    @Then("User receives {int} OK Created status code USRM")
    
    public void user_receives_status_code_usr(Integer int1) {
		Assert.assertEquals(201,response.getStatusCode());
		//ResponseBody body = response.getBody();
		//System.out.println("Response Body is: " + body.asString());
		
		
    	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
    	String userId = response.body().path("userId");
    	scenarioContext.setContext("userId", userId);
    	System.out.println("******************user Id is " + userId);
	}

//Test2 
    
 /*   
@Then("User receives {int} OK Created status code USR")
public void user_receives_ok_created_status_code_usr(Integer responseCode) {
	Assert.assertEquals(responseCode.intValue(), 200);
}

@Given("User sends POST request to create a new user with already exiting userPhoneNumber")
public void user_sends_post_request_to_create_a_new_user_with_already_exiting_user_phone_number() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleStatus";
	httpRequestGET = RestAssured.given().log().all();
}

@When("User sends HTTP POST existingph request USRM")
public void user_sends_http_POST_existingph_request_usrm() {
	
	
	//response = httpRequestGET.when().get();
	
	JSONObject testData = null;
	try {
		  testData = GetTestDataObject(1);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	ScenarioContext scenarioContext = TestContext.getInstance().getScenarioContext();
	scenarioContext.setContext("savedObjectUser", testData);
	 response = 
			given()
			.spec(httpRequestPOST)
			.contentType("application/json")
			.body(testData.toString())
			.log().all()
			.post();
}

@Then("User receives {int} Bad Request status code USRM")
public void user_receives_bad_Request_status_code_usrm(Integer responseCode) {

	Assert.assertEquals(responseCode.intValue(), 400);

}

@Given("User sends POST request for creating a user with missing mandatory fields in the request body USRM")
public void user_sends_post_request_for_creating_a_user_with_missing_mandatory_fields_in_the_request_body_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleStatus";
	httpRequestGET = RestAssured.given().log().all();
} */
/* 
 * 
 * 
 */
    //GET
@Given("User creates GET request for LMS api endpoint AllUsers USRM")
public void user_creates_get_request_for_lms_api_endpoint_all_users_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users";
	httpRequestGET = RestAssured.given();

}

@When("User sends HTTP GET request USRM")
public void user_sends_http_GET_request_usrm() {
	response=httpRequestGET.when().get();
    throw new io.cucumber.java.PendingException();
}

@Then("User receives {int} OK status code USRM")
public void user_receives_ok_status_code_usrm(Integer responseCode) {
	Assert.assertEquals(responseCode.intValue(), 200);
}

@Given("User creates GET request for LMS api endpoint with valid UserId USRM")
public void user_creates_get_request_for_lms_api_endpoint_with_valid_user_id_usrm() {
	
	
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/U9350";
	httpRequestGET = RestAssured.given();
}

@Given("User creates GET request for LMS api endpoint with invalid UserId USRM")
public void user_creates_get_request_for_lms_api_endpoint_with_invalid_user_id_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/U12345";
	httpRequestGET = RestAssured.given();
}

@Then("User receives {int} Not Found status code USRM")
public void user_receives_Not_Found_status_code_usrm(Integer responseCode) {
	Assert.assertEquals(responseCode.intValue(), 404);
} 

@Given("User creates GET request for LMS api endpoint AllStaff USRM")
public void user_creates_get_request_for_lms_api_endpoint_all_staff_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/getAllStaff";
	httpRequestGET = RestAssured.given();
}

@Given("User creates GET request for LMS api endpoint User Roles USRM")
public void user_creates_get_request_for_lms_api_endpoint_user_roles_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roles";
	httpRequestGET = RestAssured.given();
}


// PUT
/*
@Given("User sends PUT request to LMS api endpoint with valid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_with_valid_user_id_usrm() {

	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/"+userId;
	// this is the base path for the put request for valid User Id
}

@When("User sends PUT HTTP request USRM")
public void user_sends_put_http_request_usrm() {
	response = httpRequestGET.when().get();
}

@Given("User sends PUT request to LMS api endpoint with invalid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_with_invalid_user_id_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/{userId}";
	// this is the base path for the put request for invalid User Id
}

@Then("User receives {int} NotFound status code USRM")
public void user_receives_not_found_status_code_usrm(Integer responseCode) {
	Assert.assertEquals(responseCode.intValue(), 404);
}

@Given("User sends PUT request with valid UserID and missing mandatory fields in request body USRM")
public void user_sends_put_request_with_valid_user_id_and_missing_mandatory_fields_in_request_body_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/{userId}";

}

@Then("User receives {int} Bad Request status code USRM")
public void user_receives_bad_request_status_code_usrm(Integer responseCode) {
	Assert.assertEquals(responseCode.intValue(), 400);

}

@Given("User sends PUT request to LMS api endpoint User Role Status with valid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_user_role_status_with_valid_user_id_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleStatus/{userID}";

	// this is the base path for the put request for User Role Status with valid Id
}

@Given("User sends PUT request to LMS api endpoint User Role Status with invalid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_user_role_status_with_invalid_user_id_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleStatus/{userID}";

	// this is the base path for the put request for User Role Status with invalid
	// Id

}

@Given("User sends PUT request with missing mandatory fields in request body for User Role Status endpoint USRM")
public void user_sends_put_request_with_missing_mandatory_fields_in_request_body_for_user_role_status_endpoint_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleStatus/{userID}";

	// this is the base path for the put request for missing mandatory fields for
	// User Role Status with valid Id
}

@Given("User sends PUT request to assign user to Program or Batch with valid Id and request body USRM")
public void user_sends_put_request_to_assign_user_to_program_or_batch_with_valid_id_and_request_body_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleProgramBatchStatus/{userId}";
	// this is the base path for the put request for assigning updating user role to
	// program or batch
}

@Given("User sends PUT request to assign user to Program or Batch with invalid Id and request body USRM")
public void user_sends_put_request_to_assign_user_to_program_or_batch_with_invalid_id_and_request_body_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleProgramBatchStatus/{userId}";
	// this is the base path for the put request for assigning updating user role to
	// program or batch only change ID to invalid ID
}

@Given("User sends PUT request to assign user to Program or Batch with valid Id and missing mandatory fields USRM")
public void user_sends_put_request_to_assign_user_to_program_or_batch_with_valid_id_and_missing_mandatory_fields_usrm() {
	RestAssured.baseURI = "https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
	RestAssured.basePath = "/users/users/roleProgramBatchStatus/{userId}";
	// this is the base path for the put request for assigning updating user role to
	// program or batch only change ID to valid ID
}



/*
@Given("User sends POST request to create a new user with mandatory fields USR")
public void user_sends_post_request_to_create_a_new_user_with_mandatory_fields_usr() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("User sends CreateUserinUserModule with POST HTTP request USR")
public void user_sends_create_userin_user_module_with_post_http_request_usr() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("User receives {int} status code USR")
public void user_receives_status_code_usr(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

*/
/*
@Then("User receives {int} OK Created status code USR")
public void user_receives_ok_created_status_code_usr(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends POST request to create a new user with already exiting userPhoneNumber")
public void user_sends_post_request_to_create_a_new_user_with_already_exiting_user_phone_number() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}



@Then("User receives {int} Bad Request status code USRM")
public void user_receives_bad_request_status_code_usrm(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends POST request for creating a user with missing mandatory fields in the request body USRM")
public void user_sends_post_request_for_creating_a_user_with_missing_mandatory_fields_in_the_request_body_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User creates GET request for LMS api endpoint AllUsers USRM")
public void user_creates_get_request_for_lms_api_endpoint_all_users_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("User receives {int} OK status code USRM")
public void user_receives_ok_status_code_usrm(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User creates GET request for LMS api endpoint with valid UserId USRM")
public void user_creates_get_request_for_lms_api_endpoint_with_valid_user_id_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User creates GET request for LMS api endpoint with invalid UserId USRM")
public void user_creates_get_request_for_lms_api_endpoint_with_invalid_user_id_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("User receives {int} Not Found status code USRM")
public void user_receives_not_found_status_code_usrm(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User creates GET request for LMS api endpoint AllStaff USRM")
public void user_creates_get_request_for_lms_api_endpoint_all_staff_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User creates GET request for LMS api endpoint User Roles USRM")
public void user_creates_get_request_for_lms_api_endpoint_user_roles_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request to LMS api endpoint with valid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_with_valid_user_id_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@When("User sends PUT HTTP request USRM")
public void user_sends_put_http_request_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request to LMS api endpoint with invalid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_with_invalid_user_id_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("User receives {int} NotFound status code USRM")
public void user_receives_not_found_status_code_usrm(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request with valid UserID and missing mandatory fields in request body USRM")
public void user_sends_put_request_with_valid_user_id_and_missing_mandatory_fields_in_request_body_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("User receives {int} Bad request status code USRM")
public void user_receives_bad_request_status_code_usrm(Integer int1) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request to LMS api endpoint User Role Status with valid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_user_role_status_with_valid_user_id_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request to LMS api endpoint User Role Status with invalid User Id USRM")
public void user_sends_put_request_to_lms_api_endpoint_user_role_status_with_invalid_user_id_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request with missing mandatory fields in request body for User Role Status endpoint USRM")
public void user_sends_put_request_with_missing_mandatory_fields_in_request_body_for_user_role_status_endpoint_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request to assign user to Program or Batch with valid Id and request body USRM")
public void user_sends_put_request_to_assign_user_to_program_or_batch_with_valid_id_and_request_body_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request to assign user to Program or Batch with invalid Id and request body USRM")
public void user_sends_put_request_to_assign_user_to_program_or_batch_with_invalid_id_and_request_body_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Given("User sends PUT request to assign user to Program or Batch with valid Id and missing mandatory fields USRM")
public void user_sends_put_request_to_assign_user_to_program_or_batch_with_valid_id_and_missing_mandatory_fields_usrm() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}


*/
}//class end

