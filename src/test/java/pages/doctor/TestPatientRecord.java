package pages.doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.*;

/**
 *This is a class which is collect all tests related to testing Doctor's role.On the top of this
 * class there is all constant values which are used in tests. Tests in this class are: createNewRecord, checkPatientsSearchByEmail, testPatientsSortingByFirstName,
 * testPatientsSortingByLastName, testPatientsSortingByEmail.
 *
 * @author Natalia Shtick
 * @version 1.0
 */
public class TestPatientRecord extends BaseTest {
    public static final String COMPLAIN = "New complaint";
    public static final String RESULT = "New result";
    public static final String PRESCRIPTION = "New prescription";
    public static final String PATIENT = "patient.sf@hospitals.ua";
    public static final String FIRST_PATIENT = "Charles";
    public static final String SECOND_PATIENT = "Auginas";



    Logger logger = LoggerFactory.getLogger(ListPatientPage.class);
    /**
     * This is method which is executed before each method in this class.
     */
    @BeforeMethod
    public void beforeMethod() throws InterruptedException{
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN,DOCTOR_PASSWORD);
        logger.info("Test is initialized");
    }
    /**
     * This is method which is used to clean workflow after test execution.
     */
    @AfterMethod
    public void after() {
        BaseNavigation.logout();
        logger.info("Test is over");
    }

    /**
     * It is a method to check the possibility of creating a record in the patient's card.
     */
    @Test
    public void createNewRecord()  {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.getPatientsCardClick();
        PatientsCardPage patientsCardPage = new PatientsCardPage();
        patientsCardPage.addNewRecordButtonClick();
        CreateNewRecordPage createNewRecord = new CreateNewRecordPage();
        createNewRecord.inputRecord(COMPLAIN, RESULT, PRESCRIPTION);
        Assert.assertTrue(patientsCardPage.checkRecord());
        logger.debug("Create new record");
    }

    /**
     * It is a method to test the ability to find a patient by email
     */
    @Test
    public void checkPatientsSearchByEmail() {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.searchPatients(PATIENT);
        String searchPatient = listPatientPage.getDataFromTable(1, 2);
        Assert.assertNotEquals(searchPatient, PATIENT);
        logger.info("Test pass");
    }

    /**
     * This is a method for verification of sorting of patients by first name.
     */

    @Test
    public void testPatientsSortingByFirstName()  {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByFirstNameButton();
        String first_patient_after_sort = listPatientPage.getDataFromTable(1, 3);
        Assert.assertEquals(first_patient_after_sort, FIRST_PATIENT);
        logger.info("Test pass");
    }

    /**
     * This is a method for verification of sorting of patients by last name.
     */
    @Test
    public void testPatientsSortingByLastName() {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByLastNameButton();
        String first_patient_after_sort = listPatientPage.getDataFromTable(1, 4);
        Assert.assertEquals(first_patient_after_sort, SECOND_PATIENT);
        logger.info("Test pass");
    }

    /**
     * This is a method for verification of sorting of patients by email.
     */
    @Test
    public void testPatientsSortingByEmail() {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByEmailButton();
        BrowserWrapper.sleep(3);
        int actual = new TableParser(listPatientPage.table).getFieldFromTableRow(1, "patient:").compareToIgnoreCase(new TableParser(listPatientPage.table).getFieldFromTableRow(2, "patient:"));
        Assert.assertTrue(actual < 0);
        logger.info("Test pass");
    }


}
