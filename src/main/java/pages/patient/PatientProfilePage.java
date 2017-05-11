package pages.patient;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.PageInitializer;
import pages.headers.headersByRole.PatientHeader;


public class PatientProfilePage implements PageInitializer {

    public PatientHeader patientHeader;


    @FindBy(xpath = "//*[@id=\"image-div\"]/figure/a")
    private WebElement profileImage;

    @FindBy(className = "panel-heading")
    private WebElement panelHeadingDiv;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[1]/div")
    private WebElement emergancyInformationCardText;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[1]/h3/span/button")
    private WebElement closeButton;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[1]/td[1]")
    private WebElement firstNameLabel;

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[2]/td[1]")
    private WebElement lastNameLabel;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[3]/td[1]")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[4]/td[1]")

    private WebElement dateOfBirthLabel;
    @FindBy(id = "birthDate")

    private WebElement dateOfBirthInput;
    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[4]/td[1]")

    private WebElement phoneNumberLabel;
    @FindBy(id = "phone")
    private WebElement phoneNumberInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[2]/table/tbody/tr[6]/td[1]")

    private WebElement genderLabel;
    @FindBy(id = "gender")

    private WebElement genderSelector;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[1]")
    private WebElement homeAdressLabel;

    @FindBy(id = "address")
    private WebElement homeAdressInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[2]/td[1]")
    private WebElement heightLabel;

    @FindBy(id = "height")
    private WebElement heightInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[3]/td[1]")
    private WebElement weightLabel;

    @FindBy(id = "weight")
    private WebElement weightInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[4]/td[1]")
    private WebElement bloodTypeLabel;

    @FindBy(id = "bloodType")
    private WebElement bloodTypeInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[5]/td[1]")
    private WebElement eyeColorLabel;

    @FindBy(id = "eyeColor")
    private WebElement eyeColorInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[3]/table/tbody/tr[6]/td[1]")
    private WebElement hairColorLabel;

    @FindBy(id = "hairColor")
    private WebElement hairColorInput;

    @FindBy(css = "#patientProfileForm > div > div.panel-body > div > div:nth-child(4)")
    private WebElement medicalInformation;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[1]/td[1]")
    private WebElement allergiesLabel;

    @FindBy(id = "allergies")
    private WebElement allergiesInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[2]/td[1]")
    private WebElement currentMedicationLabel;

    @FindBy(id = "currentMedication")
    private WebElement currentMedicationInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[3]/td[1]")
    private WebElement hearthProblemsLabel;

    @FindBy(id = "heartProblems")
    private WebElement heartProblemsSelect;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[4]/td[1]")
    private WebElement diabetesLabel;

    @FindBy(id = "diabetes")
    private WebElement diabetesSelector;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[5]/td[1]")
    private WebElement epilepsyLabel;

    @FindBy(id = "epilepsy")
    private WebElement epilepsySelector;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[5]/table/tbody/tr[6]/td[1]")
    private WebElement restrictionsLabel;

    @FindBy(id = "restrictions")
    private WebElement restrictionsInput;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[6]")
    private WebElement immediatelyCallCaseLabel;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[2]/td[1]/p")
    private WebElement relativeNameOneLabel;

    @FindBy(id = "relativeName")
    private WebElement relativeNameInputOne;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[2]/td[2]/p")
    private WebElement relativePhoneLabelOne;

    @FindBy(id = "relativePhone")
    private WebElement relativePhoneInputOne;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[2]/td[3]/p")
    private WebElement relationLabel;

    @FindBy(id = "relativeRelation")
    private WebElement relativeRelativeInputOne;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[3]/td[1]/p")
    private WebElement relativeNameTwoLabel;

    @FindBy(xpath = "//*[@id=\"relativeName\"]")
    private WebElement relativeNameInputTwo;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[3]/td[2]/p")
    private WebElement relativePhoneLabelTwo;

    @FindBy(xpath = "//*[@id=\"relativePhone\"]")
    private WebElement relativePhoneInputTwo;

    @FindBy(xpath = "//*[@id=\"patientProfileForm\"]/div/div[2]/div/div[7]/table/tbody/tr[3]/td[3]/p")
    private WebElement relativeRelativeLabelTwo;

    @FindBy(xpath = "//*[@id=\"relativeRelation\"]")
    private WebElement relativeRelativeInputTwo;

    @FindBy(id = "submitChanges")
    private WebElement submitChangesButton;

    public PatientProfilePage() {
        this.patientHeader = new PatientHeader();
        pageInitialization();
    }


    public void closeButton() {
        closeButton.click();
    }

    public void profileImage() {
        profileImage.click();
    }

    public void enterFirstName(String value) {
        firstNameInput.clear();
        firstNameInput.sendKeys(value);
    }

    public void enterLastName(String value) {
        lastNameInput.clear();
        lastNameInput.sendKeys(value);
    }

    public void enterEmail(String value) {
        emailInput.clear();
        emailInput.sendKeys(value);
    }

    public void enterBirthDate(String value) {
        dateOfBirthInput.clear();
        dateOfBirthInput.sendKeys(value);
    }

    public void enterPhoneNumber(String value) {
        phoneNumberInput.clear();
        phoneNumberInput.sendKeys(value);
    }

    public void selectGender(String value) {
        Select dropdown = new Select(genderSelector);
        dropdown.selectByValue(value);
    }

    public void enterHomeAdress(String value) {
        homeAdressInput.clear();
        homeAdressInput.sendKeys(value);
    }

    public void enterHeight(String value) {
        heightInput.clear();
        heightInput.sendKeys(value);
    }

    public void enterWeight(String value) {
        weightInput.clear();
        weightInput.sendKeys(value);
    }

    public void enterBloodType(String value) {
        bloodTypeInput.clear();
        bloodTypeInput.sendKeys(value);
    }

    public void enterEyeColor(String value) {
        eyeColorInput.clear();
        eyeColorInput.sendKeys(value);
    }

    public void enterHairColor(String value) {
        hairColorInput.clear();
        hairColorInput.sendKeys(value);
    }

    public void enterAllergies(String value) {
        allergiesInput.clear();
        allergiesInput.sendKeys(value);
    }

    public void enterCurrentMedication(String value) {
        currentMedicationInput.clear();
        currentMedicationInput.sendKeys(value);
    }

    public void selectHearhProblemsPresent(String value) {
        Select dropdown = new Select(heartProblemsSelect);
        dropdown.selectByValue(value);
    }

    public void selectDiabetesPresent(String value) {
        Select dropdown = new Select(diabetesSelector);
        dropdown.selectByValue(value);
    }

    public void selectEpilepcyPresent(String value) {
        Select dropdown = new Select(heartProblemsSelect);
        dropdown.selectByValue(value);
    }

    public void enterResrictions(String value) {
        restrictionsInput.clear();
        restrictionsInput.sendKeys(value);
    }

    public void enterRelativeNameOne(String value) {
        relativeNameInputOne.clear();
        relativeNameInputOne.sendKeys(value);
    }

    public void enterRelativePhoneOne(String value) {
        relativePhoneInputOne.clear();
        relativePhoneInputOne.sendKeys(value);
    }

    public void enterRelationOne(String value) {
        relativeRelativeInputOne.clear();
        relativeRelativeInputOne.sendKeys(value);
    }

    public void enterRelativeNameTwo(String value) {
        relativeNameInputTwo.clear();
        relativeNameInputTwo.sendKeys(value);
    }

    public void enterRelativePhoneTwo(String value) {
        relativePhoneInputTwo.clear();
        relativePhoneInputTwo.sendKeys(value);
    }

    public void enterRelationTwo(String value) {
        relativeRelativeInputTwo.clear();
        relativePhoneInputTwo.sendKeys(value);
    }


}
