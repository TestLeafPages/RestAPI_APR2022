package chain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetRequestWithMultipleQP extends BaseRestImpl {

	@Test(dependsOnMethods = "chain.CreateRequestWithFile.sendPostRequest")
	public void sendGetRequest() {

		Map<String, String> allQueryParams = new HashMap<String, String>();
		allQueryParams.put("category", "Software");
		allQueryParams.put("sysparm_fields", "sys_id, number, category");

		// Adding the queryparam in the input using given
		response = inputRequest
							.queryParams(allQueryParams)
							.accept(ContentType.XML)
							.when()
							.get();

		// List<String> allSys_id = response.jsonPath().getList("result.sys_id");
		List<String> allSys_id = response.xmlPath().getList("response.result.sys_id");
		System.out.println("Sys_ID: " + allSys_id);

	}

}
