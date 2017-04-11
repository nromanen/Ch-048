package pages.doctor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.PageObject;
import pages.headers.headersByRole.AdminHeader;
import pages.headers.headersByRole.DoctorHeader;

public class EditRecordPage extends PageObject {
    public DoctorHeader header;
    @FindBy (id = "complaint")
    private WebElement complaintTextField;

    @FindBy (id = "result")
    private WebElement resultTextField;

    @FindBy(id = "prescription")
    private WebElement prescriptionTextField;

    @FindBy(css = "a.btn.btn-danger")
    private WebElement backButton;

    @FindBy(css = "button.btn.btn-default")
    private WebElement submitButton;

    public EditRecordPage(WebDriver driver) {
        super(driver);
        this.header =  new DoctorHeader(driver);
    }

    public void modifyComplaint(String value){
        complaintTextField.clear();
        complaintTextField.sendKeys(value);
    }

    public void modifyResult(String value){
        resultTextField.clear();
        resultTextField.sendKeys(value);
    }

    public void modifyPrescription(String value){
        prescriptionTextField.clear();
        prescriptionTextField.sendKeys(value);
    }

     public PatientsCardPage backButtonClick (){
        backButton.click();
        return new PatientsCardPage(driver);
    }
    public PatientsCardPage submitButtonClick(){
        submitButton.click();
        return new PatientsCardPage(driver);
    }
}
