package bdd.steps;

import java.io.File;
import java.util.Map;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateIncidentSteps {
	
	RequestSpecification inputRequest = null;
	Response response = null;
	
	@Given("Set the end point for Service now Incident Management")
	public void setEndPoint() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
	}
	
	@Given("Set the authentication for Service now application")
	public void setAuthType() {
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
	}
	
	@Given("Set the Queryparam in the request")
	public void setQueryParamInRequest(DataTable dataTable) {
		inputRequest = RestAssured.given().log().all();
		Map<String, String> asMap = dataTable.asMap();
		inputRequest.queryParams(asMap);
	}
	
	@Given("Set the Content type in the create Incident request")
	public void setContentType() {
		inputRequest.contentType(ContentType.JSON);
	}
	
	@Given("Set the accept in the create Incident request")
	public void setAccept() {
		inputRequest.accept(ContentType.JSON);
	}
	
	@Given("Set the request body as {string} in create Incident request")
	public void setRequestBody(String fileName) {
		File jsonInputFile = new File("./src/main/resources/"+fileName);
		inputRequest.body(jsonInputFile);
	}
	
	@When("Send the POST request to Service now Incident Management")
	public void sendPostRequest() {
		response = inputRequest.when().post();
		response.then().log().all();
	}
	
	@When("Send the GET request to Service now Incident Management")
	public void sendGetRequest() {
		response = inputRequest.when().get();
		response.then().log().all();
	}
	
	
	@Then("Validate the status code as {int}")
	public void validateStatusCode(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}
	
	@Then("Validate the response that has number starts with INC")
	public void validateNumberInResponse() {
		response.then().assertThat().body("result.number", Matchers.containsString("INC"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
