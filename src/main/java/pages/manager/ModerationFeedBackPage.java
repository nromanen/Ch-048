package pages.manager;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.PageInitializer;
import pages.headers.headersByRole.ManagerHeader;
import utils.BrowserWrapper;

import java.util.List;


/**
 * Created by radga on 06.04.2017.
 */
public class ModerationFeedBackPage implements PageInitializer {
    public ManagerHeader managerHeader;

    @FindBy(css = "input[value=\"remove\"]")
    private WebElement removeAfterDecideButton;

    @FindBy(css = "input[value=\"NEW\"]")
    private WebElement newButton;

    @FindBys(@FindBy(css = "input[value=\"OK\"]"))
    private List<WebElement> okButtons;

    @FindBy(css = "input[value=\"BAD\"]")
    private WebElement badButton;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

   @FindBy(css = "label[value=\"OK\"]")
   private  WebElement approveButton;

   @FindBy(xpath = "//*[@id=\"20\"]")
   public WebElement patientOneFeedbackBody;





    @FindBy(xpath = "/html/body/section/a")
    private WebElement backToTopButton;



    public ModerationFeedBackPage  confirmFeedback(){
        BrowserWrapper.sleep(2);
        okButtons.get(1).click();

        BrowserWrapper.refreshPage();
        return new ModerationFeedBackPage();
    }

    public ModerationFeedBackPage(){
        managerHeader = new ManagerHeader();
        pageInitialization();
    }
}
