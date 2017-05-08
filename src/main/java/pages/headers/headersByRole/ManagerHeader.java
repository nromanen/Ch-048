package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.manager.AddNewDoctorPage;
import pages.manager.ManagerDashBordPage;
import pages.manager.ModerationFeedBackPage;


/**
 * Created by Evgen on 06.04.2017.
 */
public class ManagerHeader extends AuthorizedHeader implements PageInitializer {

    public ManagerHeader() {
        pageInitialization();
    }

    @FindBy(linkText = "Actions")
    private WebElement actions;

    @FindBy(css = "a[href=\"/HospitalSeeker/manage/hospitals]")
    private WebElement hospitals;

    @FindBy(css = "a[href=\"/HospitalSeeker/newDoctor]")
    private WebElement addDoctor;

    @FindBy(css = "a[href=\"/HospitalSeeker/moderationFeedbacks]")
    private WebElement feedbacks;


    public AddNewDoctorPage addNewDoctorPage() {
        addDoctor.click();
        return new AddNewDoctorPage();
    }

    public ManagerDashBordPage managePage() {
        hospitals.click();
        return new ManagerDashBordPage();
    }

    public ModerationFeedBackPage feedBackPage() {
        feedbacks.click();
        return new ModerationFeedBackPage();
    }




}
