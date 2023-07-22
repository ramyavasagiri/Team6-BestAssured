package stepDefinitions;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
public class Programsteps
{
	private String baseURL;
    private String endpoint;
    private Response response;
    private RequestSpecification httpRequest;
@Given("User creates GET Request for the LMS API endpoint")
public void user_creates_get_request_for_the_lms_api_endpoint() {
   RestAssured.baseURI="https://lms-api-hackathon-june2023-930a8b0f895d.herokuapp.com/lms";
    RestAssured.basePath="/allPrograms";
httpRequest=RestAssured.given();
}

@When("User sends HTTPS Request")
public void user_sends_https_request() {
    //response=httpRequest.when().get(baseURL+endpoint);
	response=httpRequest.when().get();
}
@Then("User receives {int} OK Status with response body")
public void user_receives_ok_status_with_response_body(Integer int1) {
    int statuscode=response.getStatusCode();
    System.out.println("statuscode:"+statuscode);
    System.out.println(response.asPrettyString());
    Assert.assertEquals(statuscode, int1.intValue());
}
}
