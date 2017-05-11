package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.AdminHeader;
import utils.DriverInitializer;


/**
 * Created by Evgen on 09.04.2017.
 */
public class HospitalsManagersPage implements PageInitializer {

    public AdminHeader header;

    public HospitalsManagersPage() {
        this.header = new AdminHeader();
        pageInitialization();
    }

    public AdminHeader adminHeader;


    @FindBy(css = "tbody")
    private WebElement table;

    public void changeManager(int rowNumber, String name) {
        WebElement selectManager = DriverInitializer.instance().findElement(By.xpath("tbody tr[" + rowNumber + "] td[3] select"));
        selectManager.findElement(By.linkText(name));
    }

    public void deleteManager(int rowNumber) {
      WebElement deleteManager = DriverInitializer.instance().findElement(By.cssSelector("tbody tr[" + rowNumber + "] btn btn-danger"));
      deleteManager.click();
    }
}
