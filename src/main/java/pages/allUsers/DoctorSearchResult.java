package pages.allUsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.headers.BaseHeader;

import java.util.List;


/**
 * Created by Yana on 06.04.2017.
 */
public class DoctorSearchResult extends BasePage {
    private BaseHeader header;

    public DoctorSearchResult(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "[class='filter-col'])")
    private WebElement doctorsPerPage;

    @FindBy(id = "perpage")
    private WebElement numberOfDoctorsPerPage;

    @FindBy(css = ".card.panel.panel-default.text-xs-right")
    private List<WebElement> doctorNameAtList;

    @FindBy(css = ".about-img")
    private List<WebElement> doctorPhotoAtList;

    @FindBy(css = ".img-responsive")
    private List<WebElement> doctorLogoAtList;

    @FindBy(css = ".cd-top")
    private WebDriver onTop;

    @FindBy(css = "[class='pagination pagination-lg']")
    private WebDriver pageNavigation;

    public int countOfDoctors() {
        return doctorNameAtList.size();
    }

}
