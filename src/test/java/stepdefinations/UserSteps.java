package stepdefinations;

import config.APIEndpoints;
import io.cucumber.java.en.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.JSONObject;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import static org.testng.Assert.assertEquals;

import static io.restassured.RestAssured.*;
import java.io.File;

public class UserSteps extends base.BaseTest {
	
	Response response;
	@When("I send a GET request to {string} API")
	public void i_send_a_get_request_to_api(String resource) {
		APIEndpoints resourceAPI=APIEndpoints.valueOf(resource);
		 response = given().spec(reqSpec).when().get(resourceAPI.getPath());
	        ExtentCucumberAdapter.addTestStepLog("Response: " + response.prettyPrint());
	        ExtentCucumberAdapter.addTestStepLog("Status Code: " + response.getStatusCode());
}
	@Then("the response status code should be {int}")
	public void the_response_status_code_should_be(int statusCode) {
		assertEquals(response.getStatusCode(), statusCode);
		ExtentCucumberAdapter.addTestStepLog("Response: " + response.prettyPrint());
        ExtentCucumberAdapter.addTestStepLog("Status Code: " + response.getStatusCode());}

@When("I send a GET request for {string} {string}")
public void i_send_a_get_request_for(String USER_BY_ID, String id) {
	APIEndpoints resourceAPI=APIEndpoints.valueOf(USER_BY_ID);
	 response = given().pathParam("id", id).spec(reqSpec).when().get(resourceAPI.getPath());
	 ExtentCucumberAdapter.addTestStepLog("Response: " + response.prettyPrint());
     ExtentCucumberAdapter.addTestStepLog("Status Code: " + response.getStatusCode());
}

@Then("the response should contain {string} and {string}")
public void the_response_should_contain_and(String expectedName, String expectedEmail) {
	 String actualName = response.jsonPath().getString("name");
     String actualEmail = response.jsonPath().getString("email");
System.out.println(actualName+" "+actualEmail);
ExtentCucumberAdapter.addTestStepLog("Response: " + response.prettyPrint());
ExtentCucumberAdapter.addTestStepLog("Status Code: " + response.getStatusCode());
}

@When("I send a POST request to create a user with name {string} and email {string}")
public void i_send_a_post_request_to_create_a_user_with_name_and_email(String name, String email) {
	JSONObject requestBody = new JSONObject();
    requestBody.put("name", name);
    requestBody.put("email", email);
	APIEndpoints resourceAPI=APIEndpoints.valueOf("CREATE_USER");

    response = given()
            .spec(reqSpec)
            .body(requestBody.toString())
            .when()
            .post(resourceAPI.getPath());

    ExtentCucumberAdapter.addTestStepLog("Request: " + requestBody.toString());
    ExtentCucumberAdapter.addTestStepLog("Response: " + response.prettyPrint());
    ExtentCucumberAdapter.addTestStepLog("Status Code: " + response.getStatusCode());

}
@Then("validate the user schema")
public void validate_the_user_schema() {
	response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("C:\\Users\\Admin\\testAutomation\\apiAutomationTest\\src\\test\\resources\\schemas\\user_schema.json")));

    // Log schema validation result
    ExtentCucumberAdapter.addTestStepLog(" Response Schema validated successfully!");

}
}
