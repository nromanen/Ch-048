package pages.patient;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.anonymous.DoctorSearchResultPage;
import pages.allUsers.HospitalSeekerHomePage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by ybalatc on 5/10/2017.
 */
public class TestDoctorSearchByPatient extends BaseTest {

    HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindDoctorAuthorizedUser(String searchWord, int expected) {
        BaseNavigation.login(PATIENT_LOGIN, PATIENT_PASSWORD);
        DoctorSearchResultPage doctorSearchResult = hospitalSeekerHomePage.notAuthorizedHeader.findDoctor(searchWord);
        assertEquals(doctorSearchResult.countOfDoctors(), expected);
        BaseNavigation.logout();
    }

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"hous", 1},
                {"hou", 1},
                {"qwerty", 0}
        };
    }
}
