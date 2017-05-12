package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.manager.AddNewDoctorPage;
import pages.manager.ManagerDashBordPage;


/**
 * Created by Evgen on 06.04.2017.
 */
public class ManagerHeader extends AuthorizedHeader implements PageInitializer {

    public ManagerHeader() {
        pageInitialization();
    }

    @FindBy(css ="div#bs-example-navbar-collapse-1 ul li:nth-child(4)")
    public WebElement actions;

    @FindBy(css = "a[href=\"/HospitalSeeker/manage/hospitals]")
    private WebElement hospitals;

    @FindBy(css = "a[href=\"/HospitalSeeker/newDoctor]")
    private WebElement addDoctor;

    @FindBy(css = "a[href=\"/HospitalSeeker/moderationFeedbacks]")
    public WebElement feedbackManagePage;

    @FindBy(xpath = "//*[@id=\"dropdawn\"]/li[3]/a")
    public WebElement getFeedbackManagePageIco;




    public AddNewDoctorPage addNewDoctorPage() {
        addDoctor.click();
        return new AddNewDoctorPage();
    }

    public ManagerDashBordPage managePage() {
        hospitals.click();
        return new ManagerDashBordPage();
    }




}
