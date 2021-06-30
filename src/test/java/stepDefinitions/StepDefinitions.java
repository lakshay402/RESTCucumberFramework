package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import resources.TestDataBuilder;
import resources.commonUtils.RestLogger;
import resources.commonUtils.Utils;

public class StepDefinitions extends Utils {
	
	ObjectMapper objectMapper;
	Response response;
	JsonPath jsonPath;
	TestDataBuilder data = new TestDataBuilder();
	
	public String payload;
	
	@Given("Starting Test Case {string}")
	public void starting_test_case(String testcase_name) {
		 // Write code here that turns the phrase above into concrete actions
		RestLogger.initLogger();
	    RestLogger.startTestCase(testcase_name);
	}

	@Then("Ending TestCase")
	public void ending_test_case() {
		 RestLogger.endTestCase();
	}
	
	@Given("Create Repo Payload name {string} amd description {string}")
	public void create_repo_payload_name_amd_description(String name, String description) throws JsonProcessingException {
		objectMapper = new ObjectMapper();
	    payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data.createRepoPayload(name, description));
	}

	@When("User calls Create API {string} Post API Call")
	public void user_calls_create_api_post_api_call(String resourcePath) {
	   
		response = postRequest(resourcePath, payload);
		
	}

	@Then("API Call got Successful with Status Code {int}")
	public void api_call_got_successful_with_status_code(int status_code) {
	    
		assertEquals(status_code, response.getStatusCode());
		 RestLogger.info("Statuc Cod Verification is Done.... ");
	}

	@Then("Verify Repository {string} is {string}")
	public void verify_repository_is(String response_key, String repo_name) {
	   
		jsonPath = new JsonPath(response.getBody().asString());
		String key_value = jsonPath.get(response_key);
		
		RestLogger.info("Actual repo Name - " + key_value);
		RestLogger.info("Expected repo Name - " + repo_name);
	}

	@When("User calls Delete API {string} Delete Call")
	public void user_calls_delete_api_delete_call(String resourcePath) {
		
		response = deleteRequest(resourcePath, data.getRepoName());
		RestLogger.info("Delete Request Status Code is - " + response.getStatusCode());
	   
	}


}
