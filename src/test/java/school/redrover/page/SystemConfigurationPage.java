package school.redrover.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.common.BasePage;
import school.redrover.common.PageUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class SystemConfigurationPage extends BasePage<SystemConfigurationPage> {

    @FindBy(name = "Submit")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='global-properties']/..//label")
    private List<WebElement> globalPropertiesList;

    @FindBy(xpath = "//label[text()='Disable deferred wipeout on this node']")
    private WebElement globalPropertiesDisableWipeoutCheckboxToClick;

    @FindBy(id = "cb3")
    private WebElement globalPropertiesDisableWipeoutCheckboxIsEnabled;

    @FindBy(xpath = "//a[contains(@tooltip,'Disable deferred wipeout on this node')]")
    private WebElement globalPropertiesDisableWipeoutCheckboxQuestion;

    @FindBy(css = "[data-tippy-root]")
    private WebElement globalPropertiesDisableWipeoutCheckboxTooltip;

    @FindBy(xpath = "//input[@name='hudson-node_monitors-DiskSpaceMonitorNodeProperty']/following-sibling::label")
    private WebElement globalPropertiesDiskSpaceMonitoringThresholdsCheckbox;

    @FindBy(xpath = "//input[@name='hudson-slaves-EnvironmentVariablesNodeProperty']/following-sibling::label")
    private WebElement globalPropertiesEnvironmentVariablesCheckbox;

    @FindBy(xpath = "//div[@nameref='cb5']//button")
    private WebElement addVariablesButton;

    @FindBy(xpath = "//input[@name='env.key']")
    private WebElement variableNameField;

    @FindBy(xpath = "//input[@name='env.value']")
    private WebElement variableValueField;

    @FindBy(name = "_.freeDiskSpaceThreshold")
    private WebElement freeDiskSpaceThresholdField;

    @FindBy(name = "_.freeDiskSpaceWarningThreshold")
    private WebElement freeDiskSpaceWarningThresholdField;

    @FindBy(name = "_.freeTempSpaceThreshold")
    private WebElement freeTempSpaceThresholdField;

    @FindBy(name = "_.freeTempSpaceWarningThreshold")
    private WebElement freeTempSpaceWarningThresholdField;

    @FindBy(xpath = "//div[@nameref='cb3']//div[@class='help']/div[1]")
    private WebElement hintText;

    @FindBy(name = "builtin.mode")
    private WebElement usageModeDropdown;

    @FindBy(id = "metrics")
    private WebElement metrics;

    @FindBy(name = "_.computerRetentionCheckInterval")
    private WebElement computerRetentionCheckIntervalField;

    @FindBy(name = "_.quietPeriod")
    private WebElement quietPeriodField;

    @FindBy(name = "Apply")
    private WebElement applyButton;

    @FindBy(xpath = "//div[text()='This value should be between 1 and 60']")
    private WebElement invalidComputerRetentionCheckIntervalHint;

    @FindBy(css = "div.validation-error-area--visible div.error")
    private WebElement quietPeriodHint;

    @FindBy(name = "system_message")
    private WebElement systemMessageTextArea;

    @FindBy(name = "_.numExecutors")
    private WebElement systemMessageNumberOfExecutorsTextArea;

    @FindBy(className = "textarea-show-preview")
    private WebElement previewLink;

    @FindBy(xpath = "//div[contains(@class,'textarea-preview-container')]/following-sibling::div")
    private WebElement previewTextAria;

    @FindBy(css = "div .help[style='display: block;']")
    private List<WebElement> helpHomeDirectoryInfo;

    public SystemConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SystemConfigurationPage getPage() {
        return this;
    }

    public HomePage clickSave() {
        saveButton.click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public SystemConfigurationPage checkGlobalPropertiesDiskSpaceMonitoringThresholds() {
        PageUtils.scrollToElement(getDriver(), globalPropertiesDiskSpaceMonitoringThresholdsCheckbox);
        globalPropertiesDiskSpaceMonitoringThresholdsCheckbox.click();

        return this;
    }

    public SystemConfigurationPage checkGlobalPropertiesEnvironmentVariables() {
        PageUtils.scrollToElement(getDriver(), globalPropertiesEnvironmentVariablesCheckbox);
        globalPropertiesEnvironmentVariablesCheckbox.click();

        return this;
    }

    public SystemConfigurationPage clickAddVariables() {
        addVariablesButton.click();

        return this;
    }

    public SystemConfigurationPage sendFreeDiskSpaceThreshold(String currentValue) {
        freeDiskSpaceThresholdField.clear();
        freeDiskSpaceThresholdField.sendKeys(currentValue);

        return this;
    }

    public SystemConfigurationPage sendFreeDiskSpaceWarningThreshold(String currentValue) {
        freeDiskSpaceWarningThresholdField.clear();
        freeDiskSpaceWarningThresholdField.sendKeys(currentValue);

        return this;
    }

    public SystemConfigurationPage sendFreeTempSpaceThreshold(String currentValue) {
        freeTempSpaceThresholdField.clear();
        freeTempSpaceThresholdField.sendKeys(currentValue);

        return this;
    }

    public SystemConfigurationPage sendFreeTempSpaceWarningThreshold(String currentValue) {
        freeTempSpaceWarningThresholdField.clear();
        freeTempSpaceWarningThresholdField.sendKeys(currentValue);

        return this;
    }

    public SystemConfigurationPage sendVariableName(String currentValue) {
        variableNameField.clear();
        variableNameField.sendKeys(currentValue);

        return this;
    }

    public SystemConfigurationPage sendVariableValue(String currentValue) {
        variableValueField.clear();
        variableValueField.sendKeys(currentValue);

        return this;
    }

    public SystemConfigurationPage setSystemMessage(String systemMessage) {
        systemMessageTextArea.sendKeys(systemMessage);

        return this;
    }

    public SystemConfigurationPage clearSystemMessage() {
        systemMessageTextArea.clear();

        return this;
    }

    public String getPreviewSystemMessageText() {
        previewLink.click();
        return getWait2().until(ExpectedConditions.visibilityOf(previewTextAria)).getText();
    }

    public SystemConfigurationPage setNumberOfExecutors(String numberOfExecutors) {
        systemMessageNumberOfExecutorsTextArea.clear();
        systemMessageNumberOfExecutorsTextArea.sendKeys(numberOfExecutors);

        return this;
    }

    public Integer getNumberOfOpenTooltips() {
        return helpHomeDirectoryInfo.size();
    }

    public SystemConfigurationPage clickTooltip(String tooltipName) {
        WebElement tooltip = getDriver().findElement(By.cssSelector("a[tooltip= 'Help for feature: %s']".formatted(tooltipName)));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", tooltip);
        tooltip.click();

        return this;
    }

    public SystemConfigurationPage checkGlobalPropertiesDisableWipeoutCheckbox() {
        if (!globalPropertiesDisableWipeoutCheckboxIsEnabled.isSelected()) {
            PageUtils.scrollToElement(getDriver(), globalPropertiesDisableWipeoutCheckboxToClick);
            globalPropertiesDisableWipeoutCheckboxToClick.click();
        }

        return this;
    }

    public boolean isGlobalPropertiesDisableWipeoutCheckboxSelected() {
        return globalPropertiesDisableWipeoutCheckboxIsEnabled.isSelected();
    }

    public String getGlobalPropertiesDisableWipeoutCheckboxTooltipOnHoverText() {
        PageUtils.scrollToElement(getDriver(), globalPropertiesDisableWipeoutCheckboxQuestion);
        new Actions(getDriver()).moveToElement(globalPropertiesDisableWipeoutCheckboxQuestion).perform();

        return getWait5().until(ExpectedConditions.visibilityOf(globalPropertiesDisableWipeoutCheckboxTooltip)).getText();
    }

    public SystemConfigurationPage clickCheckboxTooltip() {
        new Actions(getDriver()).moveToElement(metrics).perform();
        PageUtils.scrollToElement(getDriver(), globalPropertiesDisableWipeoutCheckboxQuestion);
        globalPropertiesDisableWipeoutCheckboxQuestion.click()        ;

        return this;
    }

    public String getHintText() {
        return hintText.getText().trim();
    }

    public List<String> getUsageModeOptions() {
        Select select = new Select(usageModeDropdown);

        return select.getOptions()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    public String getInputComputerRetentionCheckIntervalValue() {
        return computerRetentionCheckIntervalField.getAttribute("value");
    }

    public SystemConfigurationPage setInputComputerRetentionCheckIntervalValue(String intervalValue) {
        computerRetentionCheckIntervalField.clear();
        computerRetentionCheckIntervalField.sendKeys(intervalValue);

        return this;
    }

    public SystemConfigurationPage clearQuietPeriod() {
        PageUtils.scrollToElement(getDriver(), quietPeriodField);
        quietPeriodField.clear();

        return this;
    }

    public SystemConfigurationPage setQuietPeriod(String seconds) {
        quietPeriodField.sendKeys(seconds, Keys.TAB);

        return this;
    }

    public String getInputQuietPeriodValue() {
        return quietPeriodField.getAttribute("value");
    }

    public ErrorPage clickSaveButtonWithInvalidValue() {
        saveButton.click();

        return new ErrorPage(getDriver());
    }

    public SystemConfigurationPage clickApply() {
        applyButton.click();

        return this;
    }

    public String getInvalidComputerRetentionCheckIntervalText() {
        return getWait5().until(ExpectedConditions.visibilityOf(invalidComputerRetentionCheckIntervalHint)).getText();
    }

    public String getQuietPeriodText() {
        return getWait5().until(ExpectedConditions.visibilityOf(quietPeriodHint)).getText();
    }

    public List<String> getGlobalPropertiesListText() {
        return globalPropertiesList
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getFreeDiskSpaceThresholdText() {
        return freeDiskSpaceThresholdField.getAttribute("value");
    }

    public String getFreeDiskSpaceWarningThresholdText() {
        return freeDiskSpaceWarningThresholdField.getAttribute("value");
    }

    public String getFreeTempSpaceThresholdText() {
        return freeTempSpaceThresholdField.getAttribute("value");
    }

    public String getFreeTempSpaceWarningThresholdText() {
        return freeTempSpaceWarningThresholdField.getAttribute("value");
    }

    public Map<String, String> getEnvironmentVariablesValues() {
        return Map.of(
                Objects.requireNonNull(variableNameField.getAttribute("value")),
                Objects.requireNonNull(variableValueField.getAttribute("value"))
        );
    }

}