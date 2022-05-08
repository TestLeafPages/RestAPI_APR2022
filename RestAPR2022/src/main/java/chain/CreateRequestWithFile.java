package chain;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateRequestWithFile extends BaseRestImpl {
	
	@Test
	public void sendPostRequest() {
		
		File jsonInputFile = new File("./src/main/resources/CreateRequest.json");
		response = inputRequest
								.contentType(ContentType.JSON) // Mandate for Post and PUT
								.accept(ContentType.JSON)
								.queryParam("sysparm_fields", "sys_id, number, category, short_description, description")
								.body(jsonInputFile)
								.when()
								.post();
		
		global_sys_id = response.jsonPath().get("result.sys_id"); 
		System.out.println("sys_id: "+global_sys_id);
		 
	}

}
