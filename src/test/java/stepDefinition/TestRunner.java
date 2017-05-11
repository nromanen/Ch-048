package stepDefinition;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features/CreateFeedbackAboutDoctor.feature", format = {"pretty",
                                "html:target/site/cucumber-pretty",
                                "rerun:target/rerun.txt",
                                "json:target/cucumber.json"})

public class TestRunner extends AbstractTestNGCucumberTests {
}
