package pageObjects.manager.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.allUsers.HospitalSeekerHomePage;
import pageObjects.manager.HospitalsPage;
import pageObjects.manager.SchedulerPage;
import utilities.BaseNavigation;

public class SchedulerPageTest extends FunctionalTest{

    @Test
    public void testDefaultCondition() throws Exception{
        BaseNavigation.login(driver, "manager.jh@hospitals.ua", "1111");
        HospitalsPage hospitalsPage = new HospitalsPage(driver);
        SchedulerPage schedulerPage = hospitalsPage.scheduleButtonClick(1);

        Assert.assertTrue(schedulerPage.checkDefaultConditionScheduler());

    }










    @Test
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
