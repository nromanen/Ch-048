package pages.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.ManagerHeader;
import utils.BrowserWrapper;

import java.util.List;

/**
 * Created by radgast on 22.04.17.
 */
public class HospitalsPage extends BasePage{
    public ManagerHeader managerHeader;
    @FindBy(css = "div.team-heading")
    public WebElement hospitalText;

    @FindAll(@FindBy(css = "div.single-member"))
    public List<WebElement> allHospitals;

    @FindBy(css = "back-to-top")
    public WebElement backToTopButton;

    public ManagerDashBordPage choseHospital(String name){

        for (WebElement element : allHospitals){
            BrowserWrapper.waitUntilElementVisible(element);
            if(element.findElement(By.tagName("h3")).getText().equals(name)){
                element.click();
                break;
            }
        }
        return new ManagerDashBordPage();

    }




    public HospitalsPage() {
        managerHeader = new ManagerHeader();
    }
}
