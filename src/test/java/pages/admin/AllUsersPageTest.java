package pages.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;
import utils.databaseutil.DatabaseOperations;
import utils.databaseutil.UserDAO;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 * This is a class which is collect of test related to testing ALL USERS page.
 *
 * @author Evgen Korcheviy
 */
public class AllUsersPageTest extends BaseTest {

    private AllUsersPage allUsersPage;
    private static TableParser tableParser;

    Logger logger = LoggerFactory.getLogger(AllUsersPage.class);

    /**
     * This is method which is executed before each method in this class
     * It's provide restoring database before test and then move to ALL USERS page
     */
    @BeforeMethod
    public void beforeMethod() {
        DriverInitializer.getToUrl(BASE_URL);
        DatabaseOperations.restore("test_backup.backup");
        allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        tableParser = new TableParser(allUsersPage.table);
        logger.info("Test is initialized");
    }

    /**
     * This is method is executed after each method in this class
     * It's provide logout and closing browser after test
     */
    @AfterMethod
    public void afterMethod() {
        BaseNavigation.logout();
        logger.info("Test is close");
        DriverInitializer.close();
    }

    /**
     * This is test of functionality "enable" button
     */
    @Test
    public void enableUsersViewTest() {
        allUsersPage = allUsersPage.showEnableUsers();
        boolean actual = UserDAO.getStatusByEmail(tableParser.getEmailFromFirstTableRow());
        Assert.assertTrue(actual, "It isn't only enable users");
        logger.info("Test pass");
    }


    /**
     * This is test of functionality of "disable" button
     */
    @Test
    public void disableUsersViewTest() {
        allUsersPage = allUsersPage.showDisableUsers();
        boolean actual = UserDAO.getStatusByEmail(tableParser.getEmailFromFirstTableRow());
        Assert.assertFalse(actual, "It isn't only disable users");
        logger.info("Test pass");
    }


    /**
     * This is method for checking functionality of "view user info"
     */
    @Test
    public void viewWindowTest() {
        List<String> actual = allUsersPage.getFirstUserDataFromInfoWindow();
        List<String> expected = UserDAO.getWindowDataFromDatabase(
                tableParser.getEmailFromFirstTableRow(), "userInfoWindow");
        Assert.assertEquals(actual, expected, "Info isn't correct");
        logger.info("Test pass");
    }


    /**
     * This is method for checking functionality of "edit user info"
     * @param role Selected role
     */
    @Test(dataProvider = "roles")
    public void changeRoleTest(String role) {
        String expected = role;
        allUsersPage = allUsersPage.changeRoleInEditWindow(role);
        String actual = tableParser.getFieldFromFirstTableRow("role");
        Assert.assertEquals(actual, expected, "Role not changed");
        logger.info("Test pass");
    }


    /**
     *This is method for checking functionality of changing count of users on one page
     * @param count Count of users in one page
     */
    @Test(dataProvider = "count")
    public void changeCountOfUsersOnPageTest(String count) {
        int expected = Integer.parseInt(count);
        allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
        int actual = allUsersPage.getCountOfUsersInTable();
        Assert.assertEquals(actual, expected, "Count not changed");
        logger.info("Test pass");
    }


    /**
     * This is test for checking correct search by role
     * @param role Role for searching
     */
    @Test(dataProvider = "roles")
    public void searchByRoleTest(String role) {
        String expected = role;
        allUsersPage = allUsersPage.changeRole(expected);
        String actual = tableParser.getFieldFromFirstTableRow("role");
        Assert.assertEquals(actual, expected, "Searching isn't correct");
        logger.info("Test pass");
    }


    /**
     * This is test for checking functionality of complex search
     * @param role Role for searching
     * @param valueOfField Text which is typing into search field
     * @param count Count of users on one page
     */
    @Test(dataProvider = "searchParams")
    public void searchTest(String role, String valueOfField, String count) {
        allUsersPage = allUsersPage.search(Integer.parseInt(count), role, "firstName", valueOfField);
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, new String[]{valueOfField, role});
        List<String> actual = new LinkedList<>();
        if (tableParser.getFieldFromFirstTableRow("@email").contains(valueOfField)) actual.add(valueOfField);
            else actual.add("noSame");
        actual.add(tableParser.getFieldFromFirstTableRow("role"));
        Assert.assertEquals(actual, expected ,"Searching isn't correct");
        logger.info("Test pass");
    }


    /**
     * This is test for checking page navigation
     */
    @Test
    public void nextPageButtonTest() {
        AllUsersPage allUsersPage1 = allUsersPage.toNextPage();
        Assert.assertNotEquals(allUsersPage, allUsersPage1, "Next page isn't present");
        logger.info("Test pass");
    }

    /**
     * This is test for check sorting by email with selected role
     * @param role Selected role
     */
    @Test(dataProvider = "roles")
    public void sortByEmailTest(String role) {
        allUsersPage.changeRole(role);
        allUsersPage.searchButton.click();
        BrowserWrapper.sleep(2);
        allUsersPage = allUsersPage.clickSortByEmail();
        int compareResult = tableParser.getEmailFromFirstTableRow().
                compareToIgnoreCase(tableParser.getEmailFromTableRow(2));
        boolean actual = compareResult < 0;
        Assert.assertTrue(actual, "Sorting by email don't work");
        logger.info("Test pass");
    }


    @DataProvider
    public Object[][] roles() {
        return new Object[][]{
                {"MANAGER"}
        };
    }

    @DataProvider
    public Object[][] count() {
        return new Object[][]{
                {"20"}
        };
    }

    @DataProvider
    public Object[][] searchParams() {
        return new Object[][]{
                {"MANAGER", "a", "20"}
        };
    }

}
