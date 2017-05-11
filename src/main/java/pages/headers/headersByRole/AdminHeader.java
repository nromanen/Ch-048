package pages.headers.headersByRole;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.admin.AddNewHospitalPage;
import pages.admin.AddUserPage;
import pages.admin.AllUsersPage;
import pages.admin.HospitalListPage;
import utils.BrowserWrapper;


/**
 * Created by Evgen on 06.04.2017.
 */
public class AdminHeader extends AuthorizedHeader implements PageInitializer {
    public AdminHeader() {
        pageInitialization();
    }

    @FindBy(css = "#bs-example-navbar-collapse-1 > ul li:nth-child(2)")
    public WebElement homeButton;

    @FindBy(xpath = "//li[4]/a")
    public WebElement actions;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/users?status=true\"]")
    private WebElement allUsersIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/newUser\"]")
    private WebElement addUserIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/listhospitals\"]")
    private WebElement hospitalListIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/new\"]")
    private WebElement addHospitalIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/map/validate\"]")
    private WebElement googlePoiIco;

    @FindBy(css = "a[href=\"/HospitalSeeker/editHospitalsManagers\"]")
    private WebElement editManagers;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/configureToken\"]")
    private WebElement tokenConfig;

    @FindBy(css = "a[href=\"/HospitalSeeker/admin/departmentName\"]")
    private WebElement addDepartment;


    public AllUsersPage goToAllUsersPage() {
        allUsersIco.click();
        return new AllUsersPage();
    }

    public AddUserPage goToAddUserPage() {
        actions.click();
        BrowserWrapper.waitUntilElementClickable(addUserIco);
        addUserIco.click();
        return new AddUserPage();
    }

    public HospitalListPage goToAllHospitalsPage() {
        actions.click();
        hospitalListIco.click();
        return new HospitalListPage();
    }

    public AddNewHospitalPage goToAddNewHospitalPage() {
        actions.click();
        addHospitalIco.click();
        return new AddNewHospitalPage();
    }


}
