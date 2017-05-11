package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.AdminHeader;
import utils.BrowserWrapper;
import utils.DriverInitializer;
import utils.TableParser;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Evgen on 06.04.2017.
 */
public class AllUsersPage implements PageInitializer {

    public AdminHeader header;

    @FindBy(css = "table")
    public WebElement table;

    @FindBy(id = "userPerPage")
    private WebElement usersPerPagePopUp;

    @FindBy(css = "#searchForm > div:first-child > label")
    public WebElement usersPerPageLabel;

    @FindBy(id = "pref-roleby")
    private WebElement role;

    @FindBy(id = "searchBy")
    private WebElement searchBy;

    @FindBy(id = "search")
    private WebElement searchWindow;

    @FindBy(id = "searchButton")
    public WebElement searchButton;

    @FindBy(id = "clearButton")
    private WebElement clearButton;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/users?status=true\"]")
    private WebElement enableButton;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/users?status=false\"]")
    private WebElement disableButton;

    @FindBy(css = ".pull-right .btn-group a:last-child")
    private WebElement allUsersButton;

    @FindBy(css = "thead")
    private WebElement tableHead;

    @FindBy(css = "tbody")
    public WebElement tableBody;

    @FindBy(css = "pagination pagination-lg")
    private WebElement navigation;

    @FindBy(css = "body section div.content div div ul li:last-child")
    private WebElement lastPageButton;

    @FindBy(css = "body section div.content div div ul li:first-child a")
    private WebElement firstPageButton;

    @FindBy(id = "email")
    public WebElement sortByEmailButton;

    @FindBy(id = "detail.firstName")
    private WebElement sortByFirsNamButton;

    @FindBy(id = "detail.lastName")
    private WebElement sortByLastNameButton;

    @FindBy(xpath = "/html/body/section/div[1]/div[1]/div/h4")
    public WebElement createdLabel;


    @FindBy(id = "roles.type")
    private WebElement sortByRoleButton;

    public WebElement viewWindow;

    @FindBy(className = "table table-user-information")
    private WebElement editWindow;

    @FindBy(className = "table table-user-information form-control")
    private WebElement editRoleSelect;

    @FindBy(css = "a[aria-label='Next']")
    public WebElement nextPageButton;


    private WebElement editButton;

    public WebElement deleteWindow;

    private WebElement deleteButton;



    public AllUsersPage() {
        this.header = new AdminHeader();
        pageInitialization();
    }




    public AllUsersPage getCreatedTableText(){
        createdLabel.getText();
        return new AllUsersPage();
    }


    public AllUsersPage showAllUsers() {
        ((JavascriptExecutor) DriverInitializer.instance()).executeScript("arguments[0].click();" , enableButton);
        return new AllUsersPage();
    }

    public AllUsersPage showEnableUsers() {
        ((JavascriptExecutor) DriverInitializer.instance()).executeScript("arguments[0].click();" , enableButton);
        BrowserWrapper.sleep(2);
        return new AllUsersPage();
    }

    public AllUsersPage showDisableUsers() {
        ((JavascriptExecutor) DriverInitializer.instance()).executeScript("arguments[0].click();" , disableButton);
        BrowserWrapper.sleep(3);
        return new AllUsersPage();
    }


    public AllUsersPage changeCountOfUsersOnPage(int count) {
        selectDropdownCount(usersPerPagePopUp, String.valueOf(count));
        BrowserWrapper.sleep(3);
        return new AllUsersPage();
    }

    public AllUsersPage changeRole(String role) {
        this.role.findElement(By.cssSelector("option[value=" + role + "]")).click();
        searchButton.click();
        BrowserWrapper.sleep(2);
        return new AllUsersPage();
    }


    public void changeSearchBy(String field) {
        searchBy.findElement(By.cssSelector("option[value=" + field + "]")).click();
    }


    public void sendKeysToSearchField(String keys) {
        searchWindow.clear();
        BrowserWrapper.sleep(1);
        searchWindow.sendKeys(keys);
    }

    public AllUsersPage search(int count, String role, String field, String keys) {
        changeCountOfUsersOnPage(count);
        changeRole(role);
        changeSearchBy(field);
        sendKeysToSearchField(keys);
        searchButton.click();
        BrowserWrapper.sleep(2);
        return new AllUsersPage();
    }


    public List<String> getUserDataFromInfoWindow(int rowNumber) {
        List<String> result = new LinkedList<>();
        WebElement infoButton = new TableParser(table).getButtonFromTableRow(rowNumber, "View");
        infoButton.click();
        BrowserWrapper.sleep(3);
        viewWindow = DriverInitializer.instance().findElement(By.className("modal-content"));
        result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(1) td:last-child")).getText());
        result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(2) td:last-child")).getText());
        result.add(viewWindow.findElement(By.cssSelector("tbody tr:nth-child(4) td:last-child")).getText());
        closeViewWindow();
        BrowserWrapper.sleep(2);
        return result;
    }

    public List<String> getFirstUserDataFromInfoWindow() {
        return getUserDataFromInfoWindow(1);
    }

    public int getCountOfUsersInTable() {
        return tableBody.findElements(By.cssSelector("tr")).size();
    }


    public void closeViewWindow(){
        viewWindow.findElement(By.className("close")).click();
    }


    public void closeEditWindow() {
        editWindow.findElement(By.className("close")).click();
    }



    public WebElement openEditWindow(int rowNumber) {
        WebElement editButton = new TableParser(table).getButtonFromTableRow(rowNumber, "Edit");
        editButton.click();
        BrowserWrapper.sleep(2);
        editWindow = DriverInitializer.instance().findElement(By.id("detailForm"));
        return editWindow;
    }


    public AllUsersPage changeRoleInEditWindow(String role) {
        int rowNumber = 1;
        openEditWindow(rowNumber);
        BrowserWrapper.sleep(2);
        selectDropdownRole(editWindow.findElement(By.id("userRoles")), role);
        BrowserWrapper.sleep(2);
        editWindow.findElement(By.cssSelector("input[value=\"Edit\"]")).click();
        BrowserWrapper.sleep(2);
        return new AllUsersPage();
    }

    public static void selectDropdownRole(WebElement element, String text) {
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(element);
        if (dropdown.getAllSelectedOptions().size() != 0) dropdown.deselectAll();
        dropdown.selectByValue(text);
    }


    public static void selectDropdownCount(WebElement element, String text) {
        org.openqa.selenium.support.ui.Select dropdown = new org.openqa.selenium.support.ui.Select(element);
        dropdown.selectByValue(text);
    }

    public AllUsersPage toNextPage() {
            if (!nextPageButton.equals(null)) {
                nextPageButton.click();
                BrowserWrapper.sleep(2);
                return new AllUsersPage();
            }
            return null;
    }

    public AllUsersPage deleteUser(int rowNumber) {
        deleteButton = new TableParser(table).getButtonFromTableRow(rowNumber, "Delete");
        deleteButton.click();
        BrowserWrapper.sleep(3);
        deleteWindow = DriverInitializer.instance().findElement(By.className("modal-content"));
        ((JavascriptExecutor) DriverInitializer.instance()).executeScript("arguments[0].click();" , DriverInitializer.instance().findElement(By.id("deleteButton")));
        BrowserWrapper.sleep(2);
        return new AllUsersPage();
    }

    public AllUsersPage clickSortByEmail() {
        sortByEmailButton.click();
        BrowserWrapper.sleep(2);
        return new AllUsersPage();
    }

}
