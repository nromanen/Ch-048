package pages.doctor;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;

/**
 * This class represent the page that give us the ability to add new records to the card
 * It's constructed using POM pattern.
 * @author Natalia Shtick
 * @version 1.0
 */

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

    /**
     * This method is used for filling field complaint
     *
     * @param value It's a string representation of complaint
     */
    public void inputComplaint(String value){
        complaint.clear();
        complaint.sendKeys(value);
    }

    /**
     * This method is used for filling field resalt
     *
     * @param value It's a string representation of result
     */
    public void inputResult(String value){
        result.clear();
        result.sendKeys(value);
    }
    /**
     * This method is used for filling field prescription
     *
     * @param value It's a string representation of prescription
     */
    public void inputPrescription(String value){
        prescription.clear();
        prescription.sendKeys(value);
    }

    /**
     * This method is used to fill three String fields
     *
     * @param complaint It's a string representation of complaint
     * @param result It's a string representation of result
     * @param prescription It's a string representation of prescription
     */
    public void inputRecord(String complaint, String result, String prescription ){
        inputComplaint(complaint);
        inputResult(result);
        inputPrescription(prescription);
        submitButtonClick();
    }

    /**
     * This method is used for pressing submit
     *
     * @return Its a page patient card.
     */
    public PatientsCardPage submitButtonClick(){
        submitButton.click();
        return new PatientsCardPage();
    }

}
