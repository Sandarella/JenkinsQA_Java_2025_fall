package school.redrover.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import school.redrover.component.common.JenkinsVersionFooterDropdown;
import school.redrover.component.common.SearchComponent;
import school.redrover.component.common.UserAccountIconDropdown;
import school.redrover.page.HomePage;
import school.redrover.page.JenkinsManagementPage;
import school.redrover.page.RestApiPage;
import school.redrover.page.UserStatusPage;

import java.util.Objects;


public abstract class BasePage<Page> extends BaseModel {

    @FindBy(className = "app-jenkins-logo")
    private WebElement logo;

    @FindBy(id = "root-action-SearchAction")
    private WebElement searchButton;

    @FindBy(id = "root-action-ManageJenkinsAction")
    private WebElement manageJenkinsGear;

    @FindBy(id = "root-action-UserAction")
    private WebElement userAccountIcon;

    @FindBy(tagName = "h1")
    private WebElement pageHeader;

    @FindBy(xpath = "//a[@href='api/']")
    private WebElement restApiLink;

    @FindBy(css = ".page-footer__links > button")
    private WebElement jenkinsVersionButton;


    public BasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public abstract Page getPage();

    public Page waitUntilPageLoadJS() {
        // delay to make sure the page starts to reload
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }

        new WebDriverWait(getDriver(), java.time.Duration.ofSeconds(10)).until(
                webDriver -> Objects.equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState"), "complete")
        );

        return getPage();
    }

    public HomePage gotoHomePage() {
        logo.click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public String getLogoText() {
        return logo.getText();
    }

    public SearchComponent clickSearchButton() {
        searchButton.click();

        return new SearchComponent(getDriver()).waitUntilComponentLoad();
    }

    public JenkinsManagementPage clickManageJenkinsGear() {
        manageJenkinsGear.click();

        return new JenkinsManagementPage(getDriver()).waitUntilPageLoadJS();
    }

    public UserStatusPage clickUserAccountIcon() {
        userAccountIcon.click();

        return new UserStatusPage(getDriver()).waitUntilPageLoadJS();
    }

    public UserAccountIconDropdown hoverUserAccountIcon() {
        PageUtils.mouseEnterJS(getDriver(), userAccountIcon);

        return new UserAccountIconDropdown(getDriver()).waitUntilComponentLoad();
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getHeaderText() {
        return pageHeader.getText();
    }

    public String getJenkinsVersionButtonText() {
        return jenkinsVersionButton.getText();
    }

    public JenkinsVersionFooterDropdown clickJenkinsVersion() {
        jenkinsVersionButton.click();

        return new JenkinsVersionFooterDropdown(getDriver()).waitUntilComponentLoad();
    }

    public WebElement getRestApiLink() {
        return restApiLink;
    }

    public RestApiPage clickRestApiLink() {
        PageUtils.clickJS(getDriver(), restApiLink);

        return new RestApiPage(getDriver()).waitUntilPageLoadJS();
    }
}
