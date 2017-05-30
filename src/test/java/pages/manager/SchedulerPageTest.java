package pages.manager;



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
import utils.databaseutil.UserDAO;

/**
 * This is a class which is collect all tests related to testing Manager's Scheduler Page. On the top of this
 * class there is all constant values which are used in tests. SchedulerPageTest extends BaseTest what gives this
 * class ability of initialization of browser and moving to main page of project. Tests in this class are divided
 * into groups: smokeTest,  schedulerSetting, eventCreation, miscellaneous. This page is also provided loggin for
 * tracing test results
 *
 * @author Yuri Tomko
 * @version 1.0
 */

public class SchedulerPageTest extends BaseTest {

    public static final String TEST_BEGIN_AT_HOUR = "11:00";
    public static final String TEST_END_AT_HOUR = "20:00";
    public static final String TEST_WEEK_SIZE = "6 days";
    public static final int EXPECTED_WEEK_SIZE = 6;
    public static final String EXPECTED_BEGIN_AT_HOUR = "11 00";
    public static final String EXPECTED_END_AT_HOUR = "19 00";
    public static final String TEST_APPOINTMENT_TEXT = "Test event";
    public static final String EXPECTED_APPOINTMENT_TEXT = "Test event";
    public static final String TEST_EDITABLE_APPOINTMENT_TEXT = "Another text";
    public static final String EXPECTED_EDITABLE_APPOINTMENT_TEXT = "Another text";
    public static final String HOSPITAL_NAME = "Miska Poliklinika";
    public static final String DOCTOR_NAME = "Chester";
    private SchedulerPage schedulerPage;
    Logger logger = LoggerFactory.getLogger(SchedulerPage.class);

    /**
     * This is method which is executed before each method in this class
     * It's provide cleaning event table of project database, and then move to the scheduler page
     *
     */
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod()  {
        UserDAO.deleteAllEvents();
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick(DOCTOR_NAME);
        logger.info("Test is initialized");
    }

    /**
     * This is method which is used to clean workflow after test execution
     *
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
            BaseNavigation.logout();
            logger.info("Test is over");
    }

    /**
     * This is a method for checking presence of all essential elements of page
     * @exception AssertionError This exception throws when not element presence
     */
    @Test(groups = {"smokeTest"})
    public void testElementPresence() {
        try{
            schedulerPage.isPageReady();
        }catch (Exception e){
            logger.error("Not all element's found");
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.isPageReady(),"Not all elements are on the page");
        logger.debug("All element present");
    }

    /**
     * This is a method for checking whether all selectors and elements are set to default values
     * @exception AssertionError This exception throws when elements not set to default
     */
    @Test(groups = {"smokeTest"})
    public void testDefaultSchedulerValues(){
        try{
            schedulerPage.checkDefaultConditionScheduler();
        }catch (Exception e){
            logger.error("Default scheduler values not set");
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.checkDefaultConditionScheduler(), "Scheduler values doesn't set to default");
        logger.error("Table set to default values");
    }

    /**
     *  This method is for testing ability of changing amount of working days
     */
    @Test(groups = "scheduleSetting")
    public void testWeekSize(){
        schedulerPage.workWeekSizeSelector(TEST_WEEK_SIZE);
        schedulerPage.saveButtonClick();
        Assert.assertEquals(schedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE, "Can't change week size");
        logger.info("Test pass");
    }

    /**
     * This method is for testing ability of setting hours in which day is begin and end
     */
    @Test(groups = "scheduleSetting")
    public void testWorkingDayDuration(){
        schedulerPage.setDayDuration(TEST_BEGIN_AT_HOUR, TEST_END_AT_HOUR);
        schedulerPage.saveButtonClick();
        Assert.assertTrue(schedulerPage.getBeginningHour().equals(EXPECTED_BEGIN_AT_HOUR) && schedulerPage.getEndingHour().equals(EXPECTED_END_AT_HOUR), "Can't change day duration");
        logger.debug("Test pass");
    }


