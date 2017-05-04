package pages.admin;

import org.apache.xerces.xs.StringList;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.*;
import utils.databaseutil.UserDAO;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Evgen on 10.04.2017.
 */
public class AllUsersPageTest extends BaseTest {


    private AllUsersPage allUsersPage;


    @BeforeMethod
    public void before() throws Exception {
        /*FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        dataSet = builder.build(new java.io.File("dependents.xml"));

        //dataSet = new FlatXmlDataSet(Thread.currentThread().getContextClassLoader().getResourceAsStream("full.xml"));
        databaseTester = new JdbcDatabaseTester(driverClass, databaseUrl, username, password);
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.setTearDownOperation(DatabaseOperation.NONE);
        databaseTester.onSetup();
        */

        DriverInitializer.getToUrl(BASE_URL);
        allUsersPage = BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
    }

    @AfterMethod
    public void after() {
        try {
            BaseNavigation.logout();
            DriverInitializer.close();
            //databaseTester.onTearDown();
        } catch (Exception e) {
            DriverInitializer.close();
        }
    }

   @Test
   public void localizationTest() throws IOException {
       String language = System.getProperty("LANGUAGE");

       Properties properties = new Properties();
       InputStream inputStream;

       if (language.equals("ua")) inputStream = new FileInputStream("src/test/resources/localization/ua.properties");
        else if (language.equals("en")) inputStream = new FileInputStream("src/test/resources/localization/en.properties");
            else inputStream = null;

       properties.load(inputStream);

       Assert.assertEquals(allUsersPage.header.homeButton.getText(), properties.getProperty("header.menu.home"));


        List<String> actual = new ArrayList<>();
        actual.add(allUsersPage.header.homeButton.getText());
        actual.add(allUsersPage.header.actions.getText());
        allUsersPage.usersPerPageLabel.getText();

        List<String> expected = new ArrayList<>();
        expected.add(properties.getProperty("header.menu.home"));
        expected.add(properties.getProperty("admin.hospital.list.table.actions"));
        expected.add(properties.getProperty("admin.dashboard.users.show.users"));

        Assert.assertEquals(actual, expected);
   }


    @Test
    public void enableUsersViewTest() {
        allUsersPage = allUsersPage.showEnableUsers();
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
        Assert.assertEquals(actual, true);
    }


    @Test
    public void disableUsersViewTest() {
        allUsersPage = allUsersPage.showDisableUsers();
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        boolean actual = UserDAO.getStatusByEmail(new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "@email"));
        Assert.assertEquals(actual, false);
    }


    @Test
    public void viewWindowTest() {
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        List<String> actual = allUsersPage.getUserDataFromInfoWindow(rowNumber);
        List<String> allInfo = UserDAO.getUserFromDatabaseByEmail(actual.get(1));
        List<String> expected = new LinkedList<>();
        Collections.addAll(expected, new String[]{allInfo.get(0),allInfo.get(1), "true"});
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "roles")
    public void changeRoleTest(String role) {
        String expected = role;
        int rowNumber = 1;
        allUsersPage = allUsersPage.changeRoleInEditWindow(rowNumber, role);
        String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "count")
    public void changeCountOfUsersOnPageTest(String count) {
        int expected = Integer.parseInt(count);
        allUsersPage = allUsersPage.changeCountOfUsersOnPage(expected);
        int actual = allUsersPage.getCountOfUsersInTable();
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "roles")
    public void searchByRoleTest(String role) {
        String expected = role;
        allUsersPage = allUsersPage.changeRole(expected);
        BrowserWrapper.sleep(2);
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        String actual = new TableParser(allUsersPage.table).getFieldFromTableRow(rowNumber, "role");
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "searchParams")
    public void searchTest(String role, String valueOfField, String count) {
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
        AllUsersPage allUsersPage1 = allUsersPage.toNextPage();
        BrowserWrapper.waitForPage();
        Assert.assertNotEquals(allUsersPage, allUsersPage1);
    }


    @Test
    public void deleteUsersTest() {
        int rowNumber = randomNumber(allUsersPage.getCountOfUsersInTable());
        String actual = allUsersPage.getCurrentUrl();
        allUsersPage = allUsersPage.deleteUser(rowNumber);
        String expected = allUsersPage.getCurrentUrl();
        Assert.assertEquals(actual, expected);
    }


    @Test(dataProvider = "roles")
    public void sortByEmailTest(String role) {
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
                {"MANAGER"}//,
                //{"PATIENT"}
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
