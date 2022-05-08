package assignments;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Assignment1 {
	
	@Test //no request body
	public void sendPostRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		Response postResponse = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.queryParam("sysparm_fields", "sys_id, number, category")
				.when()
				.post();
		
		System.out.println("Status Code: "+postResponse.statusCode());
		postResponse.prettyPrint();
		String sys_id = postResponse.jsonPath().get("result.sys_id");
		System.out.println("sys_id: "+ sys_id);
	}

}
