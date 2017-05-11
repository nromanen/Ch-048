package stepDefinition;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.manager.HospitalsPage;
import pages.manager.ManagerDashBordPage;
import utils.BaseNavigation;


/**
 * Created by radgast on 22.04.17.
 */
public class DoctorSearchSteps  {
    public static final String HOSPITAL_NAME = "Miska Poliklinika";
    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";
    public static final String NUMBER_DOCTORS_PER_PAGE = "20";
    public static final int EXPECTED_NUMBER_OF_DOCTORS_PER_PAGE= 20;
    public static final String SPECIALIZATION = "Dentist";
    public static final String SPECIALIZATION_COLUMN = "Specialization";
    public static final String DOCTOR_EMAIL = "doctor.cb@hospitals.ua";
    public static final String EMAIL = "Email";
    public static final String FIRST_NAME = "First Name";
    public static final String DOCTOR_NAME = "Chester";


    private ManagerDashBordPage managerDashBordPage;
    private int numberOfRows;

    @Given("^the manager is on dashboard of particular hospital in order to search$")
    public void the_manager_is_on_dashboard_of_particular_hospital_in_order_to_search() throws Throwable {
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);
        managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
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

        managerDashBordPage.selectSpecialization(SPECIALIZATION);
        managerDashBordPage.searchButtonClick();
    }

    @Then("^Show rows in table with doctors that match particular specialization$")
    public void show_rows_in_table_with_doctors_that_match_particular_specialization() throws Throwable {
        for(String string: managerDashBordPage.getColumn(SPECIALIZATION_COLUMN)){
            if(!string.equals(SPECIALIZATION)) {
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

}
