package school.redrover.ui.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

import java.util.List;
import java.util.function.Consumer;


public class GlobalCredentialsPage extends BasePage<GlobalCredentialsPage> {

    @FindBy(xpath = "//a[@href='/job/MyFolder/credentials/']")
    private WebElement credentialsMenuItem;

    @FindBy(xpath = "//a[@href='newCredentials']")
    private WebElement addCredentials;

    public GlobalCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public GlobalCredentialsPage getPage() {
        return this;
    }

    public FolderCredentialsPage clickCredentials() {
        credentialsMenuItem.click();

        return new FolderCredentialsPage(getDriver()).waitUntilPageLoadJS();
    }

    public NewCredentialsPage clickAddCredentialsButton() {
        addCredentials.click();

        return new NewCredentialsPage(getDriver()).waitUntilPageLoadJS();
    }

    public List<WebElement> getGlobalCredentialsList() {
        return getDriver().findElements(By.xpath("//table[contains(@class,'jenkins-table')]//tbody//tr/td[3]"));
    }

    public GlobalCredentialsPage getGlobalCredentialsList(Consumer<List<WebElement>> listConsumer) {
        listConsumer.accept(getGlobalCredentialsList());

        return this;
    }
}
