package pageObjects.allUsers;

import org.openqa.selenium.WebDriver;
import pageObjects.headers.BaseHeader;

/**
 * Created by Yana on 06.04.2017.
 */
public class DoctorInfo extends PageObject{
    BaseHeader baseHeader;
    public DoctorInfo(WebDriver driver) {
        super(driver);
        baseHeader = new BaseHeader(driver);
    }
}
