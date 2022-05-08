package chain;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseRestImpl {
	
	static RequestSpecification inputRequest = null;
	static Response response = null;
	static String global_sys_id = null;
	
	@BeforeMethod
	public void preConditions() {
		RestAssured.baseURI = "https://dev120669.service-now.com/api/now/table/incident";
		RestAssured.authentication = RestAssured.basic("admin", "r-n5kSXqUX8@");
		inputRequest = RestAssured.given().log().all();
	}
	
	@AfterMethod
	public void postConditions() {
		 System.out.println("Status Code: "+response.statusCode());
		  response.prettyPrint();
	}

}
