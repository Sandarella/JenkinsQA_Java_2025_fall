package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.List;


public class UserCreatingPage extends BasePage<UserCreatingPage> {

    @FindBy(xpath = "//h1[text()='Create User']")
    private WebElement header;

    @FindBy(id = "username")
    private WebElement userNameField;

    @FindBy(name = "password1")
    private WebElement passwordField;

    @FindBy(name = "password2")
    private WebElement confirmPasswordField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "Submit")
    private WebElement createButton;

    @FindBy(xpath = "//*[@class='error jenkins-!-margin-bottom-2']")
    private List<WebElement> errorsList;

    @FindBy(className = "error")
    private WebElement userCreatingPageError;


    public UserCreatingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public UserCreatingPage getPage() {
        return this;
    }

    public UserCreatingPage sendUserName(String userName) {
        userNameField.sendKeys(userName);

        return this;
    }

    public UserCreatingPage sendPassword(String password) {
        passwordField.sendKeys(password);

        return this;
    }

    public UserCreatingPage sendConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);

        return this;
    }

    public UserCreatingPage sendEmail(String email) {
        emailField.sendKeys(email);

        return this;
    }

    public UsersPage clickCreateAndGoToUsersPage() {
        createButton.click();

        return new UsersPage(getDriver()).waitUntilPageLoadJS();
    }

    public UserCreatingPage clickCreateAndKeepUserCreatingPage() {
        createButton.click();

        return this.waitUntilPageLoadJS();
    }

    public List<String> getAllErrors() {

        return errorsList
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
