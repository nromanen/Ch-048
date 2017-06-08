package pages.admin;

import org.openqa.selenium.Alert;
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

/**
 * This is a class which is collect all tests related to testing Add Hospital and Hospitals List Pages. On the top of this
 * class there is all constant values which are used in tests. AddHospitalTest extends BaseTest what gives this
 * class ability of initialization of browser and moving to main page of project. Tests in this class are divided
 * into groups: Add hospital and Edit/Delete hospital. This page is also provided logging for
 * tracing test results
 */

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

    /**
     * This is method which is executed before each method in this class
     */

    @BeforeMethod
    public void beforeMethod() {
        DriverInitializer.instance().manage().window().maximize();
        BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitUntilElementIsPresent(By.id(ALL_USERS_PAGE_ID_IDENTIFICATION));
        logger.info(TEST_STARTED);
    }

    /**
     * This method is for testing add new hospital with valid data
     * @param hospitalAddress  Text which is typing into address field
     * @param hospitalName     Text which is typing into hospital name field
     * @param hospitalDescription       Text which is typing into hospital description field
     */

  //  @Test(dataProvider = "validHospitalAddress", groups = {"Add hospital"})
    public void addNewHospitalWithValidDataTest(String hospitalAddress, String hospitalName, String hospitalDescription) {

            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table: " + hospitalsCountOfRow);
            hospitalListPage.submitAddNewHospital();
            AddNewHospitalPage addNewHospitalPage = new AddNewHospitalPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.id(ADD_HOSPITAL_PAGE_ID_IDENTIFICATION));


            addNewHospitalPage.addNewHospital(hospitalAddress, hospitalName, hospitalDescription);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalsCountOfRowAfterAdding = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("How much row in the table after added new hospital: " + hospitalsCountOfRowAfterAdding);

            Assert.assertNotEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding, "Hospital don't add!");
            logger.info(TEST_PASSED);
    }

    /**
     * This method is for testing delete hospital from hospital list
     * @param hospitalCountForDelete        Number of row which we want to delete
     */

    @Test(dataProvider = "loginDataForDeleteHospital", groups = {"Edit/Delete hospital"})
    public void deleteHospitalTest(int hospitalCountForDelete) {

            HospitalListPage hospitalListPage = new HospitalListPage();
            hospitalListPage.header.goToAllHospitalsPage();
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));

            int hospitalCountOfRow = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row: " + hospitalCountOfRow);
            hospitalListPage.deleteHospital(hospitalCountForDelete);
            BrowserWrapper.waitUntilElementClickableByLocator(By.xpath(ALL_HOSPITALS_PAGE_XPATH_IDENTIFICATION));
            int hospitalCountOfRowAfterDelete = hospitalListPage.getCountOfHospitalsInTable();
            System.out.println("Hospital count of row after delete: " + hospitalCountOfRowAfterDelete);

            Assert.assertNotEquals(hospitalCountOfRow, hospitalCountOfRowAfterDelete, "Hospital don't delete!");
            logger.info(TEST_PASSED);
    }

    /**
     *      This method is for testing edit hospital data
     * @param hospitalName    Text which change hospital name field
     * @param building      Text which change hospital building number field
     * @param street        Text which change street name in hospital street field
     * @param hospitalCountForEdit      Number of row which we want to edit
     */

    @Test(dataProvider = "editHospitalBuildingAndStreet", groups = {"Edit/Delete hospital"})
    public void editHospitalTest(String hospitalName, String building, String street, int hospitalCountForEdit) {

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

            Assert.assertNotEquals(actual, expected, "Hospital don't edit!");
            logger.info(TEST_PASSED);
    }

    /**
     *      This method is for testing add new hospital with invalid data
     * @param hospitalAddress       Text which is typing into address field
     * @param hospitalName      Text which is typing into hospital name field
     * @param hospitalDescription       Text which is typing into hospital description field
     */
    
 //   @Test(dataProvider = "invalidHospitalAddress", groups = {"Add hospital"})
    public void addNewHospitalWithInvalidDataTest(String hospitalAddress, String hospitalName, String hospitalDescription) {

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

        Assert.assertEquals(hospitalsCountOfRow, hospitalsCountOfRowAfterAdding, "Hospital don't add!");
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
                    {HOSPITAL_ROW_FOR_DELETE}
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

    /**
     * This is method which is used to clean workflow after test execution
     */

    @AfterMethod
    public void afterMethod() {
        DriverInitializer.instance().manage().deleteAllCookies();
        BaseNavigation.logout();
        logger.info(TEST_FINISHED);
    }
}
