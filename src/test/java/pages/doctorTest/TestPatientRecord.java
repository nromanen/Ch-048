package pages.doctorTest;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.doctor.CreateNewRecordPage;
import pages.doctor.ListPatientaPage;
import pages.doctor.PatientsCardPage;
import pages.headers.headersByRole.DoctorHeader;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.DriverInitializer;


/**
 * Created by Natasha on 18.04.2017.
 */
public class TestPatientRecord extends BaseTest {


    @AfterMethod
    public void after() {
        DriverInitializer.close();

    }


    @Test

     public void createNewRecord() throws Exception {
       WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(),5);
       BaseNavigation.loginAsDoctor(DOCTOR_LOGIN, DOCTOR_PASSWORD);

      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
      DoctorHeader doctorHeader = new DoctorHeader();
      doctorHeader.patientsButtonClick();

        ListPatientaPage listPatientaPage = new ListPatientaPage();
        BrowserWrapper.sleep(3);

        listPatientaPage.getPatientsCardClick();
        BrowserWrapper.sleep(5);

        PatientsCardPage patientsCardPage = new PatientsCardPage();
        patientsCardPage.addNewRecordButtonClick();
        CreateNewRecordPage createNewRecord = new CreateNewRecordPage();
        createNewRecord.inputRecord("New complaint", "New result", "New prescription");

        Assert.assertTrue(patientsCardPage.checkRecord());
    }
  @Test
    public void checkPatientsSearch() throws Exception {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(), 10);
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader();
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.searchPatients("patient.sf@hospitals.ua");
        BrowserWrapper.sleep(2);

        Assert.assertFalse(listPatientaPage.checkResultSearch());
    }

    @Test
    public void testPatientsSortingByFirstName() throws Exception {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(),5);
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader();
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.sortByFirstNameButton();
        BrowserWrapper.sleep(5);

        String firstPatientAfterSort = listPatientaPage.getDataFromTable(1, 3);

        Assert.assertEquals(firstPatientAfterSort, "Charles");
    }
    @Test
    public void testPatientsSortingByLastName() throws Exception {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(), 5);
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader();
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.sortByLastNameButton();
        BrowserWrapper.sleep(5);

        String firstPatientAfterSort = listPatientaPage.getDataFromTable(1, 4);

        Assert.assertEquals(firstPatientAfterSort, "Auginas");
    }
    @Test
    public void testPatientsSortingByEmail() throws Exception {
        WebDriverWait wait = new WebDriverWait(DriverInitializer.instance(),5);
        BaseNavigation.loginAsDoctor(DOCTOR_LOGIN, DOCTOR_PASSWORD);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div/div")));
        DoctorHeader doctorHeader = new DoctorHeader();
        doctorHeader.patientsButtonClick();
        ListPatientaPage listPatientaPage = new ListPatientaPage();
        listPatientaPage.sortByEmailButton();
        BrowserWrapper.sleep(5);

        String firstPatientAfterSort = listPatientaPage.getDataFromTable(1, 2);

        Assert.assertEquals(firstPatientAfterSort, "a@gmail.com");
    }


}
