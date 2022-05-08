package week3.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteIncident {
	
	@Test
	public void sendDeleteRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		Response delete_response = RestAssured.when().delete("0ab7f27b978b41103bdf318c1253af19");
		System.out.println("Status Code: "+delete_response.statusCode());
		delete_response.prettyPrint();
	}

}
