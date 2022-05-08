package bdd.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/java/bdd/features/Jira_Assignment.feature", 
glue = "bdd.steps")
public class RunCukes extends AbstractTestNGCucumberTests {
	
	//features - Path of the feature file to be executed
	//glue - Package name of the impl class

}
