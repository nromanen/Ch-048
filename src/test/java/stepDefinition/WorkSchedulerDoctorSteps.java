package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.allUsers.HospitalSeekerHomePage;
import pages.doctor.WorkSchedulerPage;
import pages.headers.headersByRole.DoctorHeader;
import utils.BaseNavigation;
import utils.BrowserWrapper;


/**
 * Created by nshtitc on 5/3/2017.
 */
public class WorkSchedulerDoctorSteps {
    public static final String DOCTOR_LOGIN = "doctor.cb@hospitals.ua";
    public static final String DOCTOR_PASSWORD = "1111";
    WorkSchedulerPage workSchedulerPage;
    public static final int EXPECTED_WEEK_SIZE = 6;


    @Given("^DOCTOR is on work scheduler page$")
    public void DOCTOR_is_on_work_scheduler_page() throws Throwable {
      HospitalSeekerHomePage hospitalSeekerHomePage = BaseNavigation.loginAsDoctor(DOCTOR_LOGIN,DOCTOR_PASSWORD);
        workSchedulerPage.header.scheduleButtonClick();

    }
    @When("^DOCTOR press button \"Day\"$")
    public void DOCTOR_press_button_Day() throws Throwable{
        workSchedulerPage.dayTabButtonClick();
    }
    @Then("^Shows the scheduler of the DOCTOR on the day$")
    public void Shows_the_scheduler_of_the_DOCTOR_on_the_day() throws Throwable{
        //Assert.;
    }
    @When("^DOCTOR press button \"Week\"$")
    public void DOCTOR_press_button_Week() throws Throwable{
        workSchedulerPage.weekTabButtonClick();
    }
    @Then("^Shows the scheduler of the DOCTOR on the week$")
    public void Shows_the_scheduler_of_the_DOCTOR_on_the_week() throws Throwable{
        Assert.assertEquals(workSchedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE, "Can't change week size");
    }
    @When("^DOCTOR press button \"Month\"$")
    public void DOCTOR_press_button_Month() throws Throwable{
        workSchedulerPage.monthTabButtonClick();
    }
    @Then("^Shows the scheduler of the DOCTOR on the month$")
    public void Shows_the_scheduler_of_the_DOCTOR_on_the_month() throws Throwable{
        //Assert.assertEquals
    }



}
