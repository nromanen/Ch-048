package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.manager.HospitalsPage;
import pages.manager.ManagerDashBordPage;
import utils.BaseNavigation;
import utils.BrowserWrapper;

import java.util.Collections;
import java.util.List;

/**
 * Created by radga on 20.05.2017.
 */
public class ManagerSteps {

    public static final String EDITED_DOCTOR_FIRST_NAME = "Johny";
    public static final String EDITED_DOCTOR_LAST_NAME = "Sins";
    public static final String EDITED_EDUCATION ="Cambridge";
    public static final String EDITED_ADDRESS = "New York";
    public static final String NUMBER_DOCTORS_PER_PAGE = "20";
    public static final int EXPECTED_NUMBER_OF_DOCTORS_PER_PAGE= 20;
    public static final String SPECIALIZATION_COLUMN = "Specialization";
    public static final String DOCTOR_EMAIL = "doctor.cb@hospitals.ua";
    public static final String DOCTOR_NAME = "Chester";
    public static final String HOSPITAL_NAME = "Miska Poliklinika";
    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";
    public static final String EMAIL = "Email";
    public static final String FIRST_NAME = "First Name";
    public static final String LAST_NAME = "Last Name";

    public static final String SPECIALIZATION = "Specialization";
    public static final String SPECIALIZATION_VALUE = "Dentist";
    public static final String CATEGORY = "Category";
    public static final String MANAGE_TITLE = "Manage";
    private ManagerDashBordPage managerDashBordPage;
    private int numberOfRows;

    @Given("^the manager is on dashboard of particular hospital$")
    public void the_manager_is_on_dashboard_of_particular_hospital() throws Throwable {
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
        Assert.assertEquals(BrowserWrapper.getTitle(),MANAGE_TITLE, "Redirection to scheduler page failed");
    }

    @When("^Manager click on delete button of particular doctor$")
    public void manager_click_on_delete_button_of_particular_doctor() throws Throwable {
        managerDashBordPage.deleteButtonClick(DOCTOR_NAME);
    }

    @Then("^Pop up form with confirmation for deleting$")
    public void pop_up_form_with_confirmation_for_deleting() throws Throwable {
        Assert.assertTrue(managerDashBordPage.isDeleteConfirmationPresent(),"Can't delete doctor");
    }

    @Then("^Name of doctor in details form should match with name from table$")
    public void name_of_doctor_in_details_form_should_match_with_name_from_table() throws Throwable {
        Assert.assertEquals(managerDashBordPage.getDetailedName(), DOCTOR_NAME, "Name in detailed form not match with table");
    }

    @And("^Manager type different first name, second name, education and address$")
    public void manager_type_different_first_name_second_name_education_and_address() throws Throwable{
        managerDashBordPage.enterFullNameDetailedForm(EDITED_DOCTOR_FIRST_NAME,EDITED_DOCTOR_LAST_NAME);
        managerDashBordPage.enterEducation(EDITED_EDUCATION);
        managerDashBordPage.enterAddress(EDITED_ADDRESS);
        managerDashBordPage.submitEdition();
    }

    @Then("^First name in table changed to proper name$")
    public void first_name_in_table_changed_to_proper_name() throws Throwable{
        List<String> list = managerDashBordPage.getColumn(FIRST_NAME);
        Assert.assertTrue(list.stream().anyMatch(e -> e.equals(EDITED_DOCTOR_FIRST_NAME)));
    }

    @And("^Confirm deletion$")
    public void confirm_deletion(){
        managerDashBordPage.deleteSubmit();
    }

    @Then("^Doctor shouldn't appear in the table$")
    public void doctor_should_not_appear_in_the_table() throws Throwable{
        List<String> list = managerDashBordPage.getColumn(FIRST_NAME);
        Assert.assertTrue(list.stream().noneMatch(e -> e.equals(DOCTOR_NAME)));
    }
    @When("^Manager Select number doctors per page from selector$")
    public void manager_Select_number_doctors_per_page_from_selector() throws Throwable {
        managerDashBordPage.selectDoctorPerPage(NUMBER_DOCTORS_PER_PAGE);
    }

    @Then("^Number of doctors on page in table equals number$")
    public void number_of_doctors_on_page_in_table_equals_number() throws Throwable {
        Assert.assertTrue(managerDashBordPage.getNumberOfRows() <= EXPECTED_NUMBER_OF_DOCTORS_PER_PAGE);

    }

    @When("^Manager select specialization from selector$")
    public void manager_select_specialization_from_selector() throws Throwable {
        managerDashBordPage.selectSpecialization(SPECIALIZATION_VALUE);
        managerDashBordPage.searchButtonClick();
    }

    @Then("^Show rows in table with doctors that match particular specialization$")
    public void show_rows_in_table_with_doctors_that_match_particular_specialization() throws Throwable {
        for(String string: managerDashBordPage.getColumn(SPECIALIZATION_COLUMN)){
            if(!string.equals(SPECIALIZATION_VALUE)) {
                throw new AssertionError("Displayed wrong list of doctors");
            }
        }
    }

    @When("^Manager chose email from search list$")
    public void manager_chose_email_from_search_list() throws Throwable {
        managerDashBordPage.selectSearchBy(EMAIL);

    }

    @When("^Type email in text field$")
    public void type_email_in_text_field() throws Throwable {
        managerDashBordPage.searchByText(DOCTOR_EMAIL);
        managerDashBordPage.searchButtonClick();
    }

    @Then("^Show rows in table with doctors that matched typed email$")
    public void show_rows_in_table_with_doctors_that_matched_typed_email() throws Throwable {
        for(String string: managerDashBordPage.getColumn(EMAIL)){
            if(!string.equals(DOCTOR_EMAIL)){
                throw  new AssertionError("Displayed wrong list of doctors");
            }
        }
    }

    @When("^Manager chose first name from search list$")
    public void manager_chose_first_name_from_search_list() throws Throwable {
        managerDashBordPage.selectSearchBy(FIRST_NAME);
    }

    @When("^Type name in text field$")
    public void type_name_in_text_field() throws Throwable {
        managerDashBordPage.searchByText(DOCTOR_NAME);
        managerDashBordPage.searchButtonClick();
    }

    @Then("^Show rows in table with doctors that match typed first name$")
    public void show_rows_in_table_with_doctors_that_match_typed_first_name() throws Throwable {
        for(String string: managerDashBordPage.getColumn(FIRST_NAME)){
            if(!string.equals(DOCTOR_NAME)){
                throw  new AssertionError("Displayed wrong list of doctors");
            }
        }
    }

    @When("^Manager check default number of rows in table$")
    public void manager_check_default_number_of_rows_in_table() throws Throwable {
        numberOfRows = managerDashBordPage.getNumberOfRows();
    }


    @When("^Then make simple find$")
    public void then_make_simple_find() throws Throwable{
        managerDashBordPage.searchByText(DOCTOR_NAME);
        managerDashBordPage.searchButtonClick();
    }

    @When("^Then click on clear button$")
    public void then_click_on_clear_button() throws Throwable {
        managerDashBordPage.clearButtonClick();
    }

    @Then("^Show rows in the table in initial condition$")
    public void show_rows_in_the_table_in_initial_condition() throws Throwable {
        Assert.assertEquals(managerDashBordPage.getNumberOfRows(), numberOfRows);
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
