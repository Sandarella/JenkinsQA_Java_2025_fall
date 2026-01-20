package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class NewCredentialsPage extends BasePage<NewCredentialsPage> {

    @FindBy(name = "Submit")
    private WebElement buttonCreate;

    public NewCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewCredentialsPage getPage() {
        return this;
    }

    public NewCredentialsPage enterUsername(String username) {
        getDriver().findElement(By.name("_.username")).sendKeys(username);

        return this;
    }

    public NewCredentialsPage enterPassword(String password) {
        getDriver().findElement(By.name("_.password")).sendKeys(password);

        return this;
    }

    public NewCredentialsPage enterDescription(String description) {
        getDriver().findElement(By.name("_.description")).sendKeys(description);

        return this;
    }

    public GlobalCredentialsPage clickCreateButton() {
        buttonCreate.click();

        return new GlobalCredentialsPage(getDriver()).waitUntilPageLoadJS();
    }
}
