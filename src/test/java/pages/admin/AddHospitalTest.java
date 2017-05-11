package pages.admin;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.DriverInitializer;


public class AddHospitalTest extends BaseTest {

    private static final String ADMIN_LOGIN = "admin@hospitals.ua";
    private static final String ADMIN_PASSWORD = "1111";
    private static final String ALL_HOSPITALS_PAGE_ID_IDENTIFICATION = "googleMap";
    private static final String ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION = "/html/body/section/div/div/div/div[1]/div[1]/a[1]";
    private static final String ADD_HOSPITAL_PAGE_ID_IDENTIFICATION = "image-uploaded";
    private static final String ALL_USERS_PAGE_ID_IDENTIFICATION = "searchButton";
    private static final String VALID_HOSPITAL_ADDRESS = "Musorhs'koho St, 4, Chernivtsi, Chernivets'ka oblast, Ukraine";
    private static final String VALID_HOSPITAL_NAME = "Hospital #15";
    private static final int HOSPITAL_ROW_FOR_DELETE = 9;
    private static final String HOSPITAL_NAME_FOR_EDIT = "Hospital #8";
    private static final String BUILDING_NUMBER_FOR_EDIT_HOSPITAL = "2";
    private static final String STREET_NAME_FOR_EDIT_HOSPITAL_DATA = "Fastivs'ka St";
    private static final int HOSPITAL_ROW_FOR_EDIT = 9;
    private static final String INVALID_HOSPITAL_ADDRESS = "Berezovska St, 14, Glybokaya, Chernivets'ka oblast, Ukraine";
    private static final String HOSPITAL_NAME_FOR_INVALID_ADDRESS = "Hospital #7";
    private static final String NOTHING_INSIDE = "";
    private static final String TEST_STARTED = "Test started!";
    private static final String TEST_PASSED = "Test passed!";
    private static final String TEST_FINISHED = "Test finished!";


    Logger logger = LoggerFactory.getLogger(HospitalListPage.class);

    @BeforeMethod
    public void beforeMethod() {
        BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitUntilElementIsPresent(By.id(ALL_USERS_PAGE_ID_IDENTIFICATION));
        logger.info(TEST_STARTED);
    }


    @Test(dataProvider = "validHospitalAddress", groups = {"Add hospital"})
    public void addNewHospitalWithValidDataTest(String hospitalAddress, String hospitalName, String hospitalDescription) throws Exception {

            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table: " + hospitalsCountOfRow);
            hospitalListPage.submitAddNewHospital();
            BrowserWrapper.waitUntilElementClickableByLocator(By.id(ADD_HOSPITAL_PAGE_ID_IDENTIFICATION));
            AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage();

            addNewHospitalPage.addNewHospital(hospitalAddress, hospitalName, hospitalDescription);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table after added new hospital: " + hospitalsCountOfRowAfterAdding);

            Assert.assertNotEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding);
            logger.info(TEST_PASSED);
    }

    @Test(dataProvider = "loginDataForDeleteHospital", groups = {"Edit/Delete hospital"})
    public void deleteHospitalTest(String login, String password, int hospitalCountForDelete) throws Exception {

            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row: " + hospitalCountOfRow);
            hospitalListPage.deleteHospital(hospitalCountForDelete);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));
            int hospitalCountOfRowAfterDelete = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row after delete: " + hospitalCountOfRowAfterDelete);

            Assert.assertNotEquals(hospitalCountOfRow, hospitalCountOfRowAfterDelete);
            logger.info(TEST_PASSED);
    }

    @Test(dataProvider = "editHospitalBuildingAndStreet", groups = {"Edit/Delete hospital"})
    public void editHospitalTest(String hospitalName, String building, String street, int hospitalCountForEdit) throws Exception {

            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            String actual = hospitalListPage.getHospitalDataFromTableRow(hospitalCountForEdit).toString();
            hospitalListPage.editButton(hospitalCountForEdit);
            BrowserWrapper.waitUntilElementClickableByLocator(By.id(ADD_HOSPITAL_PAGE_ID_IDENTIFICATION));

            AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage();
            addNewHospitalPage.addHospitalName(hospitalName);
            addNewHospitalPage.changeBuilding(building);
            addNewHospitalPage.changeStreet(street);
            addNewHospitalPage.pushSaveButton();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            String expected = hospitalListPage.getHospitalDataFromTableRow(hospitalCountForEdit).toString();

            Assert.assertNotEquals(actual, expected);
            logger.info(TEST_PASSED);
    }
    
    @Test(dataProvider = "invalidHospitalAddress", groups = {"Add hospital"})
    public void addNewHospitalWithInvalidDataTest(String hospitalAddress, String hospitalName, String hospitalDescription) throws Exception {

        HospitalListPage hospitalListPage = new HospitalListPage();
        hospitalListPage.header.goToAllHospitalsPage();
        BrowserWrapper.waitUntilElementClickableByLocator(By.id(ALL_HOSPITALS_PAGE_ID_IDENTIFICATION));

        int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table: " + hospitalsCountOfRow);
        hospitalListPage.submitAddNewHospital();
        AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage();
        BrowserWrapper.waitUntilElementClickableByLocator(By.id(ADD_HOSPITAL_PAGE_ID_IDENTIFICATION));

        addNewHospitalPage.addNewHospital(hospitalAddress, hospitalName, hospitalDescription);
        BrowserWrapper.sleep(1);
        hospitalListPage.header.goToAllHospitalsPage();
        BrowserWrapper.waitUntilElementClickableByLocator(By.id(ALL_HOSPITALS_PAGE_ID_IDENTIFICATION));

        int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
        System.out.println("How much row in the table after added new hospital: " + hospitalsCountOfRowAfterAdding);

        Assert.assertEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding);
        logger.info(TEST_PASSED);
    }

    @DataProvider
    public static Object [] [] validHospitalAddress() {
        return new  Object [] [] {
                {VALID_HOSPITAL_ADDRESS, VALID_HOSPITAL_NAME, NOTHING_INSIDE}
        };
    }

    @DataProvider
    public Object [] [] loginDataForDeleteHospital() {
            return new Object [] [] {
                    {ADMIN_LOGIN, ADMIN_PASSWORD, HOSPITAL_ROW_FOR_DELETE}
            };
    }

    @DataProvider
    public Object [] [] editHospitalBuildingAndStreet() {
        return new Object[][] {
                {HOSPITAL_NAME_FOR_EDIT, BUILDING_NUMBER_FOR_EDIT_HOSPITAL, STREET_NAME_FOR_EDIT_HOSPITAL_DATA, HOSPITAL_ROW_FOR_EDIT}
        };
    }

    @DataProvider
    public Object [] [] invalidHospitalAddress() {
        return new Object[][] {
                {INVALID_HOSPITAL_ADDRESS, HOSPITAL_NAME_FOR_INVALID_ADDRESS, NOTHING_INSIDE}
        };
    }

    @AfterMethod
    public void afterMethod() {
        DriverInitializer.instance().manage().deleteAllCookies();
        BaseNavigation.logout();
        logger.info(TEST_FINISHED);
    }
}
