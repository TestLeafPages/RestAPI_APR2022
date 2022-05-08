package week3.day2;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchIncident {
	
	@Test
	public void sendPutRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		File update_json = new File("./src/main/resources/UpdateRequest.json");
		
		Response patch_response = RestAssured
		.given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.queryParam("sysparm_fields", "sys_id, number, category, short_description, description")
		.body(update_json)
		.when()
		.patch("0ab7f27b978b41103bdf318c1253af19");
		
		System.out.println("Status Code: "+patch_response.statusCode());
		patch_response.prettyPrint();
	}

}
