package pages.doctor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.*;


/**
 * Created by Natasha on 18.04.2017.
 */
public class TestPatientRecord extends BaseTest {
    public static final String COMPLAIN = "New complaint";
    public static final String RESULT = "New result";
    public static final String PRESCRIPTION = "New prescription";
    public static final String PATIENT = "patient.sf@hospitals.ua";
    public static final String FIRST_PATIENT = "Charles";
    public static final String SECOND_PATIENT = "Auginas";



    Logger logger = LoggerFactory.getLogger(ListPatientPage.class);

    @BeforeMethod
    public void beforeMethod() throws InterruptedException{
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN,DOCTOR_PASSWORD);
        logger.info("Test is initialized");
    }

    @AfterMethod
    public void after() {
        DriverInitializer.instance().manage().deleteAllCookies();
        BaseNavigation.logout();
        logger.info("Test is over");
    }


    @Test
    public void createNewRecord() throws Exception {
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

    @Test
    public void checkPatientsSearch() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.searchPatients(PATIENT);
        Assert.assertTrue(listPatientPage.checkResultSearch());
        logger.info("Test pass");
    }

    @Test
    public void testPatientsSortingByFirstName() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByFirstNameButton();
        String first_patient_after_sort = listPatientPage.getDataFromTable(1, 3);
        Assert.assertEquals(first_patient_after_sort, FIRST_PATIENT);
        logger.info("Test pass");
    }
    @Test
    public void testPatientsSortingByLastName() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByLastNameButton();
        String first_patient_after_sort = listPatientPage.getDataFromTable(1, 4);
        Assert.assertEquals(first_patient_after_sort, SECOND_PATIENT);
        logger.info("Test pass");
    }
    @Test
    public void testPatientsSortingByEmail() throws Exception {
        ListPatientPage listPatientPage = new ListPatientPage();
        listPatientPage.header.patientsButtonClick();
        listPatientPage.sortByEmailButton();
        BrowserWrapper.sleep(3);
        int actual = new TableParser(listPatientPage.table).getFieldFromTableRow(1, "patient:").compareToIgnoreCase(new TableParser(listPatientPage.table).getFieldFromTableRow(2, "patient:"));
        Assert.assertTrue(actual < 0);
        logger.info("Test pass");
    }


}
