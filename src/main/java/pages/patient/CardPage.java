package pages.patient;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.PatientHeader;
import utils.BrowserWrapper;


/**
 * Created by gregtar on 06.04.17.
 */
public class CardPage implements PageInitializer {

    PatientHeader patientHeader;
    @FindBy(id = "headingOne")
    private WebElement diagnosisLabel;

     @FindBy(xpath = "//*[@id=\"headingOne\"]/h4/a")
     private WebElement diagnosisTimeLink;

     @FindBy(css = "#headingOne > h4 > span")
     private WebElement doctorNameText;

     @FindBy(css = ".col-md-8 > h1:nth-child(1) > span:nth-child(1)")
     private WebElement patientCardText;

    @FindBy(id = "accordion")
    private WebElement patientRecordsCount;


    public String getDoctorNameFromNewRecord() {
        return doctorNameText.getText();
    }

     public boolean checkIsRecordPresent() {
         return BrowserWrapper.isElementPresent(diagnosisLabel);
     }

    public int getCountOfRecords() {
        return patientRecordsCount.findElements(By.cssSelector("h4")).size();
    }

    public CardPage() {
        this.patientHeader = new PatientHeader();
        pageInitialization();
    }
}
