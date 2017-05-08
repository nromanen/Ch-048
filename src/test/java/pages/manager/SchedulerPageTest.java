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
    private SchedulerPage schedulerPage;
    Logger logger = LoggerFactory.getLogger(SchedulerPage.class);

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod()  {
        UserDAO.deleteAllEvents();
        HospitalsPage hospitalsPage = BaseNavigation.loginAsManager(MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        logger.info("Test is initialized");


    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(){
            BaseNavigation.logout();
            logger.info("Test is over");
    }


    @Test(groups = {"smoke test"})
    public void testElementPresence() {
        try{
            BrowserWrapper.waitForPage();
            schedulerPage.isPageReady();
        }catch (Exception e){
            logger.error("Not all element's found");
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.isPageReady(),"Not all elements are on the page");
        logger.debug("All element present");
    }

    @Test(groups = {"smoke test"})
    public void testDefaultSchedulerValues(){
        BrowserWrapper.waitForPage();
        try{
            schedulerPage.checkDefaultConditionScheduler();
        }catch (Exception e){
            logger.error("Default scheduler values not set");
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.checkDefaultConditionScheduler(), "Scheduler values doesn't set to default");
        logger.error("Table set to default values");
    }


    @Test(groups = "schedule setting")
    public void testWeekSize(){
        schedulerPage.workWeekSizeSelector(TEST_WEEK_SIZE);
        schedulerPage.saveButtonClick();
        Assert.assertEquals(schedulerPage.getDaysNumber(), EXPECTED_WEEK_SIZE, "Can't change week size");
        logger.info("Test pass");
    }

    @Test(groups = "schedule setting")
    public void testWorkingDayDuration(){
        schedulerPage.setDayDuration(TEST_BEGIN_AT_HOUR, TEST_END_AT_HOUR);
        schedulerPage.saveButtonClick();
        Assert.assertTrue(schedulerPage.getBeginningHour().equals(EXPECTED_BEGIN_AT_HOUR) && schedulerPage.getEndingHour().equals(EXPECTED_END_AT_HOUR), "Can't change day duration");
        logger.debug("Test pass");
    }



    @Test(dataProvider = "eventCreation",groups = "event creation")
    public void testEventCreation(String actualText, String expectedText){
        schedulerPage.createAppointment(actualText);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(expectedText), "Can't create event");
        logger.info("Test pass");
    }

    @Test(groups = "event creation")
    public void testEventDeletion(){
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        schedulerPage.deleteEventButtonClick();
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.nextButtonClick();
        Assert.assertEquals( schedulerPage.getEvents().size(), 0, "Cant edit event");
        logger.info("Test pass");
    }

    @Test(groups = "event creation")
    public void testEventEdition(){
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        schedulerPage.editEventText(TEST_EDITABLE_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(EXPECTED_EDITABLE_APPOINTMENT_TEXT), "Can't edit event");
        logger.info("Test pass");
    }
    @Test(groups = "event creation")
    public void testEventCancel(){
        schedulerPage.nextButtonClick();
        schedulerPage.inputEvent(TEST_APPOINTMENT_TEXT);
        schedulerPage.cancelButtonClick();
        BrowserWrapper.refreshPage();
        schedulerPage.nextButtonClick();
        Assert.assertEquals( schedulerPage.getEvents().size(), 0, "Can't cancel event creation");
        logger.info("Test pass");

    }

    @Test(groups = "event creation")
    public void testCreateEventDayTab() {
        schedulerPage.dayTabButtonClick();
        schedulerPage.createAppointment(TEST_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.dayTabButtonClick();
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains(EXPECTED_APPOINTMENT_TEXT),"Can't create event at day tab");
        logger.info("Test pass");
    }

    @Test(groups = "event creation")
    public void testCreateEventMonthTab(){
        schedulerPage.monthTabButtonClick();
        schedulerPage.createEventCalendar(TEST_APPOINTMENT_TEXT);
        BaseNavigation.logout();
        HospitalsPage hospitalsPage =BaseNavigation.loginAsManager( MANAGER_LOGIN, MANAGER_PASSWORD);
        ManagerDashBordPage managerDashBordPage =  hospitalsPage.choseHospital(HOSPITAL_NAME);
        schedulerPage = managerDashBordPage.scheduleButtonClick("Chester");
        schedulerPage.monthTabButtonClick();
        schedulerPage.nextButtonClick();
        Assert.assertTrue( schedulerPage.getEventsCalendar().size() > 0 && schedulerPage.getEventsCalendar().get(0).contains(EXPECTED_APPOINTMENT_TEXT));
        logger.info("Test pass");
    }


    @Test(groups = "miscellaneous")
    public void testMiniCalendar(){
        schedulerPage.miniCalendarButtonClick();
        Assert.assertTrue(schedulerPage.checkMiniCalendarVisibility());
        logger.info("Test pass");
    }

    @Test(groups = "miscellaneous")
    public void testTodayButton(){
        schedulerPage.nextButtonClick();
        schedulerPage.todayButtonClick();
        Assert.assertTrue(schedulerPage.checkTodayPresence());
        logger.info("Test pass");
    }


    @Test(groups = "miscellaneous")
    public void testAlertIfNotSaved(){
        schedulerPage.nextButtonClick();
        schedulerPage.createAppointmentWithoutSave(TEST_APPOINTMENT_TEXT);
        BrowserWrapper.refreshPage();
        boolean present = BrowserWrapper.isAlertPresent();
        BrowserWrapper.conformAlert();
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
