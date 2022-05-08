package week3.day2;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class LearnAssertions {
	
	@Test
	public void sendGetRequest() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		
		Map<String, String> allQueryParams = new HashMap<String, String>();
		allQueryParams.put("category", "Software");
		allQueryParams.put("sysparm_fields", "sys_id, number, category");
		
		Response response = RestAssured
							.given()
							.queryParams(allQueryParams)
							.accept(ContentType.JSON)
							.when()
							.get();
				response.prettyPrint();
				//Then converts response to validatable response
				
				// assert the status code
				response.then().assertThat().statusCode(200);
				
				//index known but value partially known -> containsString()
				//response.then().assertThat().body("result[0].number", Matchers.containsString("INC"));
				
				//know the value but index is not known -> hasItem
				//response.then().assertThat().body("result.number", Matchers.hasItem("INC0000015"));
				
				//know the value and know the index
				response.then().assertThat().body("result[0].number", Matchers.equalTo("INC0000007"));
				
	}

}
