package school.redrover.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import school.redrover.common.PageUtils;

import java.util.List;


public class PipelineProjectConfigurationPage extends BaseProjectConfigurationPage<PipelineProjectConfigurationPage, PipelineProjectStatusPage> {

    @FindBy(id = "advanced")
    private WebElement advancedTitle;

    @FindBy(xpath = "//button[text() = 'Apply']")
    private WebElement applyButton;

    @FindBy(xpath = "//button[@data-section-id='advanced']")
    private WebElement advancedMenuItem;

    @FindBy(xpath = "//div[@id='advanced']/parent::section/descendant::a[@tooltip]")
    private List<WebElement> tooltipList;

    @FindBy(xpath = "//div[@id='advanced']/parent::section/descendant::div[@class = 'help']")
    private WebElement helpElement;

    @FindBy(xpath = "//label[text()='Quiet period']")
    private WebElement quietPeriodLabel;

    @FindBy(name = "hasCustomQuietPeriod")
    private WebElement quietPeriodCheckbox;

    @FindBy(xpath = "//div[text()='Display Name']")
    private WebElement displayNameLabel;

    @FindBy(name = "_.displayNameOrNull")
    private WebElement displayNameInput;

    @FindBy(xpath = "//div[text()='Number of seconds']")
    private WebElement numberOfSecondsLabel;

    @FindBy(name = "quiet_period")
    private WebElement numberOfSecondsInput;

    @FindBy(xpath = "//button[@data-section-id = 'triggers']")
    private WebElement triggersSectionButton;

    @FindBy(xpath = "//label[contains(text(), 'Build after other projects are built')]")
    private WebElement buildAfterOtherProjectsAreBuiltLabel;

    @FindBy(xpath = "//label[contains(text(), 'Build periodically')]")
    private WebElement buildPeriodicallyLabel;

    @FindBy(xpath = "//label[contains(text(), 'GitHub hook trigger for GITScm polling')]")
    private WebElement hookTriggerForGitScmPollingLabel;

    @FindBy(xpath = "//label[contains(text(), 'Poll SCM')]")
    private WebElement pollScmLabel;

    @FindBy(xpath = "//label[contains(text(), 'Trigger builds remotely')]")
    private WebElement triggerBuildsRemotelyLabel;

    @FindBy(xpath = "//input[@id='cb8']")
    private WebElement buildAfterOtherProjectsAreBuiltCheckbox;

    @FindBy(xpath = "//input[@id='cb9']")
    private WebElement buildPeriodicallyCheckBox;

    @FindBy(xpath = "//input[@id='cb10']")
    private WebElement githubHookTriggerForGitScmPollingCheckbox;

    @FindBy(xpath = "//input[@id='cb11']")
    private WebElement pollScmCheckbox;

    @FindBy(xpath = "//input[@id='cb12']")
    private WebElement triggerBuildsRemotelyCheckbox;

    @FindBy(xpath = "//textarea[@name = '_.spec']")
    private WebElement scheduleTextarea;

    @FindBy(xpath = "//div[contains(text(), 'Would last have run at') and contains(text(), 'would next run at')]")
    private WebElement textAreaValidationMessage;

    @FindBy(xpath = "//div[contains(text(), 'Schedule')]/following-sibling::div" + "//div[@class = 'error']")
    private WebElement textErrorMessage;

    @FindBy(xpath = "//div[@id='advanced']/parent::section/descendant::button[contains(text(),'Advanced')]")
    private WebElement advancedButton;

    @FindBy(name = "quiet_period")
    private WebElement quietPeriodField;

    @FindBy(id = "toggle-switch-enable-disable-project")
    private WebElement enableDisableProjectToggle;

    @FindBy(className = "jenkins-toggle-switch__label__checked-title")
    private WebElement checkedTitleToggle;

    @FindBy(className = "jenkins-toggle-switch__label__unchecked-title")
    private WebElement uncheckedTitleToggle;

    @FindBy(css = "#error-description > h2")
    private WebElement descriptionErrorPopup;

    @FindBy(xpath = "//div[@id='error-description']/parent::*/following-sibling::button")
    private WebElement closePopupButton;

    @FindBy(id = "footer")
    private WebElement footer;

    public PipelineProjectConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected PipelineProjectStatusPage getProjectStatusPage() {
        return new PipelineProjectStatusPage(getDriver());
    }

    @Override
    public PipelineProjectConfigurationPage getPage() {
        return this;
    }

    public PipelineProjectConfigurationPage clickAdvancedLinkInSideMenu() {
        advancedMenuItem.click();

        return this;
    }

