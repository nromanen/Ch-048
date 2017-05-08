package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.AdminHeader;
import utils.BrowserWrapper;
import utils.DriverInitializer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalListPage implements PageInitializer {

    public AdminHeader header;

    public HospitalListPage() {
        this.header = new AdminHeader();
        pageInitialization();
    }

    @FindBy(css = "a.btn:nth-child(1)")
    private WebElement addNewHospitalButton;

    @FindBy(css = "a.btn:nth-child(2)")
    private WebElement checkGooglePoiButton;

    @FindBy(css = "table")
    public WebElement table;

    @FindBy(css = "thead")
    private WebElement tableHead;

    @FindBy(css = "tbody")
    private WebElement tableBody;

    @FindBy(xpath = "//button[contains(text(),'Delete')]")
    private WebElement deleteButtonInDeleteWindow;

    private WebElement deleteModalSubmit;
    private WebElement showOnMap;
    private WebElement editButton;
    private WebElement deleteButton;

    public boolean checkAddNewHospitalButton() {
        return BrowserWrapper.isElementPresent(addNewHospitalButton);
    }

    public AddNewHospitalPage submitAddNewHospital() {
        addNewHospitalButton.click();
        return new AddNewHospitalPage();
    }

    public CheckGooglePOIPage submitCheckGooglePoi() {
        checkGooglePoiButton.click();
        return new CheckGooglePOIPage();
    }

    public HospitalListPage showOnMapButton(int rowNumber) {
        showOnMap = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(3) form button:nth-child(2)"));
        showOnMap.click();
        return new HospitalListPage();
    }

    public AddNewHospitalPage editButton(int rowNumber) {
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            WebElement tableRow = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")"));
            editButton = tableRow.findElement(By.cssSelector("body > section > div > div > div > div.col-sm-8 > div.pre-scrollable.panel.panel-default > table > tbody > tr:nth-child(" + rowNumber + ") > td:nth-child(3) > form > a"));
            editButton.click();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath("//*[@id=\"image-uploaded\"]"));
            return new AddNewHospitalPage();
        }
        return null;
    }

    public HospitalListPage deleteHospital(int rowNumber) {
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            WebElement tableRow = tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")"));
            deleteButton = tableRow.findElement(By.cssSelector("body > section > div > div > div > div.col-sm-8 > div.pre-scrollable.panel.panel-default > table > tbody > tr:nth-child(" + rowNumber + ") > td:nth-child(3) > form > button:nth-child(4)"));
            deleteButton.click();
            BrowserWrapper.sleep(1);
            deleteModalSubmit = tableRow.findElement(By.cssSelector("body > section > div > div > div > div.col-sm-8 > div.pre-scrollable.panel.panel-default > table > tbody > tr:nth-child(" + rowNumber + ") > td:nth-child(3) > form div.modal-content div.modal-footer > button:nth-child(1)"));
            deleteModalSubmit.click();
            BrowserWrapper.sleep(5);
            BrowserWrapper.waitUntilElementClickableByLocator(By.cssSelector("a.btn:nth-child(1)"));
            return new HospitalListPage();
        }
        return null;
    }

    public List<String> getHospitalDataFromTableRow(int rowNumber) {
        List<String> result = new ArrayList<>();
        if (tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ")")).isDisplayed()) {
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(1)")).getText());
            result.add(tableBody.findElement(By.cssSelector("tr:nth-child(" + rowNumber + ") td:nth-child(2)")).getText());
        }
        return result;
    }

    public int getCountOfHospitalsInTable() {
        return tableBody.findElements(By.cssSelector("tr")).size();
    }

    public String getTitleOfPage() {
        return DriverInitializer.instance().getTitle();
    }
}
