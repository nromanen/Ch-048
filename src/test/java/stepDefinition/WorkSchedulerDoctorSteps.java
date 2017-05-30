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
 *This is a class which is collect all tests related to testing Doctor's role.On the top of this
 * class there is all constant values which are used in tests. Tests in this class are: View weekly schedule, See the schedule for today
 *
 * @author Natalia Shtick
 * @version 1.0
 */
public class WorkSchedulerDoctorSteps {
    public static final String DOCTOR_LOGIN = "doctor.cb@hospitals.ua";
    public static final String DOCTOR_PASSWORD = "1111";
    WorkSchedulerPage workSchedulerPage = new WorkSchedulerPage();
    public static final int EXPECTED_WEEK_SIZE = 5;

    @Given("^i sign in as a Doctor and go to Work scheduler page$")
    public void i_sign_in_as_a_Doctor_and_go_to_Work_scheduler_page() throws Throwable{
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN,DOCTOR_PASSWORD);
        workSchedulerPage.header.scheduleButtonClick();
    }

    @When("^DOCTOR press button \"Week\"$")
    public void DOCTOR_press_button_Week() throws Throwable{
        workSchedulerPage.weekTabButtonClick();

    }
    @Then("^Shows the scheduler of the DOCTOR on the week$")
    public void Shows_the_scheduler_of_the_DOCTOR_on_the_week() throws Throwable{
    Assert.assertEquals(workSchedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE, "Can't change week size");
    }

    @When("^DOCTOR press button \"Next\"$")
    public void DOCTOR_press_button_Next() throws Throwable{
       workSchedulerPage.nextDateButtonClick();
    }

    @And("^DOCTOR press button \"Today\"$")
    public void DOCTOR_press_button_Today(){
        workSchedulerPage.todayButtonClick();
           }

    @Then("^Shows the scheduler of the DOCTOR on today$")
    public void Shows_the_scheduler_of_the_DOCTOR_on_today() throws Throwable{
        Assert.assertTrue(workSchedulerPage.checkTodayPresence());
    }

}
