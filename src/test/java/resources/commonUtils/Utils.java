package resources.commonUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	RequestSpecification reqSpecification;
	Response response;

	String bearer_token = "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";
	String base_URI = "https://api.github.com";

	public Response postRequest(String resourcePath, String payload) {
		RestLogger.info("Base URI is - " + base_URI);
		RestLogger.info("Resource Path is - " + resourcePath);
		RestLogger.info("Request Payload is - " + payload);
		reqSpecification = RestAssured.given().body(payload);
		reqSpecification.contentType(ContentType.JSON);
		reqSpecification.header("Authorization", "Bearer " + bearer_token);
		response = reqSpecification.post(base_URI + resourcePath);
		RestLogger.info("Respose of Requst is - " + response.getBody().asString() );
		return response;
	}

	public Response deleteRequest(String resourcePath, String repo_name) {
		String requestURI = base_URI + resourcePath + repo_name;
		RestLogger.info("Dlete URI is - " + requestURI );		
		reqSpecification = RestAssured.given();
		reqSpecification.contentType(ContentType.JSON);
		reqSpecification.header("Content-Type", "application/json");
		reqSpecification.header("Authorization", "Bearer " + bearer_token);
		response = reqSpecification.delete(requestURI);
		return response;
	}

}
