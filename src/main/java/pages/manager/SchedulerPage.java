package pages.manager;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.ManagerHeader;
import utils.BrowserWrapper;

import java.util.ArrayList;
import java.util.List;


public class SchedulerPage implements PageInitializer {

    private static final int DEFAULT_NUMBER_OF_DAYS = 5;
    private static final String DEFAULT_BEGINNING_HOUR = "0 00";
    private static final String DEFAULT_ENDING_HOUR = "23 00";

    public ManagerHeader managerHeader;
    @FindBy(css = "div.pull-left")
    private WebElement doctorNameLabel;

    @FindBy(css = "label[for=\"workWeekSize\"]")
    private WebElement workWeekSizeLable;

    @FindBy(id="workWeekSize")
    private WebElement workWeekSizeSelector;

    @FindBy(css = "label[for=\"workdayBeginAt\"]")
    private WebElement workDayHoursLabel;

    @FindBy(id="workDayBeginAt")
    private WebElement workDayBeginAtSelector;

    @FindBy(id="workDayEndAt")
    private WebElement workDayEndAtSelector;

    @FindBy(css = "label[for=\"appointmentTime\"]")
    private WebElement apointmentSizeLabel;

    @FindBy(id="appointmentTime")
    private WebElement apointmentSizeSelector;

    @FindBy(id="saveData")
    private WebElement saveButton;

    @FindBy(name="day_tab")
    private WebElement dayTabButton;

    @FindBy(name="week_tab")
    private WebElement weekTabButton;

    @FindBy(name = "month_tab")
    private WebElement monthTabButton;

    @FindBy(id="dhx_minical_icon")
    private WebElement miniCalendarButton;

    @FindBy(css="div.hx_cal_date")
    private WebElement dateLabel;

    @FindBy(css = "div.dhx_cal_today_button")
    private WebElement todayButton;

    @FindBy(css = "div.dhx_cal_prev_button")
    private WebElement previousMonthButton;

    @FindBy(css = "div.dhx_cal_next_button")
    private WebElement nextButton;

    @FindAll({@FindBy(css = "div.dhx_scale_holder"),
              @FindBy(css = "div.dhx_scale_holder_now")})
    private List<WebElement> tableColumns;


    @FindBy(css = "div.dhx_scale_holder_now")
    private WebElement nowColumn;

    @FindBy(css = "div.dhx_scale_holder")
    private WebElement tableColumn;

    @FindBy(css = "div.dhx_scale_ignore")
    private List<WebElement> tableIgnoredColumns;

    @FindBy(className = "div.dhx_scale_hour")
    private List<WebElement> tableRows;

    @FindBy(css = "div.dhx_body")
    private List<WebElement> eventBodys;

    @FindBy(css = "div.dhx_cal_event")
    private List<WebElement> events;

    @FindBy(css = "textarea.dhx_cal_editor")
    private WebElement eventInput;

    @FindBy(css = "div.dhx_menu_icon.icon_save")
    private WebElement saveEvent;

    @FindBy(css = "div.icon_cancel")
    private WebElement cancelEvent;

    @FindBy(css = "div.icon_details")
    private WebElement detaisEvent;

    @FindBy(css = "div.icon_edit")
    private WebElement editEvent;

    @FindBy(css = "div.dhx_title")
    private WebElement eventTitle;

    @FindBy(css = "div.icon_delete")
    private WebElement eventDelete;

    @FindBy(css = "div.dhx_cal_ltext")
    private WebElement detailedEditorField;

    @FindBy(css = "div.dhx_btn_set.dhx_left_btn_set.dhx_cancel_btn_set")
    private WebElement cancelDetailedChanges;

    @FindBy(css = "div.dhx_btn_set.dhx_right_btn_set.dhx_delete_btn_set")
    private WebElement deleteDetailedChanges;

    @FindBy(css = "div.dhx_save_btn_set")
    private WebElement saveDetailedChanges;

    @FindBy(css = "div.dhx_scale_hour:first-child")
    private WebElement beginningHour;

    @FindBy(css = "div.dhx_scale_hour:last-child")
    private WebElement endHour;

    @FindBy(css = "div.dhtmlx_ok_button")
    private WebElement eventDeleteConfirmation;

    @FindBy(css = "div.dhx_event_resize")
    public WebElement resizeButton;


