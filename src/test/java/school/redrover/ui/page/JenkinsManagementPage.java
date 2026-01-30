package school.redrover.ui.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import java.util.List;


public class JenkinsManagementPage extends BasePage<JenkinsManagementPage> {

    @FindBy(id = "settings-search-bar")
    private WebElement sendTitle;

    @FindBy(xpath = "//h1[contains(text(),'Manage Jenkins')]")
    private WebElement header;

    @FindBy(xpath = "//a[@href = 'appearance']")
    private WebElement appearanceLink;

    @FindBy(xpath = "//a[@href='securityRealm/']")
    private WebElement usersLink;

    @FindBy(xpath = "//a[@href = 'credentials']")
    private WebElement credentialsLink;

    @FindBy(xpath = "//a[@href='configure']")
    private WebElement clickConfigurationSystem;

    @FindBy(css = ".jenkins-dropdown__item:nth-of-type(1)")
    private WebElement searchFirstResultDropdown;

    @FindBy(className = "jenkins-dropdown__item")
    private List<WebElement> searchResultsDropdown;

    @FindBy(xpath = "//a[@href='computer']")
    private WebElement nodesLink;

    @FindBy(css = "#main-panel > section > h2")
    private List<WebElement> systemConfigurationList;

    public JenkinsManagementPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public JenkinsManagementPage getPage() {
        return this;
    }

    public UsersPage clickUserButton() {
        usersLink.click();

        return new UsersPage(getDriver()).waitUntilPageLoadJS();
    }

    public FolderCredentialsPage clickCredentialsLink() {
        credentialsLink.click();

        return new FolderCredentialsPage(getDriver()).waitUntilPageLoadJS();
    }

    public SystemConfigurationPage clickConfigurationSystem() {
        clickConfigurationSystem.click();

        return new SystemConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public String getHTMLAttributeThemeText() {
        try {
            getWait10().until(driver -> {
                Object value = ((JavascriptExecutor) driver)
                        .executeScript("return document.documentElement.getAttribute('data-theme');");
                return value != null && !value.toString().isBlank();
            });
            Object result = ((JavascriptExecutor) getDriver())
                    .executeScript("return document.documentElement.getAttribute('data-theme');");
            return (result != null && !result.toString().isBlank()) ? result.toString() : "unknown";
        } catch (Exception e) {
            return "unknown";
        }
    }

    public JenkinsManagementPage sendTitle(String settingTitle) {
        sendTitle.sendKeys(settingTitle);

        return this;
    }

    public SystemConfigurationPage clickSearchResult() {
        new Actions(getDriver())
                .moveToElement(getWait2().until(ExpectedConditions.elementToBeClickable(searchFirstResultDropdown)))
                .click()
                .perform();

        return new SystemConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public List<String> getSearchResults() {
        List<WebElement> searchResultElements = getWait5()
                .until(ExpectedConditions.visibilityOfAllElements(searchResultsDropdown));

        return searchResultElements
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public String getSearchResultName() {
        return getWait5().until(ExpectedConditions.visibilityOf(searchFirstResultDropdown)).getText();
    }

    public AppearancePage clickAppearanceLink() {
        appearanceLink.click();

        return new AppearancePage(getDriver()).waitUntilPageLoadJS();
    }

    public NodesPage clickNodeConfigurationSystem() {
        nodesLink.click();

        return new NodesPage(getDriver());
    }

    public List<String> checkSystemConfiguration() {
        List<WebElement> checksOf = getWait5().until(ExpectedConditions.visibilityOfAllElements(
                systemConfigurationList));

        return checksOf
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
