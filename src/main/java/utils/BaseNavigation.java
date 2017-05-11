package utils;

import pages.admin.AllUsersPage;
import pages.allUsers.HospitalSeekerHomePage;
import pages.anonymous.LoginPage;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.AuthorizedHeader;
import pages.manager.HospitalsPage;


/**
 * Created by ytomktc on 07.04.2017.
 */
public class BaseNavigation {

    public static void login(String email, String password) {
        HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();
        LoginPage loginPage = hospitalSeekerHomePage.notAuthorizedHeader.loginButton();
        loginPage.authorization(email, password);
    }

    public static HospitalSeekerHomePage logout() {
        AuthorizedHeader authorizedHeader = new AuthorizedHeader();
        authorizedHeader.profileButtonClick();
        HospitalSeekerHomePage hospitalSeekerHomePage = authorizedHeader.logoutButtonClick();
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

    public static void changeLanguage(String lang) {
        BaseHeader baseHeader = new BaseHeader();
        if (lang.equals("ukr")) {
            baseHeader.changeLanguageToUa();
        } else {
            baseHeader.changeLanguageToEn();
        }
    }
}
