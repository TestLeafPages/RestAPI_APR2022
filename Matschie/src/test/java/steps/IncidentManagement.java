package steps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import java.util.Map;
import java.util.Map.Entry;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.StringUtils;

public class IncidentManagement extends baseAPI{
	

	@Given("enable logs")
	public void setUp(){ 
		request = given().log().all();
	}
	
	@When("short description is added with (.*)$")
	public void add_short_description(String short_desc){
		request = request.when().body("{\"short_description\" : \""+short_desc+"\"}");
	}
	
	@When("description is added with (.*)$")
	public void add_description(String desc){
		request = request.when().body("{\"description\" : \""+desc+"\"}");
	}

	@When("new incident is created")
	public void a_new_incident_created(){
		response = request.when().contentType(ContentType.JSON).post("incident");
		response.prettyPrint();
	}
	
	@When("get all incidents")
	public void get_all_incidents(){
		response = request.when().contentType(ContentType.JSON).get("incident");
		response.prettyPrint();
	}

	@Then("the status code is (\\d+)$")// \d+ ->only Digit += 1 or more number
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}

	@And("response includes the following$")
	public void response_equals(Map<String,String> responseFields){
		
		for (Entry<String, String> eachEntry : responseFields.entrySet()) {
			
			if(StringUtils.isNumeric(eachEntry.getValue())) {
				response
				.then()
				.body(eachEntry.getKey(), equalTo(Integer.parseInt(eachEntry.getValue())));
			} else {
				response
				.then()
				.body(eachEntry.getKey(), equalTo(eachEntry.getValue()));
			}
		}
	}	
	
	
	@And("response includes the following in any order$")
	public void response_contains_in_any_order(Map<String,String> responseFields){
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if(StringUtils.isNumeric(field.getValue())){// checking
				//result.number -> get
				json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
			}
			else{
				json.body(field.getKey(), containsInAnyOrder(field.getValue()));
			}
		}
	}
}


