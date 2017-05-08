package pages.admin;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;
import utils.databaseutil.UserDAO;

import java.util.*;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AllUsersPageTest extends BaseTest {

    @BeforeMethod
    public void before() {
        DriverInitializer.initialization();
    }

    @AfterMethod
    public void after() {
        try {
            BaseNavigation.logout();
            DriverInitializer.close();
        } catch (Exception e) {
            DriverInitializer.close();
        }
    }



    @Test
    public void enableUsersViewTest() {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        allUsersPage = allUsersPage.showEnableUsers();
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
        Assert.assertEquals(actual, true);
    }


    @Test
    public void disableUsersViewTest() {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.sleep(2);
        allUsersPage = allUsersPage.showDisableUsers();
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
        Assert.assertEquals(actual, false);
    }


    @Test
    public void viewWindowTest() {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        List<String> actual = allUsersPage.getUserDataFromInfoWindow(rowNumber);
        List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(1));
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, new String[]{allInfo.get(0),allInfo.get(1), "true"});
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "roles")
    public void changeRoleTest(String role) {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        //BrowserWrapper.sleep(2);
        String expected = role;
        int rowNumber = 1;
        allUsersPage = allUsersPage.changeRoleInEditWindow(rowNumber, role);
        String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "count")
    public void changeCountOfUsersOnPageTest(String count) {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        int expected = Integer.parseInt(count);
        allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
        int actual = allUsersPage.getCountOfUsersInTable();
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "roles")
    public void searchByRoleTest(String role) {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        String expected = role;
        allUsersPage = allUsersPage.changeRole(expected);
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "searchParams")
    public void searchTest(String role, String valueOfField, String count) {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        allUsersPage = allUsersPage.search(Integer.parseInt(count), role, "firstName", valueOfField);
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, new String[]{valueOfField, role});
        TableParser tableParser = new TableParser(allUsersPage.table);
        List<String> actual = new LinkedList<>();
        if (tableParser.getFieldFromTableRow(rowNumber, "@email").contains(valueOfField))actual.add(valueOfField);
        else actual.add("noSame");
        actual.add(tableParser.getFieldFromTableRow(rowNumber, "role"));
        Assert.assertEquals(actual, expected);
    }


    @Test
    public void nextPageButtonTest() {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        AllUsersPage allUsersPage1 = allUsersPage.toNextPage();
        BrowserWrapper.waitForPage();
        Assert.assertNotEquals(allUsersPage, allUsersPage1);
    }


    @Test
    public void deleteUsersTest() {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        String actual = allUsersPage.getCurrentUrl();
        allUsersPage = allUsersPage.deleteUser(rowNumber);
        String expected = allUsersPage.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "roles")
    public void sortByEmailTest(String role) {
        AllUsersPage allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitForPage();
        allUsersPage.changeRole(role);
        allUsersPage.searchButton.click();
        BrowserWrapper.sleep(2);
        allUsersPage = allUsersPage.clickSortByEmail();
        BrowserWrapper.sleep(2);
        int actual = new TableParser(allUsersPage.table).getFieldFromTableRow(1, "@email").compareToIgnoreCase(new TableParser(allUsersPage.table).getFieldFromTableRow(2, "@email"));
        Assert.assertEquals(actual < 0, true);
    }



    @DataProvider
    public Object[][] roles() {
        return new Object[][] {
                {"MANAGER"},
                {"PATIENT"}
        };
    }

    @DataProvider
    public Object[][] count() {
        return new Object[][] {
                {"20"}
        };
    }

    @DataProvider
    public Object[][] searchParams() {
        return new Object[][] {
                {"MANAGER", "a", "20"}
        };
    }


    public int randomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max-1)+1;
    }

}
