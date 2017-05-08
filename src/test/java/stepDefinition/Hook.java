
package stepDefinition;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import utils.DriverInitializer;

/**
 * Created by radgast on 22.04.17.
 */

public class Hook {
    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";


    @Before
    public void setUp(){
        DriverInitializer.getToUrl(BASE_URL);
    }

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



