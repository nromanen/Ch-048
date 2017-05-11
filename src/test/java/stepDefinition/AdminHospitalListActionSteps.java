package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.admin.AddNewHospitalPage;
import pages.admin.AllUsersPage;
import pages.admin.CheckGooglePOIPage;
import pages.admin.HospitalListPage;
import utils.BaseNavigation;
import utils.BrowserWrapper;

/**
 * Created by Evgen on 26.04.2017.
 */
public class AdminHospitalListActionSteps {
    private static final String ADMIN_LOGIN = "admin@hospitals.ua";
    private static final String ADMIN_PASSWORD = "1111";

    private static final String HOSPITAL_LIST_TITLE = "hospital list";
    private static final String GOOGLEPOI_TITLE = "google poi";

    private HospitalListPage hospitalListPage;
    private AllUsersPage allUsersPage;
    private AddNewHospitalPage addNewHospitalPage;
    private CheckGooglePOIPage googlePOIPage;

    @Given("^ADMIN is on hospital list page$")
    public void to_hospital_list_page() {
        allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.sleep(2);
        hospitalListPage = allUsersPage.header.goToAllHospitalsPage();
        BrowserWrapper.sleep(2);
    }


    @When("^ADMIN press button \"Add new hospital\"$")
    public void admin_try_open_add_hospital_page() {
        addNewHospitalPage = hospitalListPage.submitAddNewHospital();
        BrowserWrapper.sleep(2);
    }

    @Then("^ADMIN must see page \"Hospital add/edit page\"$")
    public void admin_is_on_add_hospital_page() {
        Assert.assertEquals(addNewHospitalPage.pageLabel.getText().toLowerCase(), "hospital add/edit page" );
    }

    @When("^ADMIN press button \"Edit\" in row '(.*)' with certain hospital$")
    public void admin_try_edit_hospital(String number) {
        addNewHospitalPage = hospitalListPage.editButtonClick(number);
        BrowserWrapper.sleep(2);
    }

    @Then("^ADMIN must see page with \"Hospital add/edit page\"$")
    public void admin_on_add_hospital_page() {
        Assert.assertEquals(addNewHospitalPage.pageLabel.getText().toLowerCase(), "hospital add/edit page" );
    }


    @When("^ADMIN press button \"Check Google POI\"$")
    public void admin_try_open_googlepoi_page() {
        googlePOIPage = hospitalListPage.submitCheckGooglePoi();
        BrowserWrapper.sleep(2);
    }

    @Then("^ADMIN must see page where he can check GooglePOI$")
    public void admin_is_on_googlepoi_page() {
        Assert.assertEquals(googlePOIPage.getTitleOfPage().toLowerCase(), GOOGLEPOI_TITLE);
    }

    @When("ADMIN press button \"Delete\" in row '(.*)' with certain hospital")
    public void admin_try_delete_user(String number) {
        hospitalListPage.deleteButtonClick(number);
    }

    @Then("ADMIN must see this page again without this hospital")
    public void admin_delete_hospital() {
        Assert.assertEquals(hospitalListPage.getTitleOfPage().toLowerCase(), HOSPITAL_LIST_TITLE);
    }
}
