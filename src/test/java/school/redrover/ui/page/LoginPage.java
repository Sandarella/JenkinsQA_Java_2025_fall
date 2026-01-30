package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;


public class LoginPage extends BasePage<LoginPage> {

    @FindBy(id = "j_username")
    private WebElement userNameField;

    @FindBy(id = "j_password")
    private WebElement userPasswordField;

    @FindBy(xpath = "//button")
    private WebElement signInButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage getPage() {
        return this;
    }

    public String getTitle() {
        return getHeaderText();
    }

    public String getUrlProfile() {
        getDriver().get(getDriver().getCurrentUrl() + "user/admin/");
        return getTitle();
    }

    public HomePage signIn(String userName, String userPassword) {
        userNameField.sendKeys(userName);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

}
