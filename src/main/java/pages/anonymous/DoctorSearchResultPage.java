package pages.anonymous;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.allUsers.DoctorInfoPage;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.BrowserWrapper;

import java.util.List;

/**
 * This class describes page with doctor's search result
 *
 * @author ybalatc
 */
public class DoctorSearchResultPage implements PageInitializer {

    public NotAuthorizedHeader notAuthorizedHeader;

    public DoctorSearchResultPage() {
        notAuthorizedHeader = new NotAuthorizedHeader();
        pageInitialization();
    }

    @FindBy(css = ".card.panel.panel-default.text-xs-right")
    private List<WebElement> doctorNameAtList;

    @FindBy(css = "div.about-img > a > img.img-responsive")
    public WebElement firstDoctorImage;

    /** Method is used for counting of searched hospitals by param
     *
     * doctorNameAtList are names and surnames of doctors that have been found
     * @return count of doctors that have been found
     */

    public int countOfDoctors() {
        return doctorNameAtList.size();
    }

    /**
     * Method is used for going to Page with info of chosen Doctor
     *
     * firstDoctorImage is image of Doctor
     * @return Page with info of chosen Doctor
     */

    public DoctorInfoPage goToDoctorInfoPage() {
        BrowserWrapper.sleep(3);
        BrowserWrapper.waitUntilElementVisible(firstDoctorImage);
        firstDoctorImage.click();
        BrowserWrapper.sleep(5);
        return new DoctorInfoPage();
    }
}
