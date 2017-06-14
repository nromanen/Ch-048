
package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import utils.BaseTest;

import utils.DriverInitializer;

/**
 * This class provides ability of setting up browser before execution of tests and all actions after test execution,
 *  that is to say: to take screenshot after test failing and close browser after test complete
 *
 *  @author Yuri Tomko
 *  @version 1.0
 */

public class Hook {

    /**
     * This method is used for going to main page of the hospital seeker site
     */
    @Before
    public void setUp(){
        if(System.getProperty("browser.name").equals("grid")) {
            DriverInitializer.getToUrl(BaseTest.BASE_URL_DOCKER);
        }else {
            DriverInitializer.getToUrl(BaseTest.BASE_URL);
        }
    }

    /**
     * This is a method which is execute after each scenario. It takes screenshots after test fail and
     * after test execution it close browser window
     *
     * @param scenario It scenario on which test is fails
     */
    @After
    public void embedScreenshot(Scenario scenario){

        if(scenario.isFailed()) {
            try {
                scenario.write("Current Page URL is " + DriverInitializer.instance().getCurrentUrl());
                byte[] screenshot = ((TakesScreenshot) DriverInitializer.instance()).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }

        }

        DriverInitializer.close();
    }
}