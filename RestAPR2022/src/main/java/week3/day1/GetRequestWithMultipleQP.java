package week3.day1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequestWithMultipleQP {
	
	@Test
	public void sendGetRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		Map<String, String> allQueryParams = new HashMap<String, String>();
		allQueryParams.put("category", "Software");
		allQueryParams.put("sysparm_fields", "sys_id, number, category");
		
		//Adding the queryparam in the input using given
		Response response = RestAssured
							.given()
							.queryParams(allQueryParams)
							.accept(ContentType.XML)
							.when()
							.get();
		
		response.prettyPrint();
		System.out.println("Status Code: "+response.statusCode());
	//	List<String> allSys_id = response.jsonPath().getList("result.sys_id");
		List<String> allSys_id = response.xmlPath().getList("response.result.sys_id");
		System.out.println("Sys_ID: "+allSys_id);
		
		
		
		
		
		
		
	}

}
