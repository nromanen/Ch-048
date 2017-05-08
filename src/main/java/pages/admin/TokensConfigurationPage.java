package pages.admin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageInitializer;
import pages.headers.headersByRole.AdminHeader;

/**
 * Created by gregtar on 21.04.17.
 */
public class TokensConfigurationPage implements PageInitializer {

    public AdminHeader header;

    public TokensConfigurationPage() {
        this.header = new AdminHeader();
        pageInitialization();
    }



    @FindBy(xpath = "//*[@id=\"configTokenForm\"]/div[1]/label")
    private WebElement resetPasswordTokenLabel;

    @FindBy(id = "configs0.value")
    private WebElement resetPasswordTokenInput;

    @FindBy(xpath = "//*[@id=\"configTokenForm\"]/div[2]/label")
    private WebElement verificationTokenLabel;

    @FindBy(id = "configs1.value")
    private WebElement verificationTokenInput;

    @FindBy(xpath = "//*[@id=\"configTokenForm\"]/div[3]/label")
    private WebElement rememberMeTokenLabel;

    @FindBy(id = "configs2.value")
    private WebElement rememberMeTokenInput;

    @FindBy(xpath = "//*[@id=\"configTokenForm\"]/div[4]/label")
    private WebElement fileMaxSizeLabel;

    @FindBy(id = "configs3.value")
    private WebElement fileMaxSizeInput;

    @FindBy(css = "input[value=\"Save\"]")
    private WebElement saveButton;

    @FindBy(id = "button-cancelToken")
    private WebElement cancelButton;


}
