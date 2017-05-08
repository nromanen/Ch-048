package stepDefinition;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.allUsers.HospitalSearchResultPage;
import pages.headers.BaseHeader;
import pages.headers.headersByRole.NotAuthorizedHeader;
import utils.DriverInitializer;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by ybalatc on 5/8/2017.
 */
public class HospitalSearchSteps {
    private static final String BASE_URL = "https://localhost:8443/HospitalSeeker/";
    HospitalSearchResultPage hospitalSearchResult = new HospitalSearchResultPage();

    @When("^I try to search hospital by (.*)$")
    public void iTryToSearchHospitalBySearch_word(String search_word) throws Throwable {
        hospitalSearchResult = hospitalSearchResult.notAuthorizedHeader.findHospital(search_word);
        Thread.sleep(1000);
    }

    @Then("^I should see (\\d+) hospitals which name, description or address consist search word$")
    public void iShouldSeeExpected_numberHospitalsWhichNameDescriptionOrAddressConsistSearchWord(int expectedNumber) throws Throwable {
        assertEquals(hospitalSearchResult.countOfHospital(), expectedNumber);
    }
}
