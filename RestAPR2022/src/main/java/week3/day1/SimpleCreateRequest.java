package week3.day1;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class SimpleCreateRequest {

	@Test
	public void sendPostRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");

		Response postResponse = RestAssured.given().contentType(ContentType.JSON) // Mandate for Post and PUT
				.accept(ContentType.JSON).when().post();

		System.out.println("Status Code: " + postResponse.statusCode());
		postResponse.prettyPrint();
		String sys_id = postResponse.jsonPath().get("result.sys_id");
		System.out.println("sys_id: " + sys_id);

		  //response schema matches with the file or not
		  
		  postResponse.then().assertThat()
		  .body(JsonSchemaValidator.matchesJsonSchema(new
		  File("./src/main/resources/CreateResponseSchema.json")));
		 

		postResponse.then().assertThat()
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CreateResponseSchema.json"));

	}

}
