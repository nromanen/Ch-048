package pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.AdminHeader;
import utils.DriverInitializer;


public class CheckGooglePOIPage implements PageInitializer {
    public AdminHeader header;

    public CheckGooglePOIPage() {
        this.header = new AdminHeader();
        pageInitialization();
    }

    @FindBy(css = "div.col-sm-8 > button")
    private WebElement getGooglePoiButton;

    public void pushGetGooglePoi() {
        getGooglePoiButton.click();
    }

    public String getTitleOfPage() {
        return DriverInitializer.instance().getTitle();
    }
}