    /**
     * This is a method for checking ability of creating events for doctor. It is use data provider for checking
     * ability of using latin and cyrillic symbols and empty string.
     *
     * @param actualText Text which is typing into event creation field
     * @param expectedText Text which is expected to appear in table
     */
    @Test(dataProvider = "eventCreation",groups = "eventCreation")
    public void testEventCreation(String actualText, String expectedText){
        schedulerPage.createAppointment(actualText);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick(DOCTOR_NAME);
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.isEventsPresent() && schedulerPage.isEventOnWeekTab(expectedText), "Can't create event");
        logger.info("Test pass");
    }

    /**
     * This is a method for checking ability of deletion event from table
     */
    @Test(groups = "eventCreation")
    public void testEventDeletion(){
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        schedulerPage.deleteEventButtonClick();
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick(DOCTOR_NAME);
        schedulerPage.nextButtonClick();
        Assert.assertFalse( schedulerPage.isEventsPresent(), "Cant edit event");
        logger.info("Test pass");
    }

    /**
     * This is a method for checking ability of edition event in the table
     */
    @Test(groups = "eventCreation")
    public void testEventEdition(){
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        schedulerPage.editEventText(TEST_EDITABLE_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick(DOCTOR_NAME);
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.isEventsPresent() && schedulerPage.isEventOnWeekTab(EXPECTED_EDITABLE_APPOINTMENT_TEXT), "Can't edit event");
        logger.info("Test pass");
    }

    /**
     * This is a method for checking ability of canceling event creation
     */
    @Test(groups = "eventCreation")
    public void testEventCancel(){
        schedulerPage.nextButtonClick();
        schedulerPage.inputEvent(TEST_APPOINTMENT_TEXT);
        schedulerPage.cancelButtonClick();
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        Assert.assertFalse( schedulerPage.isEventsPresent(), "Can't cancel event creation");
        logger.info("Test pass");

    }

    /**
     * This is a method for checking ability of creating event on day tab of table
     */
    @Test(groups = "eventCreation")
    public void testCreateEventDayTab() {
        schedulerPage.dayTabButtonClick();
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick(DOCTOR_NAME);
        schedulerPage.dayTabButtonClick();
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.isEventsPresent() && schedulerPage.getEvents().contains(EXPECTED_APPOINTMENT_TEXT),"Can't create event at day tab");
        logger.info("Test pass");
    }

    /**
     * This is a method for checking ability of creating event on month tab of table
     */
    @Test(groups = "eventCreation")
    public void testCreateEventMonthTab(){
        schedulerPage.monthTabButtonClick();
        schedulerPage.createEventCalendar(TEST_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick(DOCTOR_NAME);
        schedulerPage.monthTabButtonClick();
        schedulerPage.nextButtonClick();
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.isEventsPresentOnCalendar() && schedulerPage.isEventOnCalendarTab(EXPECTED_APPOINTMENT_TEXT),"Can't create event on month tab" );
        logger.info("Test pass");
    }

    /**
     * This is method for checking proper behavior of mini calendar button
     */
    @Test(groups = "miscellaneous")
    public void testMiniCalendar(){
        schedulerPage.miniCalendarButtonClick();
        Assert.assertTrue(schedulerPage.checkMiniCalendarVisibility());
        logger.info("Test pass");
    }

    /**
     * This is method for checking proper behavior of today button
     */
    @Test(groups = "miscellaneous")
    public void testTodayButton(){
        schedulerPage.nextButtonClick();
        schedulerPage.todayButtonClick();
        Assert.assertTrue(schedulerPage.checkTodayPresence());
        logger.info("Test pass");
    }

    /**
     * This is method for testing pop up when manager close windows after making some changes without saving
     */
    @Test(groups = "miscellaneous")
    public void testAlertIfNotSaved(){
        schedulerPage.nextButtonClick();
        schedulerPage.createAppointmentWithoutSave(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        boolean present = BrowserWrapper.isAlertPresent();
        BrowserWrapper.confirmAlert();
        schedulerPage.saveButtonClick();
        Assert.assertTrue(present);
        logger.info("Test pass");
    }

    @DataProvider(name = "eventCreation")
    public static Object[][] getData()
    {
        return  new Object[][]{
                {"Test text", "Test text"},
               {" ", ""},
                {"Тестовий текст", "Тестовий текст"}};
    }
}
