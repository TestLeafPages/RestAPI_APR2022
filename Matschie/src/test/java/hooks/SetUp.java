package hooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;

public class SetUp {
	
	@Before
	public void setUp() throws FileNotFoundException, IOException{ 
		Properties prop = new Properties();
		//load the Data from config.properties
		prop.load(new FileInputStream(new File("./src/test/resources/config.properties")));
		RestAssured.baseURI = "https://"+prop.getProperty("server")+"/"+prop.getProperty("resources")+"/";
		RestAssured.authentication = RestAssured.basic(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@After
	public void tearDown(Scenario sc){ 
		System.out.println(sc.getName()+" : "+sc.getStatus());
	}
}
