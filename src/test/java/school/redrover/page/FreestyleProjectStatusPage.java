package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.trait.project_sidebar.*;


public class FreestyleProjectStatusPage extends BaseProjectStatusPage<FreestyleProjectStatusPage>
        implements SidebarChangesTrait, SidebarWorkspaceTrait, SidebarBuildNowTrait, SidebarMoveTrait, SidebarCredentialsTrait {

    @FindBy(xpath = "//a[contains(@href, '/configure')]")
    private WebElement configureMenuItem;

    @FindBy(xpath = "//span[text()='Delete Project']/ancestor::a")
    private WebElement deleteMenuItem;

    @FindBy(id = "description-content")
    private WebElement descriptionText;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(xpath = "//div[@id='tasks']/div[4]/span/a")
    private WebElement buildNowButton;

    @FindBy(xpath = "//div[@id='notification-bar']")
    private WebElement notificationMessage;

    @FindBy(css = ".warning")
    private WebElement warningMessage;


    public FreestyleProjectStatusPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectStatusPage getPage() {
        return this;
    }

    @Override
    public FreestyleProjectStatusPage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.visibilityOf(deleteMenuItem));

        return this;
    }

    public FreestyleProjectStatusPage clickBuildNow() {
        buildNowButton.click();

        return this;
    }

    public String getDescription() {
        return descriptionText.getText();
    }

    public String getNotificationBuildScheduled() {
        return getWait10().until(ExpectedConditions.visibilityOf(notificationMessage)).getText();
    }

    public String getDisableProjectMessage() {
        return getWait5().until(ExpectedConditions
                .visibilityOf(warningMessage)).getText().split("\\R")[0];
    }
}