package stepDefinition;


import cucumber.api.java.en.*;
import org.testng.Assert;
import pages.manager.HospitalsPage;
import pages.manager.ManagerDashBordPage;
import utils.BaseNavigation;
import utils.BrowserWrapper;

import java.util.Collections;
import java.util.List;

/**
 * Created by radgast on 23.04.17.
 */
public class DoctorSortingSteps {
    public static final String HOSPITAL_NAME = "Miska Poliklinika";
    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";
    public static final String EMAIL = "Email";
    public static final String FIRST_NAME = "First Name";
    public static final String LAST_NAME = "Last Name";
    public static final String SPECIALIZATION = "Specialization";
    public static final String CATEGORY = "Category";
    public static ManagerDashBordPage managerDashBordPage;


    @Given("^the manager is on dashboard of particular hospital in order to sort$")
    public void the_manager_is_on_dashboard_of_particular_hospital_in_order_to_sort() throws Throwable {
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);
        managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
    }


    @When("^Manager click on the email sorting button one time$")
    public void manager_click_on_the_email_sorting_button_one_time() throws Throwable {
        managerDashBordPage.sortByEmailButtonClick();
    }

    @When("^Manager click on the email sorting button two times$")
    public void manager_click_on_the_email_sorting_button_two_times() throws Throwable{
        managerDashBordPage.sortByEmailDoubleButtonClick();
    }

    @Then("^Doctors in table is sorted by their emails ascending$")
    public void doctors_in_table_is_sorted_by_their_emails_ascending() throws Throwable {
       List<String> emails = managerDashBordPage.getColumn(EMAIL);
       Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by emails ascending");
    }

    @Then("^Doctors in table is sorted by their email descending$")
    public void doctors_in_table_is_sorted_by_their_email_descending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(EMAIL);
        Collections.reverse(emails);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by emails descending");
    }

    @When("^Manager click on the first name sorting button one time$")
    public void manager_click_on_the_first_name_sorting_button_one_time() throws Throwable {
        managerDashBordPage.sortByFirstNameButtonClick();
    }

    @When("^Manager click on the first name sorting button two times$")
    public void manager_click_on_the_first_name_sorting_button_two_times() throws Throwable {
        managerDashBordPage.sortByFirstNameDoubleButtonClick();
    }

    @Then("^Doctors in table is sorted by their first name ascending$")
    public void doctors_in_table_is_sorted_by_their_first_name_ascending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(FIRST_NAME);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by first name ascending");
    }

    @Then("^Doctors in table is sorted by their first name descending$")
    public void doctors_in_table_is_sorted_by_their_first_name_descending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(FIRST_NAME);
        Collections.reverse(emails);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by first name descending");
    }

    @When("^Manager click on the last name sorting button one time$")
    public void manager_click_on_the_last_name_sorting_button_one_time() throws Throwable {
        managerDashBordPage.sortByLastNameButtonClick();
    }

    @When("^Manager click on the last name sorting button two times$")
    public void manager_click_on_the_last_name_sorting_button_two_times() throws Throwable {
        managerDashBordPage.sortByLastNameDoubleButtonClick();
    }

    @Then("^Doctors in table is sorted by their last name ascending$")
    public void doctors_in_table_is_sorted_by_their_last_name_ascending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(LAST_NAME);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by last name ascending");
    }

    @Then("^Doctors in table is sorted by their last name descending$")
    public void doctors_in_table_is_sorted_by_their_last_name_descending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(LAST_NAME);
        Collections.reverse(emails);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by last name descending");
    }


    @When("^Manager click on the specialization sorting button one time$")
    public void manager_click_on_the_specialization_sorting_button_one_time() throws Throwable {
        managerDashBordPage.sortBySpecializationButtonClick();
    }

    @When("^Manager click on the specialization sorting button two times$")
    public void manager_click_on_the_specialization_sorting_button_two_times() throws Throwable {
        managerDashBordPage.sortBySpecializationDoubleButtonClick();
    }

    @Then("^Doctors in table is sorted by their specialization ascending$")
    public void doctors_in_table_is_sorted_by_their_specialization_ascending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(SPECIALIZATION);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by specialization ascending");
    }

    @Then("^Doctors in table is sorted by their specialization descending$")
    public void doctors_in_table_is_sorted_by_their_specialization_descending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(SPECIALIZATION);
        Collections.reverse(emails);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by specialization descending");
    }

    @When("^Manager click on the category sorting button one time$")
    public void manager_click_on_the_category_sorting_button_one_time() throws Throwable {
        managerDashBordPage.sortByCategoryButtonClick();
    }

    @When("^Manager click on the category sorting button two times$")
    public void manager_click_on_the_category_sorting_button_two_times() throws Throwable {
        managerDashBordPage.sortByCategoryDoubleButtonClick();
    }

    @Then("^Doctors in table is sorted by their category ascending$")
    public void doctors_in_table_is_sorted_by_their_category_ascending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(CATEGORY);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by category ascending");
    }

    @Then("^Doctors in table is sorted by their category descending$")
    public void doctors_in_table_is_sorted_by_their_category_descending() throws Throwable {
        List<String> emails = managerDashBordPage.getColumn(CATEGORY);
        Collections.reverse(emails);
        Assert.assertTrue(BrowserWrapper.isSorted(emails), "List not sorted by category descending");
    }
}
