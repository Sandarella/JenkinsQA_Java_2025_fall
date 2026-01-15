package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public abstract class BaseProjectConfigurationPage<
        ConfigurationPage extends BaseProjectConfigurationPage<?,?>,
        ProjectStatusPage extends BaseProjectStatusPage<ProjectStatusPage, ?>>
        extends BasePage<ConfigurationPage> {

    @FindBy(xpath = "//button[contains(., 'General')]")
    private WebElement generalMenuItem;

    @FindBy(xpath ="//*[contains(@name,'description')]")
    private WebElement description;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(name = "Apply")
    private WebElement applyButton;

    @FindBy(className = "textarea-show-preview")
    private WebElement previewButton;

    @FindBy(xpath = "//span[text() = 'Saved']")
    private WebElement savedMessage;

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(className = "textarea-preview")
    private WebElement  previewDescriptionTextArea;

    public BaseProjectConfigurationPage(WebDriver driver) {
        super(driver);
    }

    public ConfigurationPage clickGeneralInSideMenu() {
        generalMenuItem.click();

        return this.waitUntilPageLoadJS();
    }

    protected abstract ProjectStatusPage getProjectStatusPage();

    public ProjectStatusPage clickSave(){
        submitButton.click();

        return getProjectStatusPage().waitUntilPageLoadJS();
    }

    public String getSavedMessage() {
        return getWait5().until(ExpectedConditions.visibilityOf(savedMessage)).getText();
    }

    public ConfigurationPage clickApply() {
        applyButton.click();

        return this.waitUntilPageLoadJS();
    }

    public boolean isSaveButtonDisplayed() {
        return getWait2().until(ExpectedConditions.elementToBeClickable(saveButton)).isDisplayed();
    }

    public ConfigurationPage sendDescription(String text) {
        description.clear();
        description.sendKeys(text);

        return this.waitUntilPageLoadJS();
    }

    public String getJobDescriptionPreviewText() {
        previewButton.click();

        return getWait5().until(ExpectedConditions.visibilityOf( previewDescriptionTextArea)).getText();
    }
}
