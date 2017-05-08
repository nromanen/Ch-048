package pages.doctor;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;
import utils.BrowserWrapper;

public class PatientsCardPage implements PageInitializer {
    public DoctorHeader header;

    @FindBy(css = "a.btn.btn-info")
    private WebElement addNewRecord;

    @FindBy (xpath = "//*[@id=\"headingOne\"]")
    private WebElement patientRecords;

    @FindBy (xpath = "//*[@id=\"headingOne\"]/h4/span[2]/a")
    private WebElement editRecord;

    public PatientsCardPage() {
        this.header = new DoctorHeader();
    }
    public boolean checkRecord(){
        return BrowserWrapper.isElementPresent(patientRecords);
    }
    public void addNewRecordButtonClick (){
        BrowserWrapper.waitUntilElementClickable(addNewRecord);
        addNewRecord.click();
    }
    public void editButtonClick(){
        editRecord.click();
        pageInitialization();
    }
}
