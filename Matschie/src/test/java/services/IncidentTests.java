package services;

import static org.hamcrest.Matchers.containsString;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class IncidentTests extends BaseRequest{
	
	
	@Test
	public void getIncidents(){
		request
			.contentType(ContentType.JSON)
			.get("incident")
			.then()
			.assertThat()
			.statusCode(200)
			.body(containsString("number"),containsString("sys_id"))
			/*.extract().response()
			.prettyPrint()*/;	
	}
}
