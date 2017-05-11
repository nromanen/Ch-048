package pages.admin;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.BrowserWrapper;
import utils.DriverInitializer;


public class AddUserPageTest extends BaseTest {


    public static final String NEWUSERLOGIN = "ut5estadmin182@gmail.com.ua";
    public static final String NEWUSERPASSWORD = "Q12345w";
    public static final String NEWUSERROLE = "ADMIN";

    public static final String IDFORWAITING = "searchButton";
    public static final String SUCCEFULYCREATEDUSERTEXT = " successfully registered!";


    @BeforeMethod
    private void beforeMethod() throws InterruptedException {
        BaseNavigation.loginAsAdmin(ADMIN_LOGIN, ADMIN_PASSWORD);
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DriverInitializer.instance().manage().deleteAllCookies();
        BaseNavigation.logout();
    }


    @Test(groups = {"unSuccessfully"})
    public void isElementsPresentAddUserTest() throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage = addUserPage.header.goToAddUserPage();
        try {
            BrowserWrapper.waitForPage(10L);
            addUserPage.isPageReady();
        } catch (Exception e) {

            throw new AssertionError(e.getMessage());
        }
        Assert.assertTrue(addUserPage.isPageReady());

    }


    @Test(groups = {"Successfully"})
    public void successfulAddNewUserTest() throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AllUsersPage allUsersPage = new AllUsersPage();
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser(NEWUSERLOGIN, NEWUSERPASSWORD, NEWUSERROLE);

        String actualCreatedLableText = allUsersPage.createdLabel.getText();
        String expectedCreatedLabelText = NEWUSERLOGIN + SUCCEFULYCREATEDUSERTEXT;  //" successfully registered!";

        Assert.assertEquals(actualCreatedLableText, expectedCreatedLabelText);

    }

    @Test(dataProvider = "validInformation",groups = {"Successfully"})
    public void validInfoAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AllUsersPage allUsersPage = new AllUsersPage();
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String actualCreatedLabelText = allUsersPage.createdLabel.getText();
        String expectedCreatedLabelText = addUserName + SUCCEFULYCREATEDUSERTEXT; //" successfully registered!";

        Assert.assertEquals(actualCreatedLabelText, expectedCreatedLabelText);
    }

    @Test(groups = {"unSuccessfully"})
    public void noRoleChangedAddNewUserTest() throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage.addNewUserWithotRole(NEWUSERLOGIN, NEWUSERPASSWORD);

        String actualErrorRolesLabelText = addUserPage.userRolesErrorLabel.getText();
        String expectedErrorRolesLabelText = "This field is required.";

        Assert.assertEquals(actualErrorRolesLabelText, expectedErrorRolesLabelText);

    }

    @Test(groups = {"unSuccessfully"})
    public void noPasswordConfirmationAddNewUserTest() throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage.addNewUserWithotPasswordConfirmation(NEWUSERLOGIN, NEWUSERPASSWORD);

        String actualErrorPasswordConfirmationLabelText = addUserPage.confirmPasswordErrorLabel.getText();
        String expectedErrorPasswordConfirmationText = "This field is required.";

        Assert.assertEquals(actualErrorPasswordConfirmationLabelText, expectedErrorPasswordConfirmationText);

    }

    @DataProvider(name = "validInformation")
    public static Object[][] emailDetails() {

        return new Object[][]{
                {"st1esdteh45tonemal@mail.ru", NEWUSERPASSWORD},
                {"st1ewersttwomail@com.ru", NEWUSERPASSWORD},
                {"st1estthrdfgdeemail@com.cv.ua", NEWUSERPASSWORD},
                {"sn1ewon2etest1l@is.low.pass.case", NEWUSERPASSWORD}
        };

    }


    @Test(groups = {"unSuccessfully"})
    public void noRequiredEmailTest() throws Exception {
        // BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser("", NEWUSERPASSWORD, NEWUSERROLE);

        String expectedEmailErrorLabelText = "This field is required.";
        String actualEmailErrorLabel = addUserPage.emailErrorLabel.getText();

        Assert.assertEquals(expectedEmailErrorLabelText, actualEmailErrorLabel);
        System.out.println("Email field is required but empty");
    }

    @Test(groups = {"unSuccessfully"})
    public void noRequiredPasswordTest() throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        addUserPage.addNewUser(NEWUSERLOGIN, "", NEWUSERROLE);

        String expectedPasswordErrorLabelText = "This field is required.";
        String actualPasswordErrorLabel = addUserPage.passwordErrorLabel.getText();

        Assert.assertEquals(expectedPasswordErrorLabelText, actualPasswordErrorLabel);
        System.out.println("Password field is required but empty");
    }


    @Test(dataProvider = "notValidEmails", groups = {"unSuccessfully"})
    public void notValidEmailsAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
       // BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String actualEmaiErrorLabelText = addUserPage.emailErrorLabel.getText();
        String expectedEmailErrorLabelText = "";

        Assert.assertNotEquals(actualEmaiErrorLabelText, expectedEmailErrorLabelText);


    }

    @Test(dataProvider = "notValidPasswords", groups = {"unSuccessfully"})
    public void notValidPasswordsAddNewUserTest(String addUserName, String addUserPassword) throws Exception {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        AddUserPage addUserPage = new AddUserPage();
        addUserPage = addUserPage.header.goToAddUserPage();
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        addUserPage.addNewUser(addUserName, addUserPassword, NEWUSERROLE);

        String expectedPasswordErrorLabelText = "";
        String actualPasswordErrorLabelText = addUserPage.passwordErrorLabel.getText();

        Assert.assertNotEquals(expectedPasswordErrorLabelText, actualPasswordErrorLabelText);
    }

    @DataProvider(name = "notValidEmails")
    public static Object[][] notValidEmailDetails() {
        return new Object[][]{
                {"@gmail.com", NEWUSERPASSWORD},
                {"sd@sdj", NEWUSERPASSWORD},
                {"aa@sd.", NEWUSERPASSWORD},
                {"skdjw@.c", NEWUSERPASSWORD},
                {"sd)@.com.ua", NEWUSERPASSWORD},
                {"sdd@@com.ua", NEWUSERPASSWORD}
        };
    }

    @DataProvider(name = "notValidPasswords")
    public static Object[][] notValidPasswordsDetails() {
        return new Object[][]{
                {"bnm@mail.ru", "a"},
                {"hjsn@mail.ru", "aaaaa"},
                {"sdx@gmail.com", " "},
                {"jdhjsnw@mail.ua", ""}


        };
    }
}
