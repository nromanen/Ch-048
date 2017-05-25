package pages.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.PageInitializer;
import pages.headers.headersByRole.ManagerHeader;


/**
 * This class represent the page wich give us ability to add new doctor
 * It's constructed using POM pattern.
 * @author Yuri Tomko
 * @version 1.0
 */
public class AddNewDoctorPage implements PageInitializer {
    public ManagerHeader managerHeader;

    @FindBy(className = "h1.text-center")
    private WebElement headerTextLabel;

    @FindBy(id = "firstName")
    private WebElement firstNameTextField;

    @FindBy(id = "lastName")
    private WebElement lastNameTextField;

    @FindBy(id = "email")
    private WebElement emailTextField;

    @FindBy(css = "figure a")
    private WebElement imageHolder;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[2]/div[1]/div[1]")
    private WebElement specializationLabel;

    @FindBy(id = "specialization")
    private WebElement specializtionSelector;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[2]/div[1]/div[2]")
    private WebElement categoryLabel;

    @FindBy(id="category")
    private WebElement categorySelector;

    @FindBy(id = "education")
    private WebElement educationTextField;

    @FindBy(id="address")
    private WebElement addressTextField;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[4]/div[1]/div[1]")
    private WebElement hospitalNameLabel;

    @FindBy(id = "hospital")
    private WebElement hospitalSelector;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[4]/div[1]/div[2]")
    private WebElement departmentNameLabel;

    @FindBy(id="departmen")
    private WebElement departmentSelector;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[5]/div[1]/div")
    private WebElement genderLabel;

    @FindBy(css = "input[value=\"MALE\"]")
    private WebElement maleRadioButton;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[5]/div[2]/div/label")
    private WebElement maleRadioButtonLabel;

    @FindBy(css = "input[value=\"FEMALE\"]")
    private WebElement femaleRadioButton;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[5]/div[3]/div/label")
    private WebElement femaleRadioButtonLabel;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[7]/div[1]/div")
    private WebElement dayOfBirthLabel;

    @FindBy(id = "datepicker")
    private WebElement dadePicker;

    @FindBy(xpath = "//*[@id=\"registerNewUser\"]/div[7]/div[3]/div")
    private WebElement phoneLabel;

    @FindBy(id="phone")
    private WebElement phoneInput;

    @FindBy(id="newUserSubmit")
    private WebElement newUserSubmit;

    @FindBy(id="cancel")
    private WebElement cancleButton;

    @FindBy(xpath = "/html/body/section/a")
    private WebElement backToTopButton;

    /**
     * This method is used for filling field with first name of doctor
     *
     * @param firstName It's a first name of a doctor
     */
    public void firstName(String firstName){
        firstNameTextField.clear();
        firstNameTextField.sendKeys(firstName);
    }

    /**
     * This method is used for filling field with last name of doctor
     *
     * @param lastName It's a last name of a doctor
     */
    public void lastName(String lastName){
        lastNameTextField.clear();
        lastNameTextField.sendKeys(lastName);
    }

    /**
     * This method is used for filling field with last name of doctor
     *
     * @param email It's a email of a doctor
     */
    public void email(String email){
        emailTextField.clear();
        emailTextField.sendKeys(email);
    }

    /**
     *  This method is used for selecting specialization of doctor
     *
     * @param value It's a value which will be selected
     */
    public void specializationSelector(String value){
        Select select = new Select(specializtionSelector);
        select.selectByVisibleText(value);
    }

    /**
     * This method is used for selecting category of doctor
     *
     * @param value It's a value which will be selected
     */
    public void categorySelector(String value){
        Select select = new Select(categorySelector);
        select.selectByVisibleText(value);
    }

    /**
     * This method is used for filling field with education of doctor
     *
     * @param education It's a education of a doctor
     */
    public void education(String education){
        emailTextField.clear();
        educationTextField.sendKeys(education);
    }

    /**
     * This method is used for fill field with address of doctor
     *
     * @param address It's a address of a doctor
     */
    public void address(String address){
        addressTextField.clear();
        addressTextField.sendKeys(address);
    }

    /**
     * This method is used for selecting hospital of doctor
     *
     * @param value It's a name of hospital
     */
    public void hospitalSelector(String value){
        Select select = new Select(hospitalSelector);
        select.selectByVisibleText(value);
    }

    public AddNewDoctorPage(){
        managerHeader = new ManagerHeader();
        pageInitialization();
    }
}
