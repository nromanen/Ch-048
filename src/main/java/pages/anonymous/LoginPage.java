package pages.anonymous;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.allUsers.BasePage;
import pages.headers.BaseHeader;
import utils.BrowserWrapper;


/**
 * Created by ytomktc on 07.04.2017.
 */
public class LoginPage extends BasePage {
    public BaseHeader baseHeader;
    @FindBy(xpath = "//*[@id=\"loginForm\"]/fieldset/h2")
    private WebElement loginText;

    @FindBy(id="email")
    public WebElement emailTextField;

    @FindBy(id="password")
    private WebElement passwordTextField;

    @FindBy(xpath = "//*[@id=\"loginForm\"]/fieldset/span/button")
    private WebElement rememberMeButton;

    @FindBy(id="forgotButton")
    private WebElement forgotPasswordButton;

    @FindBy(id="loginSubmit")
    private WebElement loginSubmitButton;

    @FindBy(id="registerRedirect")
    private WebElement registerButton;

    public void authorization(String email, String password){
        BrowserWrapper.waitUntilElementClickable(emailTextField);
        emailTextField.clear();
        emailTextField.sendKeys(email);
        BrowserWrapper.waitUntilElementClickable(passwordTextField);
        passwordTextField.clear();
        passwordTextField.sendKeys(password);
        BrowserWrapper.waitUntilElementClickable(loginSubmitButton);
        loginSubmitButton.click();
    }

    public void rememberMeButtonClick(){
        rememberMeButton.click();
    }

    public void forgotPasswordButton(){
        forgotPasswordButton.click();
    }

    public void loginSubmitButton(){
        loginSubmitButton.click();
    }


    public LoginPage(WebDriver driver) {
        super(driver);
        baseHeader = new BaseHeader(driver);
    }
}
