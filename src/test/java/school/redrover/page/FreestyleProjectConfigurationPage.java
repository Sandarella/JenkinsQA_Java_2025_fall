package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import school.redrover.common.PageUtils;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;


public class FreestyleProjectConfigurationPage extends BaseProjectConfigurationPage<FreestyleProjectConfigurationPage, FreestyleProjectStatusPage> {

    @FindBy(name = "description")
    private WebElement description;

    @FindBy(xpath = "//label[text()='Discard old builds']")
    private WebElement oldBuildsCheck;

    @FindBy(name = "_.daysToKeepStr")
    private WebElement daysToKeepBuilds;

    @FindBy(name = "_.numToKeepStr")
    private WebElement maxNumOfBuildsToKeep;

    @FindBy(id = "triggers")
    private WebElement triggersTitle;

    @FindBy(xpath = "//button[@data-section-id='triggers']")
    private WebElement triggersLinkSideMenu;

    @FindBy(xpath = "//div[@class='jenkins-section__description' and contains(text(), 'Set up automated actions')]")
    private WebElement triggersDescription;

    @FindBy(xpath = "//section[@class='jenkins-section'][2]//label[@class='attach-previous ']")
    private List<WebElement> checkboxTriggersLabels;

    @FindBy(xpath = "//div[@id='source-code-management']/following-sibling::div[contains(@class, 'jenkins-section__description')]")
    private WebElement sourceCodeManagementDescription;

    @FindBy(xpath = "//button[@data-section-id='environment']")
    private WebElement clickEnvironmentMenuOption;
  
    @FindBy(xpath = "//button[@class='jenkins-dropdown__item ']")
    private List<WebElement> addParameterList;

    @FindBy(xpath = "//button[text()='Add Parameter']")
    private WebElement addParameterDropDownButton;

    @FindBy(xpath = "//div[@name ='parameterDefinitions']//div[@class= 'repeated-chunk__header']")
    private List<WebElement> selectedParameterList;

    @FindBy(xpath = "//label[text()='Git']")
    private WebElement gitRadioButton;

    @FindBy(xpath = "//input[@name='_.url']")
    private WebElement repositoryURL;

    @FindBy(xpath = "//label[text()='Trigger builds remotely (e.g., from scripts)']")
    private WebElement triggerBuildsRemotelyButton;

    @FindBy(name = "authToken")
    private WebElement authToken;

    @FindBy(id = "source-code-management")
    private WebElement sourceCodeManagementTitle;

    @FindBy(xpath = "//button[@data-section-id='source-code-management']")
    private WebElement sourceCodeManagementMenuOption;

    @FindBy(xpath = "//button[contains(text(),'Add build')]")
    private WebElement addBuildStepButton;

    @FindBy(xpath = "//button[normalize-space()='Execute Windows batch command']")
    private WebElement executeWindowsBatchCommandButton;

    @FindBy(xpath = "//input[@name='scm' and @checked='true']")
    private WebElement selectedRadioButtonInSCM;

    @FindBy(xpath = "//a[@title='Help for feature: Git']")
    private WebElement gitHelpIcon;

    @FindBy(xpath = "//input[@type='search' and @placeholder='Filter']")
    private WebElement filterBuildStepInputField;

    @FindBy(css = "#toggle-switch-enable-disable-project")
    private WebElement enableDisableProjectSwitch;


    public FreestyleProjectConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected FreestyleProjectStatusPage createProjectStatusPage() {
        return new FreestyleProjectStatusPage(getDriver());
    }

    @Override
    public FreestyleProjectConfigurationPage getPage() {
        return this;
    }

