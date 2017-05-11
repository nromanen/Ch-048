package pages.allUsers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.NotAuthorizedHeader;

import java.util.List;

public class HospitalSearchResultPage implements PageInitializer {
    public NotAuthorizedHeader notAuthorizedHeader;

    public HospitalSearchResultPage() {
        notAuthorizedHeader = new NotAuthorizedHeader();
        pageInitialization();
    }

    @FindBy(className = "filter-col")
    private WebElement hospitalPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfHospitalsPerPage;

    @FindBy(css = ".card.panel.panel-default.text-xs-right")
    private List<WebElement> hospitalNameAtList;

    @FindBy(className = "about-img")
    private List<WebElement> hospitalPhotoAtList;

    @FindBy(className = "img-responsive")
    private List<WebElement> hospitalLogoAtList;

    @FindBy(css = ".cd-top")
    private WebElement onTop;

    @FindBy(className = "pagination pagination-lg")
    private WebElement pageNavigation;

    public int countOfHospital() {
        return hospitalNameAtList.size();
    }
}
