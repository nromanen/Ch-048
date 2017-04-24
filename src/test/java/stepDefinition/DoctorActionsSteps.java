package stepDefinition;

import cucumber.api.java.en.*;
import org.testng.Assert;
import pages.manager.HospitalsPage;
import pages.manager.ManagerDashBordPage;
import utils.BaseNavigation;
import utils.BrowserWrapper;

/**
 * Created by radgast on 24.04.17.
 */
public class DoctorActionsSteps {
    public static final String HOSPITAL_NAME = "Miska Poliklinika";
    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";
    public static final String DOCTOR_NAME = "Chester";
    ManagerDashBordPage managerDashBordPage;

    @Given("^the manager is on dashboard of particular hospital in order to interact with doctor$")
    public void the_manager_is_on_dashboard_of_particular_hospital_in_order_to_interact_with_doctor() throws Throwable {
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);
        managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
    }

    @When("^Manager click on the details button of particular doctor$")
    public void manager_click_on_the_details_button_of_particular_doctor() throws Throwable {
        managerDashBordPage.viewButtonClick(DOCTOR_NAME);
    }

    @Then("^Pop up form with detaild information about particular doctor$")
    public void pop_up_form_with_detaild_information_about_particular_doctor() throws Throwable {
        Assert.assertTrue(managerDashBordPage.checkTitleDetails(), "Detailed form not appear");
    }

    @When("^Manager click on the edit button of particular doctor$")
    public void manager_click_on_the_edit_button_of_particular_doctor() throws Throwable {
        managerDashBordPage.editButtonClick(DOCTOR_NAME);
    }

    @Then("^Pop up form with information to edit about particular doctor$")
    public void pop_up_form_with_information_to_edit_about_particular_doctor() throws Throwable {
        Assert.assertTrue(managerDashBordPage.checkTitleEdit(), "Edit form not appear");
    }

    @When("^Manager click on the scheduler button of particular doctor$")
    public void manager_click_on_the_scheduler_button_of_particular_doctor() throws Throwable {
        managerDashBordPage.scheduleButtonClick(DOCTOR_NAME);
    }

    @Then("^Moving to the scheduler page of particular doctor$")
    public void moving_to_the_scheduler_page_of_particular_doctor() throws Throwable {
        Assert.assertEquals(BrowserWrapper.getTitle(),"Manage", "Redirection to scheduler page failed");
    }

    @When("^Manager click on delete button of particular doctor$")
    public void manager_click_on_delete_button_of_particular_doctor() throws Throwable {
        managerDashBordPage.deleteButtonClick(DOCTOR_NAME);
    }

    @Then("^Pop up form with confirmation for deleting$")
    public void pop_up_form_with_confirmation_for_deleting() throws Throwable {
        managerDashBordPage.isDeleteConfirmationPresent();
    }

    @Then("^Name of doctor in details form should match with name from table$")
    public void name_of_doctor_in_details_form_should_match_with_name_from_table() throws Throwable {
        Assert.assertEquals(managerDashBordPage.getDetailedName(), DOCTOR_NAME, "Name in detailed form not match with table");
    }
}