    @Override
    public FreestyleProjectConfigurationPage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.visibilityOf(oldBuildsCheck));

        return this;
    }

    public FreestyleProjectConfigurationPage setCheckBoxDiscardAndSetDaysNum(String daysToKeep, String maxOfBuilds) {
        oldBuildsCheck.click();

        daysToKeepBuilds.sendKeys(daysToKeep);
        maxNumOfBuildsToKeep.sendKeys(maxOfBuilds);

        return this;
    }

    public FreestyleProjectConfigurationPage selectGitAndSendRepositoryUrl(String url) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", gitRadioButton);
        gitRadioButton.click();

        repositoryURL.sendKeys(url);

        return this;
    }

    public FreestyleProjectConfigurationPage setCheckBoxTriggerBuildsAndSendUrl(String url) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", triggerBuildsRemotelyButton);
        triggerBuildsRemotelyButton.click();

        authToken.sendKeys(url);

        return this;
    }

    public List<String> getSettingsToList() {
        return List.of(
                description.getAttribute("value"),
                daysToKeepBuilds.getAttribute("value"),
                maxNumOfBuildsToKeep.getAttribute("value"),
                repositoryURL.getAttribute("value"),
                authToken.getAttribute("value")
        );
    }

    public String getSCMTitleText() {
        return getWait5().until(ExpectedConditions.visibilityOf(sourceCodeManagementTitle)).getText();
    }

    public FreestyleProjectConfigurationPage clickSourceCodeManagementMenuOption() {
        sourceCodeManagementMenuOption.click();

        return this;
    }

    public FreestyleProjectConfigurationPage clickEnvironmentMenuOption() {
        clickEnvironmentMenuOption.click();

        return this;
    }

    public FreestyleProjectConfigurationPage clickBuildStepMenuOption() {
        getWait2().until(ExpectedConditions.visibilityOf(addBuildStepButton));

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", addBuildStepButton);
        addBuildStepButton.click();

        getWait2().until(ExpectedConditions.visibilityOf(executeWindowsBatchCommandButton));

        return this;
    }

    public FreestyleProjectConfigurationPage scrollToSourceCodeManagementWithJS() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", sourceCodeManagementTitle);

        return this;
    }

    public String getScmDescriptionText() {
        return sourceCodeManagementDescription.getText();
    }

    public String getSelectedRadioButtonLabelInSCM() {
        String inputId = selectedRadioButtonInSCM.getAttribute("id");
        WebElement linkedLabel = getDriver().findElement(By.xpath("//label[@for='%s']".formatted(inputId)));

        return linkedLabel.getText();
    }

    public String getConfigUrl() {
        return getDriver().getCurrentUrl();
    }

    public FreestyleProjectConfigurationPage refreshPage() {
        getDriver().navigate().refresh();

        return this;
    }

    public boolean isGitOptionDisplayed() {
        return gitRadioButton.isDisplayed();
    }

    public String getGitTooltipText() {
        return gitHelpIcon.getAttribute("tooltip");
    }

    public WebElement verifySentNameIsInFilter(String buildStep) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated
                        (By.xpath("//button[contains(@style,'inline-flex') and normalize-space()='%s']".formatted(buildStep))));
    }

    public FreestyleProjectConfigurationPage typeIntoFilterBuildStep(String text) {
        filterBuildStepInputField.clear();
        filterBuildStepInputField.sendKeys(text);

        return this;
    }

    public FreestyleProjectConfigurationPage clickTriggersLinkInSideMenu() {
        triggersLinkSideMenu.click();

        return this;
    }

    public FreestyleProjectConfigurationPage clickEnableDisableProject() {
        getWait5().until(ExpectedConditions.visibilityOf(enableDisableProjectSwitch)).click();

        return this;
    }

    public String getTriggerTitleText() {
        return triggersTitle.getText();
    }

    public String getTriggersDescriptionText() {
        return triggersDescription.getText();
    }

    public List<String> getTriggerCheckboxLabels() {
        return checkboxTriggersLabels.stream()
                .limit(5)
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public FreestyleProjectConfigurationPage selectCheckbox(String checkBoxLabel) {
        getWait5().until(ExpectedConditions.elementToBeClickable(By
                .xpath("//label[text()='%s']".formatted(checkBoxLabel))))
                .click();

        return this;
    }

    public FreestyleProjectConfigurationPage clickAddParameterDropDownButton() {
        ((JavascriptExecutor) getDriver())
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", addParameterDropDownButton);

        new Actions(getDriver()).moveToElement(addParameterDropDownButton).click().perform();
        getWait10().until(ExpectedConditions.visibilityOfAllElements(addParameterList));

        return this;
    }

    public List<String> getAddParameterList() {
        return addParameterList
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    public FreestyleProjectConfigurationPage selectParameterInDropDownButton(String parameterName) {
        List<String> parameterList = getAddParameterList();

        Assert.assertNotEquals(addParameterList.size(), 0);
        for (WebElement element : addParameterList) {
            if (parameterList.contains(parameterName)) {
                PageUtils.mouseEnterJS(getDriver(), element);
                PageUtils.clickJS(getDriver(), element);
                break;
            } else
                System.out.println("Параметр " + parameterName + " не найден");
        }

        getWait10().until(ExpectedConditions.visibilityOfAllElements(selectedParameterList));

        return this;
    }

    public List<String> getSelectedParameterList() {
        return selectedParameterList
                .stream()
                .map(WebElement::getText)
                .map(text -> text.split("\\n")[0])
                .map(String::trim)
                .toList();
    }
}
