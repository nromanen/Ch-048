package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.allure.annotations.Attachment;


/**
 * Created by radgast on 26.04.17.
 */
public class ScreenshotListener extends TestListenerAdapter{


    @Override
    public void onTestFailure(ITestResult result){
        saveScreenshot(result);
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot(ITestResult result){
        return ((TakesScreenshot) DriverInitializer.instance()).getScreenshotAs(OutputType.BYTES);
    }
}

