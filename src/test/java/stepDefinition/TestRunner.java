package stepDefinition;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * This is a helper class which is used for running Cucumber tests
 *
 * @author Yuri Tomki
 * @version 1.0
 */
@CucumberOptions(features = "src/test/resources/features/", format = {"pretty",
                                "html:target/site/cucumber-pretty",
                                "rerun:target/rerun.txt",
                                "json:target/cucumber.json"})

/**
 * This method execute all features which are set in the annotation and then from reporting
 */
public class TestRunner extends AbstractTestNGCucumberTests {
}
