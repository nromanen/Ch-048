package stepDefinition;

/**
 * Created by gregtar on 27.04.17.
 */

import cucumber.api.java.en.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.allUsers.DoctorInfoPage;
import pages.allUsers.DoctorSearchResultPage;
import pages.allUsers.*;
import pages.headers.BaseHeader;
import pages.manager.HospitalsPage;
import pages.manager.ModerationFeedBackPage;
import sun.security.util.PendingException;
import utils.BaseNavigation;
import utils.BrowserWrapper;


public class FeedbackCreatingSteps {

    public static final String PATIENT_LOGIN = "patient.cd@hospitals.ua";
    public static final String PATIENT_PASSWORD = "1111";

    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";

    public static final String CURRENT_DOCTOR_SURNAME = "Benington";
    public static final String FEEDBACK_MESSAGE = "Test feedback about";

    public static final String SEARCH_BUTTON_ID = "select_doctor_search";

    public static DoctorInfoPage doctorInfoPage;
    public static ModerationFeedBackPage moderationFeedBackPage;
    public static DoctorSearchResultPage doctorSearchResultPage;


    @When("^i sign in as a PATIENT and move to the current doctorInfoPage$")
    public void i_sign_in_as_a_PATIENT_and_move_to_the_current_doctorInfoPage() throws Throwable {
        HospitalSeekerHomePage hospitalSeekerHomePage = BaseNavigation.loginAsPatient(PATIENT_LOGIN, PATIENT_PASSWORD);
        BrowserWrapper.sleep(3);
        BaseHeader header = new BaseHeader();
        DoctorSearchResultPage doctorSearchResultPage = header.findDoctor(CURRENT_DOCTOR_SURNAME);
        DoctorInfoPage doctorInfoPage = doctorSearchResultPage.goToDoctorInfoPage();
        throw new PendingException();
    }

    @Then("^click on the feedback field  write Feedback and submit it$")
    public void click_on_the_feedback_field_write_Feedback_and_submit_it() throws Throwable {
        doctorInfoPage.createFeedBack(FEEDBACK_MESSAGE);
        throw new PendingException();
    }

    @Then("^feedback field should disappear after page refresh$")
    public void feedback_field_should_disappear_after_page_refresh() throws Throwable {
        BrowserWrapper.sleep(3);
        Assert.assertFalse(BrowserWrapper.isElementPresent(doctorInfoPage.doctorFeedbackInput));
        throw new PendingException();
    }

    @When("^i sign in as a manager and move to the feedbackManagePage$")
    public void i_sign_in_as_a_manager_and_move_to_the_feedbackManagePage() throws Throwable {
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);

        BrowserWrapper.sleep(5);
        throw new PendingException();
    }

    @And("^approve or disapprove feedback about current doctor$")
    public void approve_or_disapprove_feedback_about_current_doctor() throws Throwable {
        moderationFeedBackPage = moderationFeedBackPage.confirmFeedback();
        BrowserWrapper.sleep(5);
        throw new PendingException();
    }

    @Then("^feedback field on manager page should disappear after page refresh$")
    public void feedback_field_on_manager_page_should_disappear_after_page_refresh() throws Throwable {
        BrowserWrapper.sleep(5);
        Assert.assertFalse(BrowserWrapper.isElementPresent(moderationFeedBackPage.patientOneFeedbackBody));
        throw new PendingException();
    }



    @When("^i sign in as a patient and move to the current doctorInfoPage$")
    public void i_sign_in_as_a_patient_and_move_to_the_current_doctorInfoPage() throws Throwable {
        HospitalSeekerHomePage hospitalSeekerHomePage = BaseNavigation.loginAsPatient(PATIENT_LOGIN, PATIENT_PASSWORD);
        BaseHeader header = new BaseHeader();
        BrowserWrapper.sleep(5);
        DoctorSearchResultPage doctorSearchResultPage = header.findDoctor(CURRENT_DOCTOR_SURNAME);
        BrowserWrapper.sleep(5);
        DoctorInfoPage doctorInfoPage = doctorSearchResultPage.goToDoctorInfoPage();
        throw new PendingException();
    }

    @Then("^my feedback , wich was written by me early should be added$")
    public void my_feedback_wich_was_written_by_me_early_should_be_added() throws Throwable {
        BrowserWrapper.sleep(5);
        Assert.assertEquals(FEEDBACK_MESSAGE,doctorInfoPage.doctorInfoLabel.getText());
        throw new PendingException();
    }







}
