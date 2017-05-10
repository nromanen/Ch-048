package pages.doctor;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.DoctorHeader;
import utils.BrowserWrapper;

import java.util.List;

public class WorkSchedulerPage implements PageInitializer {

    public DoctorHeader header;

    @FindBy(name="day_tab")
    private WebElement dayTabButton;

    @FindBy(name="week_tab")
    private WebElement weekTabButton;

    @FindBy(name = "month_tab")
    private WebElement monthTabButton;

    @FindBy(id="dhx_minical_icon")
    private WebElement miniCalendarButton;

    @FindBy(className="div.hx_cal_date")
    private WebElement dateLabel;

    @FindBy(className = "div.dhx_cal_today_button")
    private WebElement todayButton;

    @FindBy(className = "div.dhx_cal_prev_button")
    private WebElement previousDateButton;

    @FindBy(css = "div.dhx_cal_next_button")
    private WebElement nextDateButton;

    @FindAll(@FindBy(css = "div.dhx_scale_holder"))
    private List<WebElement> tabelColomns;

    @FindAll(@FindBy(className = "dhx_scale_hour"))
    private List<WebElement> tabelRows;

    @FindAll({@FindBy(css = "div.dhx_scale_holder"),
            @FindBy(css = "div.dhx_scale_holder_now")})
    private List<WebElement> tableColumns;

    @FindAll(@FindBy(css = "div.dhx_scale_ignore"))
    private List<WebElement> tableIgnoredColumns;


    public WorkSchedulerPage( ) {
        this.header = new DoctorHeader();
        pageInitialization();
    }
    public void dayTabButtonClick (){
        dayTabButton.click();
    }
    public WorkSchedulerPage weekTabButtonClick(){
        BrowserWrapper.waitUntilElementClickable(weekTabButton);
        weekTabButton.click();
        return new WorkSchedulerPage();
    }
    public void monthTabButtonClick(){
        monthTabButton.click();
    }
    public void miniCalendarButtonClick(){
        miniCalendarButton.click();
    }
    public void previousDateButtonClick(){
        previousDateButton.click();
    }
    public void nextDateButtonClick(){
        nextDateButton.click();
    }
    public void todayButtonClick(){
        todayButton.click();
    }
    public int getDaysNumber(){
        return tableColumns.size() - tableIgnoredColumns.size() - 1;
    }

}
