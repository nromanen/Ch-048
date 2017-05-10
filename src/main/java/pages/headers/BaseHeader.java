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

    private static final int SLEEP_TIMEOUT = 2;

    public BaseHeader(){
        pageInitialization();
    }

    @FindBy(css = "img.localization-flag")
    protected WebElement changeLanguageIco;
    @FindBy(id = "select_doctor_search_button")
    protected WebElement doctorSearchButton;
    @FindBy(id = "select_doctor_search")
    protected WebElement doctorSearchField;
    @FindBy(css = "a[href$='?lang=en']")
    protected WebElement enLanguage;
    @FindBy(css = "a[href$='/HospitalSeeker/']")
    protected WebElement home;
    @FindBy(id = "select_hospital_search_button")
    protected WebElement hospitalSearchButton;
    @FindBy(id = "select_hospital_search-error")
    protected WebElement hospitalSearchError;
    @FindBy(id = "select_hospital_search")
    protected WebElement hospitalSearchField;
    @FindBy(className = "img-responsive logo")
    protected WebElement logo;
    @FindBy(css = "a[href$='/HospitalSeeker/mapsearch']")
    protected WebElement nearestHospital;
    @FindBy(css = "a[href=\"#toggle-search\"]")
    protected WebElement searchButton;
    @FindBy(css = "a[href$='?lang=ua']")
    protected WebElement uaLanguage;
    @FindBy(id = "select_doctor_search-error")
    private WebElement doctorSearchError;
    @FindBy(id = "select_doctor_search_button")
    private WebElement doctorSearchFieldButton;
    @FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[7]/a")
    private WebElement searchLogo;

    public WebElement getChangeLanguageIco() {
        return changeLanguageIco;
    }

    public WebElement getDoctorSearchError() {
        return doctorSearchError;
    }

    public WebElement getHospitalSearchError() {
        return hospitalSearchError;
    }

    public BaseHeader changeLanguageToEn() {
        changeLanguageIco.click();
        enLanguage.click();
        return this;
    }

    public BaseHeader changeLanguageToUa() {
        changeLanguageIco.click();
        uaLanguage.click();
        return new BaseHeader();
    }

      public void fillDoctorInput(String doctorName) {
        fillInput(doctorName, doctorSearchField);
    }

    public void fillHospitalInput(String hospitalName) {
        fillInput(hospitalName, hospitalSearchField);
    }

    public void fillInput(String inputText, WebElement inputField) {
        BrowserWrapper.sleep(SLEEP_TIMEOUT );
        searchButton.click();
        BrowserWrapper.waitUntilElementVisible(inputField);
        inputField.clear();
        inputField.sendKeys(inputText);
    }

    public DoctorSearchResultPage findDoctor(String doctorName) {
        fillDoctorInput(doctorName);
        BrowserWrapper.waitUntilElementClickable(doctorSearchButton);
        doctorSearchButton.click();
        BrowserWrapper.sleep(SLEEP_TIMEOUT );
        return new DoctorSearchResultPage();
    }

    public HospitalSearchResultPage findHospital(String hospitalName) {
        fillHospitalInput(hospitalName);
        BrowserWrapper.waitUntilElementClickable(hospitalSearchButton);
        hospitalSearchButton.click();
        BrowserWrapper.sleep(SLEEP_TIMEOUT );
        return new HospitalSearchResultPage();
    }

    public HospitalSeekerHomePage toHomePage() {
        home.click();
        return new HospitalSeekerHomePage();
    }
}
