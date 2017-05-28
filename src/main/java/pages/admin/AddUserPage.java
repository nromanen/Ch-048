package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.PageInitializer;
import pages.headers.headersByRole.AdminHeader;
import utils.BrowserWrapper;



public class AddUserPage implements PageInitializer {

    public static final String IDFORWAITING = "newUserSubmit";

    public AdminHeader header;

    public AddUserPage() {
        this.header = new AdminHeader();
        pageInitialization();
    }


    @FindBy(css = "form fieldset h1")
    private WebElement addUserLabel;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(className = "col-sm-2 control-label")
    private WebElement roleLabel;

    @FindBy(id = "userRoles")
    private WebElement userRolesSelect;

    @FindBy(css = "#enabledUserCheckbox.button-checkbox button.btn.btn-default")
    private WebElement enableButton;

    @FindBy(id = "newUserSubmit")
    private WebElement newUserButton;

    @FindBy(id = "cancel")
    private WebElement cancelButton;

    @FindBy(id="email-error")
    public WebElement emailErrorLabel;

    @FindBy(id="password-error")
    public WebElement passwordErrorLabel;

    @FindBy(id = "confirmPassword-error")
    public WebElement confirmPasswordErrorLabel;


    @FindBy(id = "userRoles-error")
    public WebElement userRolesErrorLabel;

    private boolean checkEmailInput(){
        return BrowserWrapper.isElementPresent(emailInput);
    }

    private boolean checkPasswordInput(){
        return BrowserWrapper.isElementPresent(passwordInput);
    }

    private boolean checkConfirmPasswordInput(){
        return BrowserWrapper.isElementPresent(confirmPasswordInput);
    }

    private boolean checkRoleLabel(){
        return BrowserWrapper.isElementPresent(roleLabel);
    }

    private boolean checkUserRolesSelect(){
        return BrowserWrapper.isElementPresent(userRolesSelect);
    }

    private boolean checkEnableButton(){
        return BrowserWrapper.isElementPresent(enableButton);
    }

    private boolean checkNewUserButton(){
        return BrowserWrapper.isElementPresent(newUserButton);
    }

    private boolean checkCancelButton(){
        return BrowserWrapper.isElementPresent(cancelButton);
    }

    private boolean checkAddUserLabel(){
        return BrowserWrapper.isElementPresent(addUserLabel);
    }

    public boolean isPageReady() throws Exception {

        /**
         * This  method  check  if all element is present on the page.
         *
         */

        BrowserWrapper.waitUntilElementVisible(newUserButton);
        StringBuilder errors = new StringBuilder();
        if(!checkEmailInput()){
            errors.append("Email Input\n");
        }
        if(!checkPasswordInput()){
            errors.append("Password Input\n");
        }
        if(!checkConfirmPasswordInput()){
            errors.append("Confirm Password Input\n");
        }
        if(!checkUserRolesSelect()){
            errors.append("User Roles Selector\n");
        }
        if(!checkEnableButton()){
            errors.append("Enable Button\n");
        }
        if(!checkNewUserButton()){
            errors.append("New User Button\n");
        }
        if(!checkCancelButton()){
            errors.append("Cancel Button\n");
        }
        if(!errors.toString().isEmpty()){
            errors.append("are not present");
            throw new NoSuchElementException(errors.toString());
        }
        return true;
    }

    public AllUsersPage submitAdding() {
        newUserButton.click();
        return new AllUsersPage();
    }

    public AllUsersPage cancelAdding() {
        cancelButton.click();
        return new AllUsersPage();
    }

    public void enterEmail(String value) {
        emailInput.clear();
        emailInput.sendKeys(value);
    }

    public void enterPassword(String value) {
        passwordInput.clear();
        passwordInput.sendKeys(value);
    }

    public void confirmPassword(String value) {
        confirmPasswordInput.clear();
        confirmPasswordInput.sendKeys(value);
    }

    public void selectRole(String value) {
        Select dropdown = new Select(userRolesSelect);
        dropdown.selectByValue(value);

    }

    public void enableButtonClick() {
        enableButton.click();
    }

    /**
     * This is a method for creating user as admin.
     * @param email
     * @param password
     * @param role
     */
    public void addNewUser(String email, String password, String role) throws InterruptedException {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        selectRole(role);
        enableButtonClick();
        submitAdding();

    }


    /**
     * This is a method for creating user as admin without role, this method was created specially for test, to check
     * wicth will be result if user wont check the role.
     * @param email
     * @param password
     */


    public void addNewUserWithotRole(String email, String password) throws InterruptedException {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        enterEmail(email);
        enterPassword(password);
        confirmPassword(password);
        enableButtonClick();
        submitAdding();

    }

    /**
     * This is a method for creating user as admin without password confirmation , this method was created specially for test, to check
     * wicth will be result if user wont confirm  the password.
     * @param email
     * @param password
     */

    public void addNewUserWithotPasswordConfirmation(String email, String password) throws InterruptedException {
        BrowserWrapper.waitUntilElementIsPresent(By.id(IDFORWAITING));
        enterEmail(email);
        enterPassword(password);
        enableButtonClick();
        submitAdding();

    }

}

