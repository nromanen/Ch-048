package pages.headers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.allUsers.*;
import utils.BrowserWrapper;


/**
 * Created by Evgen on 05.04.2017.
 */
public class BaseHeader implements PageInitializer {

    public BaseHeader(){
        pageInitialization();
    }

    @FindBy(className = "img-responsive logo")
    protected WebElement logo;

    @FindBy(css = "a[href$='/HospitalSeeker/']")
    protected WebElement home;

    @FindBy(css = "a[href$='/HospitalSeeker/mapsearch']")
    protected WebElement nearestHospital;

    @FindBy(css = "img.localization-flag")
    protected WebElement changeLanguageIco;

    @FindBy(css = "a[href$='/HospitalSeeker/?lang=ua']")
    protected WebElement uaLanguage;

    @FindBy(css = "a[href$='/HospitalSeeker/?lang=en']")
    protected WebElement enLanguage;

    @FindBy(css = "a[href=\"#toggle-search\"]")
    protected WebElement searchButton;

    @FindBy(id = "select_hospital_search")
    protected WebElement hospitalSearchField;

    @FindBy(id = "select_hospital_search-error")
    protected WebElement hospitalSearchError;

    @FindBy(id = "select_hospital_search_button")
    protected WebElement hospitalSearchButton;

    @FindBy(id = "select_doctor_search")
    protected WebElement doctorSearchField;

    @FindBy(id = "select_doctor_search-error")
    private WebElement doctorSearchError;

    @FindBy(id = "select_doctor_search_button")
    protected WebElement doctorSearchButton;

    @FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[7]/a")
    private WebElement searchLogo;

    @FindBy(id = "select_doctor_search_button")
    private WebElement doctorSearchFieldButton;

    public WebElement getChangeLanguageIco() {
        return changeLanguageIco;
    }

    public WebElement getDoctorSearchError() {
        return doctorSearchError;
    }

    public WebElement getHospitalSearchError() {
        return hospitalSearchError;
    }

    public HospitalSeekerHomePage toHomePage() {
        home.click();
        return new HospitalSeekerHomePage();
    }



    public BaseHeader changeLanguageToUa() {
        changeLanguageIco.click();
        uaLanguage.click();
        return new BaseHeader();
    }

    public BaseHeader changeLanguageToEn() {
        changeLanguageIco.click();
        enLanguage.click();
        return this;
    }

    public HospitalSearchResultPage findHospital(String hospitalName) {
        fillHospitalInput(hospitalName);
        BrowserWrapper.waitUntilElementClickable(hospitalSearchButton);
        hospitalSearchButton.click();
        return new HospitalSearchResultPage();
    }

    public void fillHospitalInput(String hospitalName) {
        searchButton.click();
        BrowserWrapper.waitUntilElementVisible(hospitalSearchField);
        hospitalSearchField.clear();
        hospitalSearchField.sendKeys(hospitalName);
    }

    public  DoctorSearchResultPage findDoctor(String doctorName) {
        BrowserWrapper.sleep(2);
        fillDoctorInput(doctorName);
        BrowserWrapper.waitUntilElementClickable(doctorSearchButton);
        doctorSearchButton.click();
        return new DoctorSearchResultPage();
    }

    public void fillDoctorInput(String doctorName) {
        searchButton.click();
        BrowserWrapper.waitUntilElementVisible(doctorSearchField);
        doctorSearchField.clear();
        doctorSearchField.sendKeys(doctorName);
        BrowserWrapper.waitForPage(10L);
    }
}
