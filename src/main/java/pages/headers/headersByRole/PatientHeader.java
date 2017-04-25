package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Evgen on 06.04.2017.
 */
public class PatientHeader extends AuthorizedHeader {



    @FindBy(linkText = "Actions")
    private WebElement actions;

    @FindBy(css = "a[href=\"/HospitalSeeker/card\"]")
    private WebElement card;

    @FindBy(css = "a[href=\"/HospitalSeeker/appointments\"]")
    private WebElement appointments;

    @FindBy(css = "a[href=\"/HospitalSeeker/laboratory\"]")
    private WebElement studies;

}
