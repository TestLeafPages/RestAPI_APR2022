package assignments;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Assignment2 {
	
	@Test
	public void sendPostRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		File jsonInputFile = new File("./src/main/resources/CreateRequest.json");
		Response postResponse = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.XML)
				.queryParam("sysparm_fields", "sys_id, number, category")
				.body(jsonInputFile)
				.when()
				.post();
		
		System.out.println("Status Code: "+postResponse.statusCode());
		postResponse.prettyPrint();
	}

}
