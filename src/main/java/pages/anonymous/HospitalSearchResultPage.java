package pages.anonymous;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.NotAuthorizedHeader;

import java.util.List;

/**
 * This class describes page with hospital's search result
 *
 * @author ybalatc
 */

public class HospitalSearchResultPage implements PageInitializer {
    public NotAuthorizedHeader notAuthorizedHeader;

    public HospitalSearchResultPage() {
        notAuthorizedHeader = new NotAuthorizedHeader();
        pageInitialization();
    }

    @FindBy(css = ".card.panel.panel-default.text-xs-right")
    private List<WebElement> hospitalNameAtList;

    /**
     * Method is used for counting of searched hospitals by param
     *
     * hospitalNameAtList are names of hospitals that have been found
     * @return count of hospitals that have been found
     */

    public int countOfHospital() {
        return hospitalNameAtList.size();
    }
}
