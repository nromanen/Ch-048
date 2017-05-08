package pages.doctor;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;

public class CreateNewRecordPage implements PageInitializer {
    public DoctorHeader header;
    @FindBy(className = "label[for=\"complaint\"]")
    private WebElement complaintLabel;

    @FindBy (id = "complaint")
    private WebElement complaint;

    @FindBy(css = "a.btn.btn-danger")
    private WebElement backButton;

    @FindBy(css = "button.btn.btn-default")
    private WebElement submitButton;

    @FindBy(className = "<label[for=\"result\"]")
    private WebElement resultLabel;

    @FindBy (id = "result")
    private WebElement result;

    @FindBy (className = "label[for=\"prescription\"]")
    private WebElement prescriptionLabel;

    @FindBy(id = "prescription")
    private WebElement prescription;


    public CreateNewRecordPage() {
        this.header = new DoctorHeader();
        pageInitialization();
    }

    public void inputComplaint(String value){
        complaint.sendKeys(value);
    }

    public void inputResult(String value){
        result.sendKeys(value);
    }

    public void inputPrescription(String value){
        prescription.sendKeys(value);
    }


    public void inputRecord(String complaint, String result, String prescription ){
        inputComplaint(complaint);
        inputResult(result);
        inputPrescription(prescription);
        submitButtonClick();
    }


    public PatientsCardPage backButtonClick (){
        backButton.click();
        return new PatientsCardPage();
    }
    public PatientsCardPage submitButtonClick(){
        submitButton.click();
        return new PatientsCardPage();
    }

}
