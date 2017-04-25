package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.anonymous.LoginPage;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;


/**
 * Created by Evgen on 05.04.2017.
 */
public class NotAuthorizedHeader extends BaseHeader {

    @FindBy(css = "a[href='/HospitalSeeker/login']")
    protected WebElement login;

    public LoginPage loginButton(){
        try {
            BrowserWrapper.waitUntilElementVisible(login);
            login.click();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new LoginPage();
    }



}
