package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class UserAccountPage extends BasePage<UserAccountPage> {

    @FindBy(xpath = "//h1[text()='Account']")
    private WebElement header;

    @FindBy(name = "_.fullName")
    private WebElement fullNameField;

    @FindBy(xpath = "//input[@name='email.address']")
    private WebElement emailField;
    
    @FindBy(xpath = "//button[@name='Apply']")
    private WebElement applyButton;

    @FindBy(name = "Submit")
    private WebElement saveButton;


    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public UserAccountPage getPage() {
        return this;
    }

    public UserAccountPage sendFullName(String fullName) {
        fullNameField.clear();
        fullNameField.sendKeys(fullName);

        return this;
    }

    public UserStatusPage clickSave() {
        saveButton.click();

        return new UserStatusPage(getDriver()).waitUntilPageLoadJS();
    }

    public UserAccountPage sendEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email); 

        return this;
    }

    public UserAccountPage editEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
        applyButton.click();

        return this;
    }

    public String getEmailText() {
        return emailField.getAttribute("value");
    }
}