    public PipelineProjectConfigurationPage scrollDownToAdvancedSection() {
        ((JavascriptExecutor) getDriver()).executeScript(
                "arguments[0].scrollIntoView(true);", advancedTitle);

        return this;
    }

    public String getAdvancedTitleText() {
        return advancedTitle.getText();
    }

    public PipelineProjectConfigurationPage clickAdvancedButton() {
        PageUtils.scrollToElement(getDriver(), footer);

        advancedButton.click();

        getWait10().until(ExpectedConditions.visibilityOf(quietPeriodLabel));

        return this;
    }

    public String getQuietPeriodLabelText() {
        new Actions(getDriver()).moveToElement(quietPeriodLabel).perform();

        return quietPeriodLabel.getText();
    }

    public boolean quietPeriodCheckboxIsSelected() {
        return quietPeriodCheckbox.isSelected();
    }

    public PipelineProjectConfigurationPage clickQuitePeriod() {
        new Actions(getDriver()).moveToElement(quietPeriodLabel).click().perform();

        getWait10().until(ExpectedConditions.visibilityOf(quietPeriodField));

        return this;
    }

    public String getDisplayNameLabelText() {
        new Actions(getDriver()).moveToElement(displayNameLabel).perform();

        return displayNameLabel.getText().split("\\n")[0];
    }

    public boolean displayNameValueIsEmpty() {
        return displayNameInput.getAttribute("value").isEmpty();
    }

    public PipelineProjectConfigurationPage sendDisplayName(String displayName) {
        new Actions(getDriver()).moveToElement(displayNameInput).perform();
        displayNameInput.sendKeys(displayName);

        return this;
    }

    public String getNumberOfSecondsLabelText() {
        return numberOfSecondsLabel.getText();
    }

    public boolean isNumberOfSecondsInputDisplayed() {
        return numberOfSecondsInput.isDisplayed();
    }

    public List<String> getTooltipList() {
        return tooltipList
                .stream()
                .map(webElement -> webElement.getAttribute("title"))
                .toList();
    }

    public boolean isHelpElementDisplayed() {
        boolean isHelpElementDisplayed = false;

        Assert.assertNotEquals(tooltipList.size(), 0);
        for (WebElement webElement : tooltipList) {
            new Actions(getDriver()).moveToElement(webElement).click().perform();
            isHelpElementDisplayed = helpElement.isDisplayed();
        }

        return isHelpElementDisplayed;
    }

    public String getToggleCheckedLabelText() {
        return getWait5().until(ExpectedConditions.visibilityOf(checkedTitleToggle)).getText();
    }

    public String getToggleUncheckedLabelText() {
        return getWait5().until(ExpectedConditions.visibilityOf(uncheckedTitleToggle)).getText();
    }

    public PipelineProjectConfigurationPage clickToggle() {
        enableDisableProjectToggle.click();

        return this;
    }

    public PipelineProjectConfigurationPage clickTriggersSectionButton() {
        triggersSectionButton.click();

        getWait5().until(ExpectedConditions.attributeContains(
                triggersSectionButton, "class", "task-link--active"));

        return this;
    }

    public PipelineProjectConfigurationPage selectBuildPeriodicallyCheckbox() {
        if (!buildPeriodicallyCheckBox.isSelected()) {
            buildPeriodicallyLabel.click();
        }
        return this;
    }

    public PipelineProjectConfigurationPage sendScheduleText(String validTimePeriod) {
        scheduleTextarea.clear();
        scheduleTextarea.sendKeys(validTimePeriod);

        return this;
    }

    public String getValidationMessageText() {
        getWait5().until(ExpectedConditions.visibilityOf(textAreaValidationMessage));
        return textAreaValidationMessage.getText();
    }

    public String getErrorMessageText() {
        getWait5().until(ExpectedConditions.visibilityOf(textErrorMessage));
        return textErrorMessage.getText();
    }

    public String getDescriptionErrorPopup() {
        getWait10().until(ExpectedConditions.visibilityOf(descriptionErrorPopup));

        return descriptionErrorPopup.getText();
    }

    public void closePopup() {

        getWait5().until(ExpectedConditions.elementToBeClickable(closePopupButton)).click();
    }

    public WebElement[] selectAllTriggers() {
        WebElement[] triggers = {buildAfterOtherProjectsAreBuiltLabel,
                buildPeriodicallyLabel,
                hookTriggerForGitScmPollingLabel,
                pollScmLabel,
                buildPeriodicallyLabel};

        for (WebElement trigger : triggers) {
            PageUtils.scrollToElement(getDriver(), trigger);
            trigger.click();
        }
        return triggers;
    }
}