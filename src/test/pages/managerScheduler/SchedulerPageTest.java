package pages.managerScheduler;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.manager.HospitalsPage;
import pages.manager.SchedulerPage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;


public class SchedulerPageTest extends BaseTest{

    @Test
    public void testDefaultCondition() throws Exception{
        BaseNavigation.loginAsManager(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.sleep(6);
        try{
            schedulerPage.isPageReady();
        }catch (Exception e){
            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(schedulerPage.isPageReady());

    }



//    @Test
//    public void testSchedulerCreation() throws Exception{
//        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
//        HospitalsPage hospitalsPage = new HospitalsPage(driver);
//        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
//        BrowserWrapper.sleep(6);
//        schedulerPage.workWeekSizeSelector("6 days");
//
//
//        Assert.assertEquals(schedulerPage.getDaysNumber(), 6);
//        Assert.assertEquals(schedulerPage.getBeginningHour(),"11 00");
//        Assert.assertEquals(schedulerPage.getEndingHour(), "19 00");
//    }

    @Test
    public void testWeekSize() throws Exception{
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.sleep(6);
        schedulerPage.workWeekSizeSelector("6 days");
        Assert.assertEquals(schedulerPage.getDaysNumber(), 6);
    }

    @Test
    public void testWorkingDayDuration() throws Exception {

        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        BrowserWrapper.sleep(6);
        schedulerPage.workDayBeginAtSelector("11:00");
        schedulerPage.workDayEndAtSelector("20:00");
        schedulerPage.saveButtonClick();
        Assert.assertEquals(schedulerPage.getBeginningHour(),"11 00");
        Assert.assertEquals(schedulerPage.getEndingHour(), "19 00");
    }



   // @Test
    public void testEventCreation() throws Exception{
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);
        schedulerPage.setAppointment("Test",3);
        schedulerPage.saveButtonClick();

        schedulerPage.managerHeader.logOut();

        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        hospitalsPage = new HospitalsPage(driver);
        schedulerPage = hospitalsPage.scheduleButtonClick(1);
        Assert.assertTrue( schedulerPage.getEvents().size() > 0 && schedulerPage.getEvents().contains("Test"));
    }
}
