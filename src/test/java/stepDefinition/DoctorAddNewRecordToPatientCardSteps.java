package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.doctor.CreateNewRecordPage;
import pages.doctor.ListPatientPage;
import pages.doctor.PatientsCardPage;
import pages.headers.headersByRole.DoctorHeader;
import pages.headers.headersByRole.PatientHeader;
import pages.patient.CardPage;
import utils.BaseNavigation;
import utils.BrowserWrapper;


public class DoctorAddNewRecordToPatientCardSteps {

    private static final String DOCTOR_LOGIN = "doctor.cb@hospitals.ua";
    private static final String DOCTOR_PASSWORD = "1111";
    private static final String PATIENT_LOGIN = "patient.ta@hospitals.ua";
    private static final String PATIENT_PASSWORD = "1111";
    private static final String DOCTOR_NAME = "Chester Benington";
    private static final String PATIENT_NAME = "Thomas Auginas";
    private static final String COMPLAINT_FIELD_FROM_CREATE_NEW_RECORD_PAGE = "Depression";
    private static final String RESULT_FIELD_FROM_CREATE_NEW_RECORD_PAGE = "Reduction of the general emotional background";
    private static final String PRESCRIPTION_FIELD_FROM_CREATE_NEW_RECORD_PAGE = "Antidepressants";
    private static final String DOCTOR_HOME_PAGE_CSS_SELECTOR_FOR_WAIT = "a[href=\"/HospitalSeeker/patients\"]";
    private static final String PATIENTS_LIST_OF_CURRENT_DOCTOR = "usr";
    private static final String PATIENT_CARD_AT_DOCTOR_ROLE_CSS_SELECTOR_FOR_WAIT = "a.btn.btn-info";


    @When("^i sign in as a Doctor and go to Create New Record Page$")
    public void iSignInAsADoctorAndGoToCreateNewRecordPage() throws Throwable {
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN, DOCTOR_PASSWORD);
        BrowserWrapper.waitUntilElementClickableByLocator(By.cssSelector(DOCTOR_HOME_PAGE_CSS_SELECTOR_FOR_WAIT));
        DoctorHeader doctorHeader = new DoctorHeader();
        doctorHeader.patientsButtonClick();
        BrowserWrapper.waitUntilElementIsPresent(By.id(PATIENTS_LIST_OF_CURRENT_DOCTOR));
        ListPatientPage listPatientPage = new ListPatientPage();
        PatientsCardPage patientsCardPage = new PatientsCardPage();
        listPatientPage.getPatientsCardClick();
        BrowserWrapper.waitUntilElementClickableByLocator(By.cssSelector(PATIENT_CARD_AT_DOCTOR_ROLE_CSS_SELECTOR_FOR_WAIT));
        patientsCardPage.addNewRecordButtonClick();
    }

    @And("^doctor fills up all required fields and click button Submit$")
    public void doctor_fills_up_all_required_fields_and_click_button_Submit() throws Throwable {
        CreateNewRecordPage createNewRecordPage = new CreateNewRecordPage();
        PatientsCardPage patientsCardPage = new PatientsCardPage();
        createNewRecordPage.inputRecord(COMPLAINT_FIELD_FROM_CREATE_NEW_RECORD_PAGE, RESULT_FIELD_FROM_CREATE_NEW_RECORD_PAGE, PRESCRIPTION_FIELD_FROM_CREATE_NEW_RECORD_PAGE);
        patientsCardPage.checkAddNewRecordButton();
    }

    @Then("^doctor must see page Patient card Thomas Auginas with one additional record$")
    public void doctorMustSeePagePatientCardThomasAuginasWithOneAdditionalRecord() {
        PatientsCardPage patientsCardPage = new PatientsCardPage();
        Assert.assertTrue(patientsCardPage.getDoctorNameFromRecord().contains(DOCTOR_NAME));
    }

    @When("^i sign in as a Patient and go to Card Page$")
    public void iSignInAsAPatientAndGoToCardPage() throws Throwable {
        BaseNavigation.loginAsPatient(PATIENT_LOGIN, PATIENT_PASSWORD);
        BrowserWrapper.sleep(3);
        PatientHeader patientHeader = new PatientHeader();
        patientHeader.isActionsButtonPresent();
        patientHeader.goToCardPage();
    }

    @Then("^patient must see information on the card after a doctor created new record$")
    public void patient_must_see_information_on_the_card_after_a_doctor_created_new_record() throws Throwable {
        CardPage cardPage = new CardPage();
        Assert.assertTrue(cardPage.getDoctorNameFromNewRecord().contains(DOCTOR_NAME));
    }

}
