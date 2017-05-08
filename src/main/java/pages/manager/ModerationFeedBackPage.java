package pages.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.ManagerHeader;
import utils.BrowserWrapper;


/**
 * Created by radga on 06.04.2017.
 */
public class ModerationFeedBackPage implements PageInitializer {
    public ManagerHeader managerHeader;

    @FindBy(css = "input[value=\"remove\"]")
    private WebElement removeAfterDecideButton;

    @FindBy(css = "input[value=\"NEW\"]")
    private WebElement newButton;

    @FindBy(css = "input[value=\"OK\"]")
    private WebElement okButton;

    @FindBy(css = "input[value=\"BAD\"]")
    private WebElement badButton;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    //Feedback 1

    @FindBy(xpath = "//*[@id=\"2\"]/div[1]/span")
    private WebElement patientOneName;

    @FindBy(xpath = "//*[@id=\"2\"]/div[1]/div/span")
    private WebElement patienOneDateOfCreation;

    @FindBy(xpath = "//*[@id=\"2\"]/div[2]")
    public WebElement patientOneFeedbackBody;

    @FindBy(xpath = "//*[@id=\"2\"]/div[3]/div/label[1]")
    private WebElement patientOneOkButton;

    @FindBy(xpath = "//*[@id=\"2\"]/div[3]/div/label[2]")
    private WebElement patientOneBlockButton;

    @FindBy(xpath = "//*[@id=\"2\"]/div[3]/span")
    private WebElement patientOneDoctorName;

    //Feedback 2

    @FindBy(xpath = "//*[@id=\"5\"]/div[1]/span")
    private WebElement patientTwoName;

    @FindBy(xpath = "//*[@id=\"5\"]/div[1]/div/span")
    private WebElement patienTwoDateOfCreation;

    @FindBy(xpath = "//*[@id=\"5\"]/div[2]")
    private WebElement patientTwoFeedbackBody;

    @FindBy(xpath = "//*[@id=\"5\"]/div[3]/div/label[1]")
    private WebElement patientTwoOkButton;

    @FindBy(xpath = "//*[@id=\"5\"]/div[3]/div/label[2]")
    private WebElement patientTwoBlockButton;

    @FindBy(xpath = "//*[@id=\"5\"]/div[3]/span")
    private WebElement patientTwoDoctorName;




    @FindBy(xpath = "/html/body/section/a")
    private WebElement backToTopButton;


    public ModerationFeedBackPage  confirmFeedback(){
        patientOneOkButton.click();
        BrowserWrapper.refreshPage();
        return new ModerationFeedBackPage();
    }

    public ModerationFeedBackPage(){
        managerHeader = new ManagerHeader();
        pageInitialization();
    }
}
