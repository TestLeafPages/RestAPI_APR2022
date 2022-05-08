package week3.day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateRequestWithBody {
	
	@Test //no request body
	public void sendPostRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		Response postResponse = RestAssured
				.given()
				.contentType(ContentType.JSON) // Mandate for Post and PUT
				.accept(ContentType.JSON)
				.queryParam("sysparm_fields", "sys_id, number, category, short_description, description")
				.body("{\"description\":\"My Oracle DB is down\","
						+ "\"category\":\"software\","
						+ "\"short_description\":\"Test RDS - Blocker\"}")
				.when()
				.post();
		
		System.out.println("Status Code: "+postResponse.statusCode());
		postResponse.prettyPrint();
		String sys_id = postResponse.jsonPath().get("result.sys_id");
		System.out.println("sys_id: "+ sys_id);
	}

}
