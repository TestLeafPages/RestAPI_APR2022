package bdd.steps;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class JiraIssueSteps {
	
	RequestSpecification inputRequest = null;
	Response response = null;
	static String issue_key = null;
	
	@Given("Set the endpoint for Jira issue management")
	public void set_the_endpoint_for_jira_issue_management() {
	    RestAssured.baseURI = "https://restapijira.atlassian.net/rest/api/2/issue";
	}

	@Given("Set the authentication for JiraAPI access")
	public void set_the_authentication_for_jira_api_access() {
	    RestAssured.authentication = RestAssured.preemptive().basic("RestAPIJira@gmail.com", "511MgvltKp4SMtyRHn2133B0");
	    inputRequest = RestAssured.given().log().all();
	}

	@Given("Set the following headers in the input request")
	public void set_the_following_headers_in_the_input_request(DataTable dt) {
		List<Header> allheadersList = new ArrayList<Header>();
		Map<String, String> headersAsMap = dt.asMap();
		Set<Entry<String, String>> entrySet = headersAsMap.entrySet();
		for (Entry<String, String> entry : entrySet) {
			allheadersList.add(new Header(entry.getKey(), entry.getValue()));
		}
		Headers allHeaders = new Headers(allheadersList);
		inputRequest.headers(allHeaders);
	}

	@Given("Set the request body as {string} in the create issue request")
	public void set_the_request_body_as_in_the_create_issue_request(String fileName) {
		File jsonInputFile = new File("./src/main/resources/"+fileName);
		inputRequest.body(jsonInputFile);
	}

	@When("Send the Post request to the issue management")
	public void send_the_post_request_to_the_issue_management() {
		response = inputRequest.when().post();
		response.then().log().all();
	}

	@Then("Validate the status code is {int}")
	public void validate_the_status_code_is(Integer statusCode) {
	    response.then().assertThat().statusCode(statusCode);
	}

	@Then("validate the response field key contains {string}")
	public void validate_the_response_field_key_contains(String string) {
		response.then().assertThat().body("key", Matchers.containsString("AP"));
		issue_key = response.jsonPath().get("key");
	}
	
	@When("Send the GET request to the issue management")
	public void send_the_get_request_to_the_issue_management() {
		response = inputRequest.when().get(issue_key);
		response.then().log().all();
	}
	
	@Then("Validate the GET response contains the fields with values")
	public void validateGetResponseFields(DataTable dt) {
		Map<String, String> headersAsMap = dt.asMap();
		Set<Entry<String, String>> entrySet = headersAsMap.entrySet();
		for (Entry<String, String> entry : entrySet) {
			response.then().assertThat().body(entry.getKey(), Matchers.equalTo(entry.getValue()));
		}
	}
}
