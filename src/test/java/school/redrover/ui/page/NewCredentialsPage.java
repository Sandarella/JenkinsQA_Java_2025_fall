package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;


public class NewCredentialsPage extends BasePage<NewCredentialsPage> {

    @FindBy(name = "Submit")
    private WebElement buttonCreate;

    @FindBy(name = "_.username")
    private WebElement usernameField;

    @FindBy(name = "_.password")
    private WebElement passwordField;

    @FindBy(name = "_.description")
    private WebElement descriptionTextArea;

    public NewCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewCredentialsPage getPage() {
        return this;
    }

    public NewCredentialsPage enterUsername(String username) {
        usernameField.sendKeys(username);

        return this;
    }

    public NewCredentialsPage enterPassword(String password) {
        passwordField.sendKeys(password);

        return this;
    }

    public NewCredentialsPage enterDescription(String description) {
        descriptionTextArea.sendKeys(description);

        return this;
    }

    public GlobalCredentialsPage clickCreateButton() {
        buttonCreate.click();

        return new GlobalCredentialsPage(getDriver()).waitUntilPageLoadJS();
    }
}
