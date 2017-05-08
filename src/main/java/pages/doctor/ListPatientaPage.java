package pages.doctor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;
import utils.BrowserWrapper;
import utils.DriverInitializer;

public class ListPatientaPage implements PageInitializer {
    public DoctorHeader header;
    WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(), 5);
    @FindBy(className = "label[for=\"usr\"]")
    private WebElement showPatientsLabel;

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

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[2]/div[1]")
    private WebElement colomEmailPatients;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[3]/div[1]")
    private WebElement colomFirstName;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[4]/div[1]")
    private WebElement colomLastName;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[2]/div[2]/a/i")
    private WebElement sortEmailPatients;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[3]/div[2]/a/i")
    private WebElement sortByFirstName;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/thead/tr/th[4]/div[2]/a/i")
    private WebElement sortByLastName;

    //First row
    @FindBy(xpath = "/html/body/section/div/div/div[2]/table/tbody/tr[1]/td[2]/a")
    private WebElement firstRowPatient;

    @FindBy (xpath = "/html/body/section/div/div/div[2]/table/tbody/tr[1]/td[3]")
    private WebElement firstRowFirstName;

    @FindBy (xpath = "/html/body/section/div/div/div[2]/table/tbody/tr[1]/td[4]")
    private WebElement firstRowLastName;

    @FindBy(partialLinkText = "patient.ta@hospitals.ua")
    private WebElement patientta;
    @FindBy(partialLinkText = "patient.sf@hospitals.ua")
    private WebElement patientsf;
    @FindBy(partialLinkText = "patient.qm@hospitals.ua")
    private WebElement patientqm;
    @FindBy(partialLinkText = "patient.ow@hospitals.ua")
    private WebElement patientow;
    @FindBy(partialLinkText = "patient.in@hospitals.ua")
    private WebElement patientin;
    @FindBy(partialLinkText = "patient.cd@hospitals.ua")
    private WebElement patientcd;
    @FindBy(partialLinkText = "a@gmail.com")
    private WebElement patienta;

    public void searchPatients(String value){
        searchTextField.clear();
        searchTextField.sendKeys(value);
        searchButtonClick();
    }
    public boolean checkResultSearch(){
        return BrowserWrapper.isElementPresent(patientsf);
    }

    public void sortByEmailButton(){
        BrowserWrapper.waitUntilElementClickable(sortEmailPatients);
        sortEmailPatients.click();
    }

    public WebElement getEmailPatients(){
        return patientsf;
    }

    public void sortByFirstNameButton(){
        BrowserWrapper.waitUntilElementClickable(sortByFirstName);
        sortByFirstName.click();
    }
    public void sortByLastNameButton(){
        BrowserWrapper.waitUntilElementClickable(sortByLastName);
        sortByLastName.click();

    }


    public String getDataFromTable(int k, int l) {
        int rowCount = DriverInitializer.instance().findElements(By.xpath("//table/tbody/tr")).size();
        int colCount = DriverInitializer.instance().findElements(By.xpath("//table/tbody/tr[1]/td")).size();

        String firstPart = "//table/tbody/tr[";
        String secondPart = "]/td[";
        String thirdPart = "]";

        String finalXpath = firstPart + k + secondPart + l + thirdPart;
        String tableData = DriverInitializer.instance().findElement(By.xpath(finalXpath)).getText();
        return tableData;
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



    public ListPatientaPage() {
        this.header = new DoctorHeader();
        pageInitialization();
    }

}
