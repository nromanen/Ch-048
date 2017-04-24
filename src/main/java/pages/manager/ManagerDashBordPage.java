package pages.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.ManagerHeader;
import utils.BrowserWrapper;
import utils.Driver;

import java.util.ArrayList;
import java.util.List;


public class ManagerDashBordPage extends BasePage {

    public ManagerHeader managerHeader;
    @FindBy(className = "h1.text-center")
    private WebElement hospitalName;

    //maybe no work
    @FindBy(className = "label[for=\"doctorPerPage\"]")
    private WebElement showDoctorsLabel;

    @FindBy(id = "doctorPerPage")
    private WebElement doctorPerPageSelector;

    @FindBy(className = "label[for=\"pref-specializationBy\"]")
    private WebElement specializationLabel;

    @FindBy(id = "pref-specializationBy")
    private WebElement specializationSelector;

    @FindBy(className = "label[for=\"searchBy\"]")
    private WebElement searchByLabel;

    @FindBy(id = "searchBy")
    private WebElement searchBySelector;

    @FindBy(id = "search")
    private WebElement searchInput;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(id = "clearButton")
    private WebElement clearButton;



    @FindBy(css = "a[href=\"/HospitalSeeker/manager/manageDepartments/2\"]")
    private WebElement labaratoryButton;

    @FindBy(css = "a[href=\"/HospitalSeeker/manager/manageDepartments/2\"]")
    private WebElement departmentButton;

    @FindBy(className = "a[href=\"/HospitalSeeker/newDoctor\"]")
    private WebElement newDoctorButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[1]")
    private WebElement hashLabel;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[2]/i")
    private WebElement emailLabel;

    @FindBy(id = "email")
    private WebElement sortByEmailButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[4]/i")
    private WebElement nameLabel;

    @FindBy(id="firstName")
    private WebElement sortByFirstNameButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[4]/i")
    private WebElement lastName;

    @FindBy(id = "lastName")
    private WebElement sortByLastNameButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[5]/i")
    private WebElement specializtionLabel;

    @FindBy(id = "specialization")
    private WebElement sortBySpecializationButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[6]/i")
    private WebElement categoryLabel;

    @FindBy(id = "category")
    private WebElement sortByCategoryButton;

    @FindBy(xpath = "//*[@id=\"allDoctors\"]/thead/tr/th[7]/i")
    private WebElement actionLabel;


    //popUpForms

    @FindBy(xpath = "//*[@id=\"detailForm\"]/h1")
    private WebElement formMainTextLabel;

        @FindBy(css="#detailForm #firstName")
    private WebElement formFirstNameInput;

    @FindBy(id="lastName")
    private WebElement formLastNameInput;

    @FindBy(id="email")
    private WebElement formEmailInput;

    @FindBy(id="image-uploaded")
    private WebElement formImage;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[2]/div[1]/div[1]")
    private WebElement formSpecializationLabel;

    @FindBy(id="specialization")
    private WebElement formSpecializationSelector;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[2]/div[1]/div[2]")
    private WebElement formCategoryLabel;

    @FindBy(id="category")
    private WebElement formCategorySelector;

    @FindBy(id="education")
    private WebElement formEducationInput;

    @FindBy(id="address")
    private WebElement formAddressInput;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[4]/div[1]/div")
    private WebElement formGenderLabel;

    @FindBy(css = "input[value=\"MALE\"]")
    private WebElement formMaleRadioButton;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[4]/div[2]/div/label")
    private WebElement formMaleLabel;

    @FindBy(css = "input[value=\"FEMALE\"]")
    private WebElement formFemaleRadioButton;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[4]/div[3]/div/label")
    private WebElement formFemaileLabel;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[6]/div[1]/div")
    private WebElement formDataOfBirthLabel;

    @FindBy(id="birthDate")
    private WebElement formDateOfBirthInput;

    @FindBy(xpath = "//*[@id=\"detailForm\"]/div[6]/div[3]/div")
    private WebElement formPhoneLabel;

    @FindBy(id="phone")
    private WebElement formPhoneInput;

    @FindBy(id="cancel")
    private WebElement formCancelButton;

    @FindBy(id="editUser")
    private WebElement formSaveButton;


    @FindBy(xpath = "//html/body/section/a[2]")
    private WebElement backToTopButton;

    @FindBy(tagName = "tbody")
    private WebElement tableBody;

    @FindBy(css = "div#deleteDoctorModal h3")
    public WebElement deleteHeader;

    public String information = "INFORMATION ABOUT DOCTOR";
    public String edit = "EDIT DOCTOR";
    public void selectDoctorPerPage(String value) {
        BrowserWrapper.selectDropdown(doctorPerPageSelector, value);

    }

    public void selectSpecialization(String value) {
        BrowserWrapper.selectDropdown(specializationSelector, value);


    }

    public void selectSearchBy(String value) {
        BrowserWrapper.selectDropdown(searchBySelector, value);

    }

    public void searchByText(String value){
        searchInput.clear();
        searchInput.sendKeys(value);
    }

    public void searchButtonClick(){
        BrowserWrapper.waitUntilElementVisible(searchButton);
        searchButton.click();
        BrowserWrapper.sleep(3);

    }

    public void clearButtonClick(){
       BrowserWrapper.clickWithStaleException(clearButton);
       BrowserWrapper.sleep(3);
    }

    public DepartmentsPage labaratoryButtonClick(){
        labaratoryButton.click();
        return new DepartmentsPage();
    }

    public DepartmentsPage departmentsButtonClick(){
        departmentButton.click();
        return new DepartmentsPage();
    }

