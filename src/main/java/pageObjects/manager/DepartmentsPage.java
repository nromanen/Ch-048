package pageObjects.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.allUsers.PageObject;
import pageObjects.headers.headersByRole.ManagerHeader;

/**
 * Created by ytomktc on 06.04.2017.
 */
public class DepartmentsPage extends PageObject {

    ManagerHeader managerHeader;
    @FindBy(xpath = "/html/body/section/div/div/h1")
    private WebElement departmentsLabel;
    private WebElement departmentsText;

    @FindBy(xpath = "//*[@id=\"allDepartment\"]/thead/tr/th[1]")
    private WebElement nameText;

    @FindBy(id = " name")
    private WebElement sortByNameButton;

    @FindBy(xpath = "//*[@id=\"allDepartment\"]/thead/tr/th[2]")
    private WebElement descriptionText;

    @FindBy(id="description")
    private WebElement sortByDescriptionButton;

    @FindBy(xpath = "//*[@id=\"allDepartment\"]/thead/tr/th[3]/i")
    private WebElement actionText;

    @FindBy(id="image-add")
    private WebElement newDepartmentButton;

    @FindBy(xpath = "/html/body/section/a[2]")
    private WebElement backToTopButton;

    public WebElement viewButton(int i) {
        return driver.findElement(By.cssSelector("tbody tr:nth-child(" + i + ") td:last-child  a:first-child"));
    }

    public WebElement editButton(int i) {
        return driver.findElement(By.cssSelector("tbody tr:nth-child(" + i + ") td:last-child #ediDepartment"));
    }
    public WebElement scheduleButton(int i) {
        return driver.findElement(By.cssSelector("tbody tr:nth-child(" + i + ") td:last-child a:last-child"));
    }



    public DepartmentsPage(WebDriver driver) {
        super(driver);
        managerHeader = new ManagerHeader(driver);
    }
}
