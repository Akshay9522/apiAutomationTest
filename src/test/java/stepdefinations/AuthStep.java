package stepdefinations;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.TokenManager;
import io.cucumber.java.en.*;

public class AuthStep {
    private Response response;
    private String accessToken;

    @Given("I have a valid OAuth 2.0 token")
    public void i_have_a_valid_oauth_token() {
        accessToken = TokenManager.getToken();
        Assert.assertNotNull("Token should not be null", accessToken);
    }

    @When("I send a GET request to fetch Google Drive files")
    public void i_send_a_get_request() {
        response = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .get("https://www.googleapis.com/drive/v3/files");
        
        response.prettyPrint(); // Log Response
    }
    @Then("the response should contain a list of files")
    public void the_response_should_contain_files() {
        Assert.assertTrue(response.getBody().asString().contains("files"));
    }

    @Then("the response should match the {string}")
    public void the_response_should_match_schema(String schemaPath) {
        response.then().assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
    }
}