    public AddNewDoctorPage addNewDoctorButtonClick(){
        newDoctorButton.click();
        return new AddNewDoctorPage();
    }

    public void sortByEmailButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByEmailButton);
        sortByEmailButton.click();
      //  BrowserWrapper.sleep(1);
    }

    public void sortByFirstNameButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByFirstNameButton);
        sortByFirstNameButton.click();
      //  BrowserWrapper.sleep(1);
    }

    public void sortByLastNameButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByLastNameButton);
        sortByLastNameButton.click();
    //    BrowserWrapper.sleep(1);
    }

    public void sortBySpecializationButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortBySpecializationButton);
        sortBySpecializationButton.click();
     //   BrowserWrapper.sleep(1);
    }

    public void sortByCategoryButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByCategoryButton);
        sortByCategoryButton.click();
     //   BrowserWrapper.sleep(1);
    }

    public void sortByEmailDoubleButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByEmailButton);
        BrowserWrapper.tripleClick(sortByEmailButton);
        BrowserWrapper.sleep(1);
    }

    public void sortByFirstNameDoubleButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByFirstNameButton);
        BrowserWrapper.tripleClick(sortByFirstNameButton);
        BrowserWrapper.sleep(1);
    }

    public void sortByLastNameDoubleButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByLastNameButton);
        BrowserWrapper.tripleClick(sortByLastNameButton);
        BrowserWrapper.sleep(1);
    }

    public void sortBySpecializationDoubleButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortBySpecializationButton);
        BrowserWrapper.tripleClick(sortBySpecializationButton);
        BrowserWrapper.sleep(1);
    }

    public void sortByCategoryDoubleButtonClick(){
        BrowserWrapper.waitUntilElementVisible(sortByCategoryButton);
        BrowserWrapper.tripleClick(sortByCategoryButton);
        BrowserWrapper.sleep(1);
    }

    public String getValue(String row, String colName){
        String td = tdFinder(colName);

       return Driver.instance().findElement(By.cssSelector("tbody tr:nth-child(" + row + ") td:nth-child(" + td + ")")).getText();
    }

    public String tdFinder(String colName){
        String td = null;
        switch (colName){
            case "email":
                td = "2";
                break;
            case "first name":
                td = "3";
                break;
            case "last name":
                td = "4";
                break;
            case "specialization":
                td = "5";
                break;
            case "category":
                td = "6";
                break;
            case "actions":
                td = "7";
                break;
        }
        return td;
    }

    public List<String> getCoulumn(String colName){
        String td = tdFinder(colName);
        ArrayList<String> list = new ArrayList<>();

        String text = null;
        List<WebElement> elements = Driver.instance().findElements(By.cssSelector("tbody tr td:nth-child("+ td +")"));

        for( WebElement webElement : elements){
            for(int i = 0; i < 5; i++) {
                try {
                    text = webElement.getText();
                    break;
                } catch (StaleElementReferenceException e) {
                }
            }
            if(text!=null) {
                list.add(text);
            }
        }
        return list;
    }


    public boolean isDeleteConfirmationPresent(){
        return BrowserWrapper.isElementPresent(deleteHeader);
    }


    public String getTestStale(WebElement element){
        String st = null;
        for (int i = 0 ; i<5; i++) {
            try {
                st = element.getText();
                break;
            }catch (StaleElementReferenceException e) {
            }
        }
        return st;
    }

    public boolean checkTitleDetails(){
        return getTestStale(formMainTextLabel).equals(information);
    }

    public boolean checkTitleEdit(){
        return getTestStale(formMainTextLabel).equals(edit);
    }

    public String getDetailedName(){
        String text = null;
        for(int i = 0; i<5; i++) {
            try {
                text = formFirstNameInput.getAttribute("value");
                break;
            }catch (StaleElementReferenceException e) {
            }
        }
        return text;
    }

    public int getNumberOfRows(){
        int number = 0;
        for(int i = 0; i<5; i++){

            try{
                number = tableBody.findElements(By.tagName("tr")).size();
                break;
            }catch (StaleElementReferenceException e){
            }catch (InvalidSelectorException e){
                break;
            }
        }
        return number;
    }

    public void viewButtonClick(String name) {
        List<WebElement> tableRows= Driver.instance().findElements(By.tagName("tr"));
        for (WebElement element : tableRows){
            if(element.getText().contains(name)){
                element.findElement(By.id("viewUser")).click();
            }
        }
    }

    public void editButtonClick(String name) {
        List<WebElement> tableRows= Driver.instance().findElements(By.tagName("tr"));
        for (WebElement element : tableRows){
            if(element.getText().contains(name)){
                element.findElement(By.id("ediUser")).click();
            }
        }
    }


    public SchedulerPage scheduleButtonClick(String name) {
        List<WebElement> tableRows= Driver.instance().findElements(By.tagName("tr"));
        for (WebElement element : tableRows){
            if(element.getText().contains(name)){
                element.findElement(By.id("schedule")).click();
            }
        }
        BrowserWrapper.waitUntilElementVisible(Driver.instance().findElement(By.cssSelector("div.dhx_cal_date")));

         return new SchedulerPage();
    }

    public void deleteButtonClick(String name) {
        List<WebElement> tableRows= Driver.instance().findElements(By.tagName("tr"));
        for (WebElement element : tableRows){
            if(element.getText().contains(name)){
                element.findElement(By.id("deleteDoctor")).click();
            }
        }
    }




    public ManagerDashBordPage() {
        managerHeader = new ManagerHeader();
    }
}
