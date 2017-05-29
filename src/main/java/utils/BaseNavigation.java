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
        LoginPage loginPage = hospitalSeekerHomePage.header.loginButton();
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
        return hospitalSeekerHomePage;
    }

    /**
     *  This is a method which provide login function for the role of admin
     * @param email It's a string representation of email
     * @param password It's a string representation of password
     * @return Its a main page of admin
     */
    public static AllUsersPage loginAsAdmin(String email, String password) {
        login(email, password);
        return new AllUsersPage();
    }
    /**
     *  This is a method which provide login function for the role of manager
     * @param email It's a string representation of email
     * @param password It's a string representation of password
     * @return Its a main page of manager
     */
    public static HospitalsPage loginAsManager(String email, String password) {
        login(email, password);
        return new HospitalsPage();
    }
    /**
     *  This is a method which provide login function for the role of doctor
     * @param email It's a string representation of email
     * @param password It's a string representation of password
     * @return Its a main page of doctor
     */
    public static HospitalSeekerHomePage loginAsDoctor(String email, String password) {
        login(email, password);
        return new HospitalSeekerHomePage();
    }
    /**
     *  This is a method which provide login function for the role of patient
     * @param email It's a string representation of email
     * @param password It's a string representation of password
     * @return Its a main page of patient
     */
    public static HospitalSeekerHomePage loginAsPatient(String email, String password) {
        login(email, password);
        return new HospitalSeekerHomePage();
    }

    /**
     * This is a method which is used for changing language of interface of site
     *
     * @param lang it's a string representation of language it which test will occure. Can be "eng" and "ukr"
     */
    public static void changeLanguage(String lang) {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        if (lang.equals("ukr")) {
            hospitalSeekerHomePage.changeLanguageToUa();
        } else {
            hospitalSeekerHomePage.changeLanguageToEn();
        }
    }
}
