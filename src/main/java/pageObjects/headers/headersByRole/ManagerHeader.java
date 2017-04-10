package pageObjects.headers.headersByRole;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.HospitalSeekerHomePage;
import pageObjects.headers.BaseHeader;
import pageObjects.manager.AddNewDoctorPage;
import pageObjects.manager.DepartmentsPage;
import pageObjects.manager.HospitalsPage;
import pageObjects.manager.ModerationFeedBackPage;

/**
 * Created by Evgen on 06.04.2017.
 */
public class ManagerHeader extends BaseHeader{

    public ManagerHeader(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/a")
    private WebElement actions;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[1]/a")
    private WebElement hospitals;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[2]/a")
    private WebElement addDoctor;

    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[4]/ul/li[3]/a")
    private WebElement feedbacks;


    @FindBy(xpath = "/html/body/nav/div[1]/div[2]/ul/li[5]/a")
    private WebElement profile;

    @FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[5]/a")
    private WebElement myProfile;

    @FindBy(xpath = "//*[@id=\"dropdawn\"]/li[2]/a")
    private WebElement logOut;

    public void logOut() throws InterruptedException {
        myProfile.click();
        Thread.sleep(3000);
        logOut.click();
    }

    public AddNewDoctorPage addNewDoctorPage() {
        addDoctor.click();
        return new AddNewDoctorPage(driver);
    }

    public HospitalsPage managePage() {
        hospitals.click();
        return new HospitalsPage(driver);
    }

    public ModerationFeedBackPage feedBackPage() {
        feedbacks.click();
        return new ModerationFeedBackPage(driver);
    }




}
