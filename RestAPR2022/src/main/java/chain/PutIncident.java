package chain;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutIncident extends BaseRestImpl {
	
	@Test(dependsOnMethods = "chain.CreateRequestWithFile.sendPostRequest")
	public void sendPutRequest() {
		
		response = inputRequest
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.queryParam("sysparm_fields", "sys_id, number, category, short_description, description")
					.body("{\"category\":\"hardware\","
										+ "\"short_description\":\"updated via PUT request\"}")
					.when()
					.put(global_sys_id);
	}

}