    @FindBy(css = "div.dhx_cal_event_line_start")
    private List<WebElement> monthElements;

    @FindBy(css = "div.dhx_month_body")
    private WebElement monthElement;

    @FindBy(css = "div.dhx_mini_calendar")
    private WebElement calendarBody;

    @FindBy(css = "div.gray_section")
    private List<WebElement> notActiveRows;



   public void nextButtonClick(){
        while(notActiveRows.size()>0){
            BrowserWrapper.waitUntilElementVisible(nextButton);
            nextButton.click();
        }
   }

    public void createAppointment(String text)  {
       nextButtonClick();
       inputEvent(text);
       saveEventClick();
       saveButtonClick();
    }
    public void createAppointmentWithoutSave(String text)  {
        nextButtonClick();
        inputEvent(text);
        saveEventClick();

    }

    public boolean checkMiniCalendarVisibility(){
       return BrowserWrapper.isElementPresent(calendarBody);
    }

    public void inputEvent(String text) {
        BrowserWrapper.doubleClickJs(tableColumn);
        BrowserWrapper.waitUntilElementVisible(eventInput);
        eventInput.sendKeys(text);
    }


    public void saveEventClick(){
        BrowserWrapper.waitUntilElementVisible(saveButton);
        saveEvent.click();
    }

    public void callEventContext(){
        events.get(0).findElement(By.cssSelector("div.dhx_title")).click();
    }
    public void deleteEventButtonClick(){
        callEventContext();
        eventDelete.click();
        BrowserWrapper.waitUntilElementClickable(eventDeleteConfirmation);
        eventDeleteConfirmation.click();
        saveButtonClick();
    }

    public void editEventText(String text){
        callEventContext();
        editEvent.click();
        eventInput.sendKeys(text);
        saveEvent.click();
        saveButtonClick();
    }


    public void createEventCalendar(String text){
        nextButtonClick();
        BrowserWrapper.waitUntilElementVisible(monthElement);
        BrowserWrapper.doubleClickJs(monthElement);
        BrowserWrapper.waitUntilElementVisible(detailedEditorField);
        detailedEditorField.sendKeys(text);
        saveDetailedChanges.click();
        saveButtonClick();
    }



    public int getDaysNumber(){
        return tableColumns.size() - tableIgnoredColumns.size() - 1;
    }

    public String getBeginningHour(){
        return beginningHour.getText();
    }

    public String getEndingHour(){
        return endHour.getText();
    }


