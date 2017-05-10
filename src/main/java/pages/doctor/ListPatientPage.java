package pages.doctor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;
import utils.BrowserWrapper;
import utils.DriverInitializer;

public class ListPatientPage implements PageInitializer {
    public DoctorHeader header;

    @FindBy(className = "label[for=\"usr\"]")
    private WebElement showPatientsLabel;

    @FindBy(css = "table")
    public WebElement table;

    @FindBy(css = "thead")
    private WebElement tableHead;

    @FindBy(css = "tbody")
    public WebElement tableBody;

    @FindBy(id = "usr")
    private WebElement searchTextField;

    @FindBy(id = "searchButton")
    private WebElement buttonSearch;

    @FindBy(css = "i.glyphicon.glyphicon-refresh")
    private WebElement searchRefresh;

    @FindBy (className = "fa fa-angle-double-left")
    private WebElement goFirstPage;

    @FindBy (className = "fa fa-angle-double-right")
    private WebElement goLastPage;

    @FindBy(css = "a[href=\"/HospitalSeeker/patients?page=1&sortBy=email&order=true\"] i")
    private WebElement sortEmailPatients;

    @FindBy(css = "a[href=\"/HospitalSeeker/patients?page=1&sortBy=firstName&order=true\"] i")
    private WebElement sortByFirstName;

    @FindBy(css = "a[href=\"/HospitalSeeker/patients?page=1&sortBy=lastName&order=true\"] i")
    private WebElement sortByLastName;

    @FindBy(partialLinkText = "patient.ta@hospitals.ua")
    private WebElement patientta;

    @FindBy(partialLinkText = "patient.sf@hospitals.ua")
    private WebElement patientsf;

    public void searchPatients(String value){
        searchTextField.clear();
        searchTextField.sendKeys(value);
        searchButtonClick();
    }
    public boolean checkResultSearch(){
        return BrowserWrapper.isElementPresent(patientsf);
    }

    public ListPatientPage sortByEmailButton(){
        BrowserWrapper.waitUntilElementClickable(sortEmailPatients);
        sortEmailPatients.click();
        return new ListPatientPage();
    }

    public WebElement getEmailPatients(){
        return patientsf;
    }

    public void sortByFirstNameButton(){
        BrowserWrapper.waitUntilElementClickable(sortByFirstName);
        sortByFirstName.click();
        BrowserWrapper.sleep(3);

    }
    public void sortByLastNameButton(){
        BrowserWrapper.waitUntilElementClickable(sortByLastName);
        sortByLastName.click();
        BrowserWrapper.sleep(3);

    }
    public WebDriver getDriver(){
        return DriverInitializer.instance();
    }


    public String getDataFromTable(int k, int l) {
        String finalXpath = "//table/tbody/tr[" + k + "]/td[" + l + "]";
        return getDriver().findElement(By.xpath(finalXpath)).getText();

    }


    public void searchButtonClick(){
        buttonSearch.click();
    }

    public void searchRefresh(){
        searchRefresh.click();
    }

    public PatientsCardPage getPatientsCardClick(){
        BrowserWrapper.waitUntilElementClickable(patientsf);
        patientta.click();
        return new PatientsCardPage();
    }



    public ListPatientPage() {
        this.header = new DoctorHeader();
        pageInitialization();
    }

}
