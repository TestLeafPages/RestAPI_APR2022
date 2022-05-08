package assignments;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Assignment3 {
	
	@Test
	public void sendPostRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		Response postResponse = RestAssured
				.given()
				.contentType(ContentType.XML)
				.accept(ContentType.XML)
				.queryParam("sysparm_fields", "sys_id, number, category")
				.body("<request>"
						+ "<entry>"
						+ "<description>Created from rest-assured</description>"
						+ "<category>inquiry</category>"
						+ "<short_description>Created with request body as string</short_description>"
						+ "</entry>"
						+ "</request>")
				.when()
				.post();
		
		System.out.println("Status Code: "+postResponse.statusCode());
		postResponse.prettyPrint();
		String sysId = postResponse.xmlPath().get("response.result.sys_id");
		System.out.println("Sys_ID: "+sysId);
	}

}
