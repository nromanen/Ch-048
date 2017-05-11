package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.patient.CardPage;
import utils.BrowserWrapper;


/**
 * Created by Evgen on 06.04.2017.
 */
public class PatientHeader extends AuthorizedHeader implements PageInitializer {

    public PatientHeader() {
        pageInitialization();
    }

    @FindBy(xpath = "//li[4]/a")
    private WebElement actions;

    @FindBy(css = "a[href=\"/HospitalSeeker/card\"]")
    private WebElement card;

    @FindBy(css = "a[href=\"/HospitalSeeker/appointments\"]")
    private WebElement appointments;

    @FindBy(css = "a[href=\"/HospitalSeeker/laboratory\"]")
    private WebElement studies;

    @FindBy(linkText = "Thomas Auginas")
    private WebElement patientProfileName;

    public CardPage goToCardPage() {
        BrowserWrapper.sleep(1);
        BrowserWrapper.waitUntilElementClickable(actions);
        actions.click();
//        BrowserWrapper.waitUntilElementClickable(card);
        card.click();
        return new CardPage();
    }

    public boolean isActionsButtonPresent() {
        return BrowserWrapper.isElementPresent(actions);
    }

    public String getPatientProfileName() {
       return patientProfileName.getText();
    }

}
