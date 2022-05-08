package week3.day2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutIncident {
	
	@Test
	public void sendPutRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		Response put_response = RestAssured
		.given()
		.log()
		.all()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.queryParam("sysparm_fields", "sys_id, number, category, short_description, description")
		.body("{\"category\":\"hardware\",\"short_description\":\"updated via PUT request\"}")
		.when()
		.put("0ab7f27b978b41103bdf318c1253af19");
		
		System.out.println("Status Code: "+put_response.statusCode());
		put_response.prettyPrint();
	}

}
