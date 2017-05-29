package pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.AdminHeader;
import utils.BrowserWrapper;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created by Jeksonis on 06.04.2017.
 */
public class AddNewHospitalPage implements PageInitializer {

    private static final String HOSPITAL_IMAGE_PATH = "src/main/resources/RhodeIslandHosp14_360_360_90.jpg";

    public AdminHeader header;

    public AddNewHospitalPage(){
        this.header = new AdminHeader();
        pageInitialization();
    }

    @FindBy(id = "image-uploaded")
    private WebElement imageButton;

    @FindBy(id = "googleMap")
    private WebElement googleMap;

    @FindBy(id = "addressGeo")
    private WebElement addressInputField;

    @FindBy(id = "address.country")
    private WebElement countryInputField;

    @FindBy(id = "address.city")
    private WebElement cityInputField;

    @FindBy(id = "address.street")
    private WebElement streetInputField;

    @FindBy(id = "address.building")
    private WebElement buildingInputField;

    @FindBy(id = "name")
    private WebElement nameInputField;

    @FindBy(id = "description")
    private WebElement descriptionInputField;

    @FindBy(id = "button-find")
    private WebElement findButton;

    @FindBy(id = "button-fill")
    private WebElement fillButton;

    @FindBy(id = "button-save")
    private WebElement saveButton;

    @FindBy(id = "button-reset")
    private WebElement resetButton;

    @FindBy(css = "body > section > div > h3")
    public WebElement pageLabel;

    @FindBy(id = "modalOK")
    private WebElement buttonOkInUploadPhotoModalWindow;


    public void setClipboardData() {
        File file = new File(HOSPITAL_IMAGE_PATH);
        String absolutePath = file.getAbsolutePath();
        StringSelection stringSelection = new StringSelection(absolutePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
    }


    public void addNewHospitalPhoto() throws AWTException {
        setClipboardData();
        Robot robot = new Robot();
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(500);
    }

    public void pushAddPhotoButton() {
        imageButton.click();
    }

    public void addressData(String text) {
        BrowserWrapper.waitUntilElementVisible(addressInputField);
        addressInputField.clear();
        addressInputField.sendKeys(text);
    }

    public AddNewHospitalPage changeCountry(String text) {
        BrowserWrapper.waitUntilElementClickable(countryInputField);
        countryInputField.clear();
        countryInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public AddNewHospitalPage changeCity(String text) {
        BrowserWrapper.waitUntilElementClickable(cityInputField);
        cityInputField.clear();
        cityInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public  AddNewHospitalPage  changeStreet(String text) {
        BrowserWrapper.waitUntilElementClickable(streetInputField);
        streetInputField.clear();
        streetInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public  AddNewHospitalPage changeBuilding(String text) {
        BrowserWrapper.waitUntilElementClickable(buildingInputField);
        buildingInputField.clear();
        buildingInputField.sendKeys(text);
        return new AddNewHospitalPage();
    }

    public void addHospitalName(String text) {
        BrowserWrapper.waitUntilElementClickable(fillButton);
        nameInputField.clear();
        nameInputField.sendKeys(text);
    }

    public void addHospitalDescription(String text) {
        BrowserWrapper.waitUntilElementClickable(fillButton);
        descriptionInputField.clear();
        descriptionInputField.sendKeys(text);
    }

   public void pushFindButton() {
        BrowserWrapper.waitUntilElementClickable(findButton);
        findButton.click();
   }

   public void pushFillButton() {
        BrowserWrapper.waitUntilElementClickable(fillButton);
        fillButton.click();
   }


   public void pushResetButton() {
        resetButton.click();
   }

   public HospitalListPage pushSaveButton() {
        saveButton.click();
        return new HospitalListPage();
   }

   public void addNewHospital(String address, String name, String description) {
       try {
           pushAddPhotoButton();
           addNewHospitalPhoto();
           BrowserWrapper.waitUntilElementVisible(buttonOkInUploadPhotoModalWindow);
           buttonOkInUploadPhotoModalWindow.click();
           addressData(address);
           pushFillButton();
           addHospitalName(name);
           addHospitalDescription(description);
           pushFindButton();
           BrowserWrapper.sleep(2);
           pushSaveButton();
       } catch (AWTException ex) {
           ex.printStackTrace();
       }
   }
}