    public boolean checkDefaultConditionScheduler(){
        boolean result = false;
        try {
            BrowserWrapper.waitUntilElementNotStale(beginningHour);
            boolean r1 = beginningHour.getText().equals(DEFAULT_BEGINNING_HOUR);
            BrowserWrapper.waitUntilElementNotStale(endHour);
            boolean r2 = endHour.getText().equals(DEFAULT_ENDING_HOUR);
            result = r1 && r2 && getDaysNumber() == DEFAULT_NUMBER_OF_DAYS;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void setDayDuration(String begin, String end){
        BrowserWrapper.waitUntilElementVisible(workDayBeginAtSelector);
        BrowserWrapper.selectDropdown(workDayBeginAtSelector, begin);
        BrowserWrapper.waitUntilElementClickable(workDayEndAtSelector);
        BrowserWrapper.selectDropdown(workDayEndAtSelector, end);
    }

    private boolean checkDayButton(){
        return (BrowserWrapper.isElementPresent(dayTabButton));
    }

    private boolean checkWeekButton(){
        return BrowserWrapper.isElementPresent(weekTabButton);

    }

    private boolean checkMonthButton(){
        return BrowserWrapper.isElementPresent(monthTabButton);
    }

    private boolean checkMiniCalendarButton(){
        return BrowserWrapper.isElementPresent(miniCalendarButton);
    }


    private boolean checkTodayButton(){
        return BrowserWrapper.isElementPresent(todayButton);
    }

    private boolean checkPreviousButton(){
        return BrowserWrapper.isElementPresent(previousMonthButton);
    }

    private boolean checkNextButton(){
        return BrowserWrapper.isElementPresent(nextButton);
    }

    private boolean checkWeekSizeSelectorSelector(){
        return BrowserWrapper.isElementPresent(workWeekSizeSelector);
    }

    private boolean checkBeginAtHourSelector(){
        BrowserWrapper.waitUntilElementNotStale(beginningHour);
        return BrowserWrapper.isElementPresent(beginningHour);
    }

    private boolean checkEndAtHourSelector() {
        BrowserWrapper.waitUntilElementNotStale(endHour);
        return BrowserWrapper.isElementPresent(endHour);
    }

    private boolean checkAppointmentSizeSelector(){
        return BrowserWrapper.isElementPresent(apointmentSizeSelector);
    }

    private boolean checkDoctorLabel(){return BrowserWrapper.isElementPresent(doctorNameLabel); };

    public boolean isPageReady()  {
        BrowserWrapper.waitUntilElementVisible(doctorNameLabel);
        StringBuilder errors = new StringBuilder();
        if(!checkAppointmentSizeSelector()){
            errors.append("appointment size selector\n");
        }
        if(!checkBeginAtHourSelector()){
            errors.append("begin at hour selector\n");
        }
        if(!checkDayButton()){
            errors.append("day button\n");
        }
        if(!checkWeekSizeSelectorSelector()){
            errors.append("week size selector\n");
        }
        if(!checkEndAtHourSelector()){
            errors.append("end at hour selector\n");
        }
        if(!checkMiniCalendarButton()){
            errors.append("mini calendar button\n");
        }
        if(!checkMonthButton()){
            errors.append("month button\n");
        }
        if(!checkNextButton()){
            errors.append("next button\n");
        }
        if(!checkTodayButton()){
            errors.append("today button\n");
        }
        if(!checkWeekButton()){
            errors.append("week button\n");
        }
        if(!checkPreviousButton()) {
            errors.append("previous button\n");
        }
        if(!checkDoctorLabel()){
            errors.append("doctor label\n");
        }
        if(!errors.toString().isEmpty()){
            errors.append("are not present");
            throw new NoSuchElementException(errors.toString());
        }
        return true;
    }

    public boolean isEventOnCalendarTab(String event){
        return getEventsCalendar().stream().anyMatch(e -> e.contains(event));
    }

    public List<String> getEventsCalendar(){
        List<String> list = new ArrayList<>();

        if(monthElements.size() == 0){
            return list;
        }
        for(WebElement element : monthElements){
            list.add(element.getText());
        }

        return list;
    }

    public boolean isEventsPresentOnCalendar(){
        return getEventsCalendar().size()>0;
    }
    public boolean isEventsPresent(){
        return getEvents().size() > 0;
    }


    public boolean isEventOnWeekTab(String event){
        return getEvents().stream().anyMatch(e -> e.contains(event));
    }

    public List<String> getEvents(){
        List<String> list = new ArrayList<>();

        if(eventBodys.size() == 0){
            return list;
        }
        for(WebElement element : eventBodys){
            list.add(element.getText());
        }

        return list;
    }

    public void workWeekSizeSelector(String value){
        BrowserWrapper.waitUntilElementClickable(workWeekSizeSelector);
        BrowserWrapper.selectDropdown(workWeekSizeSelector, value);
    }


    public void cancelButtonClick(){
        BrowserWrapper.waitUntilElementVisible(cancelEvent);
        cancelEvent.click();
        saveButtonClick();
    }

    public void saveButtonClick(){
        BrowserWrapper.waitUntilElementVisible(saveButton);
        saveButton.click();
    }

    public void dayTabButtonClick(){
        BrowserWrapper.waitUntilElementClickable(dayTabButton);
        dayTabButton.click();
    }

    public void weekTabButtonClick(){
        weekTabButton.click();
    }

    public void monthTabButtonClick(){
        BrowserWrapper.waitUntilElementVisible(monthTabButton);
        monthTabButton.click();
    }

    public void miniCalendarButtonClick(){
        BrowserWrapper.waitUntilElementVisible(miniCalendarButton);
        miniCalendarButton.click();
    }

    public void todayButtonClick(){
        todayButton.click();
    }

    public void previousMonthButtonClick(){
        previousMonthButton.click();
    }

    public boolean checkTodayPresence(){
       return BrowserWrapper.isElementPresent(nowColumn);
    }



    public SchedulerPage(){
        managerHeader = new ManagerHeader();
        pageInitialization();
    }
}