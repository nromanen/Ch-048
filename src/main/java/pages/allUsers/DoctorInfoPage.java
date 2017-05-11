package pages.allUsers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;


public class DoctorInfoPage implements PageInitializer {
    BaseHeader baseHeader;

    @FindBy(css = "img.img-thumbnail")
    private WebElement avatarImage;

    @FindBy(id = "addAppointment")
    private WebElement appointmentButton;

    @FindBy(css = "div.panel-heading text-center")
    private WebElement doctorsNameLabel;

   // @FindBy(css = "div.panel-body")
    @FindBy(xpath = "/html/body/section/div/div[2]/div[3]/div/div/div/div[2]")
    public WebElement doctorInfoLabel;

    @FindBy(css = "div.panel-footer")
    private WebElement doctorsHospitalLabel;

    @FindBy(id = "input-feedback")
  //  @FindBy(xpath = "//*[@id=\"input-feedback\"]")
    public WebElement doctorFeedbackInput;

    @FindBy(id = "sendFeedback")
    private WebElement sendFeedbackButton;

    @FindBy(id = "modal-body-text")
    public WebElement feedbackText;

   // @FindBy(css = "div.panel-body.fixed-panel-body")
    @FindBy(xpath = "//div[3]/div/div/div/div[2]")
    public WebElement infoLabel;


    public DoctorInfoPage feedbackText(){
        infoLabel.click();
        BrowserWrapper.sleep(2);
        System.out.println(feedbackText.getText());
        return new DoctorInfoPage();
    }



    public void createFeedBack(String value){
       // BrowserWrapper.waitUntilElementVisible(doctorFeedbackInput);
        doctorFeedbackInput.clear();
        sendFeedbackButton.sendKeys(value);
        BrowserWrapper.sleep(2);
        sendFeedbackButton.click();
        BrowserWrapper.refreshPage();
        BrowserWrapper.refreshPage();
        BrowserWrapper.sleep(2);
    }



    public DoctorInfoPage() {
        baseHeader = new BaseHeader();
        pageInitialization();
    }
}
