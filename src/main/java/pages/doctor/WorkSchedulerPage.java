package pages.doctor;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.headersByRole.DoctorHeader;

import java.util.List;

public class WorkSchedulerPage extends BasePage {

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



    public WorkSchedulerPage(WebDriver driver) {
        super(driver);
        this.header = new DoctorHeader(driver);
    }
    public void dayTabButtonClick (){
        dayTabButton.click();
    }
    public void weekTabButtonClick(){
        weekTabButton.click();
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

}
