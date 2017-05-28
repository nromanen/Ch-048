package utils;

import pages.admin.AllUsersPage;
import pages.allUsers.HospitalSeekerHomePage;
import pages.anonymous.LoginPage;
import pages.headers.headersByRole.AuthorizedHeader;
import pages.manager.HospitalsPage;


/**
 * This is a class which is used for navigation through hospital seeker site. It provides ability of login as
 * all of roles, logout and ability of switch language before executing of test
 *
 * @author Yuri Tomo, Evgen Korcheviy
 *
 * @version 1.0
 */
public class BaseNavigation {

    /**
     * This is a method which is used for login to the site. It takes user email and password
     *
     * @param email It's a string representation of email
     * @param password It's a string representation of password
     */
    public static void login(String email, String password) {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        LoginPage loginPage = hospitalSeekerHomePage.notAuthorizedHeader.loginButton();
        loginPage.authorization(email, password);
    }

    /**
     * This is a method which is used for logout from page when user of any role is authorized.
     *
     * @return It is the main page of the hospital seeker site
     */
    public static HospitalSeekerHomePage logout() {
        AuthorizedHeader authorizedHeader = new AuthorizedHeader();
        authorizedHeader.profileButtonClick();
        HospitalSeekerHomePage hospitalSeekerHomePage = authorizedHeader.logoutButtonClick();
        if(BrowserWrapper.isAlertPresent()) {
            BrowserWrapper.confirmAlert();
        }
        return hospitalSeekerHomePage;
    }

    public static AllUsersPage loginAsAdmin(String email, String password) {
        login(email, password);
        return new AllUsersPage();
    }

    public static HospitalsPage loginAsManager(String email, String password) {
        login(email, password);
        return new HospitalsPage();
    }

    public static HospitalSeekerHomePage loginAsDoctor(String email, String password) {
        login(email, password);
        return new HospitalSeekerHomePage();
    }

    public static HospitalSeekerHomePage loginAsPatient(String email, String password) {
        login(email, password);
        return new HospitalSeekerHomePage();
    }

    /*public static void changeLanguage(String lang) {
        NotAuthorizedHeader notAuthorizedHeader = new NotAuthorizedHeader();
        if (lang.equals("ukr")) {
            notAuthorizedHeader.changeLanguageToUa();
        } else {
            notAuthorizedHeader.changeLanguageToEn();
        }
    }*/
}
