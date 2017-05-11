package pages.patient;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.anonymous.HospitalSearchResultPage;
import pages.allUsers.HospitalSeekerHomePage;
import utils.BaseNavigation;
import utils.BaseTest;
import utils.DriverInitializer;

import static org.testng.Assert.assertEquals;

/**
 * Created by ybalatc on 5/10/2017.
 */
public class TestHospitalSearchByPatient extends BaseTest {

    HospitalSeekerHomePage hospitalSeekerHomePage = new HospitalSeekerHomePage();

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        DriverInitializer.deleteAllCookies();
    }

    @Test(dataProvider = "SearchProvider")
    public void testFindHospitalAuthorizedUser(String searchWord, int expected) throws Exception {
        BaseNavigation.login(PATIENT_LOGIN, PATIENT_PASSWORD);
        HospitalSearchResultPage hospitalSearchResult = hospitalSeekerHomePage.notAuthorizedHeader.findHospital(searchWord);
        assertEquals(hospitalSearchResult.countOfHospital(), expected,
                "Actual count of finded hospital isn't as expected");
        BaseNavigation.logout();
    }

    @DataProvider(name = "SearchProvider")
    public static Object[][] parametrizedData() {
        return new Object[][]{
                {"polik", 1},
                {"hosp", 3},
                {"qwerty", 0}
        };
    }
}
