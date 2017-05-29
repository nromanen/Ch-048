package pages.doctor;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;

/**
 * This class represent the page that give us the ability to modify records to the card
 * It's constructed using POM pattern.
 * @author Natalia Shtick
 * @version 1.0
 */


public class EditRecordPage implements PageInitializer {
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

    public EditRecordPage() {
        this.header =  new DoctorHeader();
        pageInitialization();
    }

    /**
     * This method is used for modify field Complaint
     *
     * @param value It's a string representation of complaint
     */
    public void modifyComplaint(String value){
        complaintTextField.clear();
        complaintTextField.sendKeys(value);
    }
    /**
     * This method is used for modify field Result
     *
     * @param value It's a string representation of result
     */
    public void modifyResult(String value){
        resultTextField.clear();
        resultTextField.sendKeys(value);
    }
    /**
     * This method is used for modify field Prescription
     *
     * @param value It's a string representation of prescription
     */
    public void modifyPrescription(String value){
        prescriptionTextField.clear();
        prescriptionTextField.sendKeys(value);
    }
    /**
     * This method is used to return to the previous page
     *
     * @return Its a page patient card.
     */
     public PatientsCardPage backButtonClick (){
        backButton.click();
        return new PatientsCardPage();
    }
    /**
     * This method is used  to save the recording in the patient record
     *
     * @return Its a page patient card.
     */
    public PatientsCardPage submitButtonClick(){
        submitButton.click();
        return new PatientsCardPage();
    }
}
