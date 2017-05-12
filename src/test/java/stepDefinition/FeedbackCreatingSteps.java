package stepDefinition;

/**
 * Created by gregtar on 27.04.17.
 */

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pages.allUsers.DoctorInfoPage;
import pages.allUsers.DoctorSearchResultPage;
import pages.allUsers.HospitalSeekerHomePage;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.ManagerHeader;
import pages.manager.HospitalsPage;
import pages.manager.ModerationFeedBackPage;
import utils.BaseNavigation;
import utils.BrowserWrapper;
import utils.databaseutil.UserDAO;


public class FeedbackCreatingSteps {

    public static final String PATIENT_LOGIN = "patient.cd@hospitals.ua";
    public static final String PATIENT_PASSWORD = "1111";

    public static final String MANAGER_LOGIN = "manager.jh@hospitals.ua";
    public static final String MANAGER_PASSWORD = "1111";

    public static final String CURRENT_DOCTOR_SURNAME = "Benington";
    public static final String FEEDBACK_MESSAGE = "The newone feedback";

    private ManagerHeader header;

    public static DoctorInfoPage doctorInfoPage;
    public static ModerationFeedBackPage moderationFeedBackPage;
    public static DoctorSearchResultPage doctorSearchResultPage;


    @When("^i sign in as a PATIENT and move to the current doctorInfoPage$")
    public void i_sign_in_as_a_PATIENT_and_move_to_the_current_doctorInfoPage() throws Throwable {
        UserDAO.deleteAllFeedbacks();
        HospitalSeekerHomePage hospitalSeekerHomePage = BaseNavigation.loginAsPatient(PATIENT_LOGIN, PATIENT_PASSWORD);
        BrowserWrapper.sleep(2);
        hospitalSeekerHomePage.notAuthorizedHeader.findDoctor(CURRENT_DOCTOR_SURNAME);
        doctorInfoPage = doctorSearchResultPage.goToDoctorInfoPage();
    }

    @Then("^click on the feedback field  write Feedback and submit it$")
    public void click_on_the_feedback_field_write_Feedback_and_submit_it() throws Throwable {
        doctorInfoPage.createFeedBack(FEEDBACK_MESSAGE);
    }

    @Then("^feedback field should disappear after page refresh$")
    public void feedback_field_should_disappear_after_page_refresh() throws Throwable {
        Assert.assertFalse(BrowserWrapper.isElementPresent(doctorInfoPage.doctorFeedbackInput));
    }

    @When("^i sign in as a manager and move to the feedbackManagePage$")
    public void i_sign_in_as_a_manager_and_move_to_the_feedbackManagePage() throws Throwable {
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);
        BrowserWrapper.sleep(3);
       // ModerationFeedBackPage moderationFeedBackPage = ModerationFeedBackPage();
     //   header = new ManagerHeader();
      //  moderationFeedBackPage.feedBackPage();


    }

    @And("^approve or disapprove feedback about current doctor$")
    public void approve_or_disapprove_feedback_about_current_doctor() throws Throwable {
       // BrowserWrapper.sleep(2);
        ModerationFeedBackPage moderationFeedBackPage = new ModerationFeedBackPage();
        moderationFeedBackPage.confirmFeedback();
    }

  /*  @Then("^feedback field on manager page should disappear after page refresh$")
    public void feedback_field_on_manager_page_should_disappear_after_page_refresh() throws Throwable {
        BrowserWrapper.sleep(2);
        ModerationFeedBackPage moderationFeedBackPage = new ModerationFeedBackPage();
        System.out.println("Is element Present");
        Assert.assertFalse(BrowserWrapper.isElementPresent(moderationFeedBackPage.patientOneFeedbackBody));
    }*/



    @When("^i sign in as a patient and move to the current doctorInfoPage$")
    public void i_sign_in_as_a_patient_and_move_to_the_current_doctorInfoPage() throws Throwable {
        HospitalSeekerHomePage hospitalSeekerHomePage = BaseNavigation.loginAsPatient(PATIENT_LOGIN, PATIENT_PASSWORD);
        BaseHeader header = new BaseHeader();
        DoctorSearchResultPage doctorSearchResultPage = header.findDoctor(CURRENT_DOCTOR_SURNAME);
        doctorSearchResultPage.goToDoctorInfoPage();
    }

    @Then("^my feedback , wich was written by me early should be added$")
    public void my_feedback_wich_was_written_by_me_early_should_be_added() throws Throwable {
        DoctorInfoPage doctorInfoPage = new DoctorInfoPage();
        doctorInfoPage.infoLabel.click();
        BrowserWrapper.sleep(2);
        String expectedMessage = doctorInfoPage.feedbackText.getText();
        System.out.println("!"+expectedMessage+"!");
        Assert.assertEquals(expectedMessage,FEEDBACK_MESSAGE);
    }


}
