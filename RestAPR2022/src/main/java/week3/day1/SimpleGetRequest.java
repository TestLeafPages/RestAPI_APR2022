package week3.day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SimpleGetRequest {
	
	@Test
	public void sendGetRequest() {
		//End-Point
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		
		//Add Request
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		//Send Request
		Response response = RestAssured.get();
		
		//Validate Response
		response.prettyPrint();
		System.out.println("Status Code: "+response.statusCode());
	}
}
