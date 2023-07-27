package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features={"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/01program.feature",
				"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/02batch.feature",
				 "/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/03user.feature",
				"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/04assignment.feature",
				
				"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/05submission.feature"
				
				
				
				
		},
		
		
		
		//features={"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/01program.feature"},
		//features={"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/03user.feature"},
		//features={"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/04assignment.feature"},
		//features={"/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/03user.feature","/Users/amruta/eclipse-workspace/team6-RestAssuredBDD/Team6-BestAssured/src/test/resources/featurefiles/05submission.feature"},
		glue ={"stepDefinitions"},
        monochrome=true,
plugin= {"pretty","html:target/cucumber.html"}
//tags= "@"
)        
public class TestRunner {
	
}


