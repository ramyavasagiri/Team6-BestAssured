package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
		features={"src/test/resources/featurefiles/assignment.feature"},
		glue ={"stepDefinitions"},
        monochrome=true,
plugin= {"pretty","html:target/cucumber.html"},
tags="@tag"
)        
public class TestRunner {
	
}
