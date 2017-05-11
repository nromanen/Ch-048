package pages.doctor;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;
import utils.BrowserWrapper;

public class PatientsCardPage implements PageInitializer {
    public DoctorHeader header;

    @FindBy(css = "a.btn.btn-info")
    private WebElement addNewRecord;

    @FindBy(css = ".col-md-8 > h1:nth-child(1) > a:nth-child(2)")
    private WebElement patientName;

    @FindBy(id = "accordion")
    private WebElement patientRecordsCount;

    @FindBy (css = "#headingOne > h4 > span")
    private WebElement patientRecords;

    @FindBy (css = "div#headingOne a[style=\"float:right\"]")
    private WebElement editRecord;

    public PatientsCardPage() {
        this.header = new DoctorHeader();
        pageInitialization();
    }

    public boolean checkAddNewRecordButton() {
       return BrowserWrapper.isElementPresent(addNewRecord);
    }

    public String getDoctorNameFromRecord() {
        return patientRecords.getText();
    }

    public boolean checkRecord(){
        return BrowserWrapper.isElementPresent(patientRecords);
    }

    public void addNewRecordButtonClick (){
        addNewRecord.click();
    }

    public void editButtonClick(){
        editRecord.click();
    }

    public String getTextFromPatientName() {
       return patientName.getText();
    }

    public int getCountOfRecords() {
        return patientRecordsCount.findElements(By.cssSelector("h4")).size();
    }
}
