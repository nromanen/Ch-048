package pages.allUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.NotAuthorizedHeader;

import java.util.List;


public class HospitalSearchResultPage implements PageInitializer{
    public NotAuthorizedHeader notAuthorizedHeader;

    public HospitalSearchResultPage() {
        notAuthorizedHeader = new NotAuthorizedHeader();
        pageInitialization();
    }

    @FindBy(css = "[class='filter-col'])")
    private WebElement hospitalPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfHospitalsPerPage;

    @FindBy(css = ".card.panel.panel-default.text-xs-right")
    private List<WebElement> hospitalNameAtList;

    @FindBy(css = "[class='about-img']")
    private List<WebElement> hospitalPhotoAtList;

    @FindBy(css = "[class='img-responsive']")
    private List<WebElement> hospitalLogoAtList;

    @FindBy(css = ".cd-top")
    private WebDriver onTop;

    @FindBy(css = "[class='pagination pagination-lg']")
    private WebDriver pageNavigation;

    public int countOfHospital() {
        return hospitalNameAtList.size();
    }
}
