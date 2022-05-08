package week3.day1;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestWithQP {
	
	@Test
	public void sendGetRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		//Adding the queryparam in the input using given
		Response response = RestAssured.given().queryParam("category", "software").get();
		
		response.prettyPrint();
		System.out.println("Status Code: "+response.statusCode());
	}

}
