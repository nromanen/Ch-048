package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;


/**
 * This is a class which is take screenshots in case when test fails. It is base on the Selenium listener.
 *
 * @author Yuri Tomko
 * @version 1.0
 */
public class ScreenshotListener extends TestListenerAdapter{

    /**
     * This is a method which create screenshot in the case when method fails
     * @param result its a representation of test which is fails
     */
    @Override
    public void onTestFailure(ITestResult result){
        saveScreenshot(result);
    }

    /**
     * This method is used for saving screenshots in particular format and which particular name
     * @param result its a failed test representation
     * @return its a array of byte representation of screenshot
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(ITestResult result){
        return ((TakesScreenshot) DriverInitializer.instance()).getScreenshotAs(OutputType.BYTES);
    }
}

