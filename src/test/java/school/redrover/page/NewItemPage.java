package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;
import school.redrover.common.PageUtils;

import java.util.List;
import java.util.Objects;


public class NewItemPage extends BasePage<NewItemPage> {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(xpath = "//span[text()='Multibranch Pipeline']")
    private WebElement multibranchPipelineType;

    @FindBy(className = "hudson_model_FreeStyleProject")
    private WebElement freestyleProjectType;

    @FindBy(xpath = "//span[text()='Multi-configuration project']")
    private WebElement multiConfigurationProjectType;

    @FindBy(xpath = "//span[text()='Pipeline']")
    private WebElement pipelineType;

    @FindBy(xpath = "//span[text()='Folder']")
    private WebElement folderType;

    @FindBy(xpath = "//span[text()='Organization Folder']")
    private WebElement organizationFolderType;

    @FindBy(id = "ok-button")
    private WebElement okButton;

    @FindBy(xpath = "//*[contains(@class, 'WorkflowJob')]")
    private WebElement pipelineTypeCheck;

    @FindBy(className = "input-validation-message")
    private List<WebElement> validationMessages;

    @FindBy(id = "itemname-invalid")
    private WebElement errorMessage;
    
    @FindBy(id = "itemname-required")
    private WebElement errorMessageForEmptyItemName;
    
    @FindBy(xpath = "//span[contains(text(), 'General')]")
    private WebElement getGeneralTitle;

    @FindBy(id = "from")
    private WebElement copyFromField;

    @FindBy(xpath = "//p[@class='jenkins-form-label']")
    private WebElement hintFromCopyField;

    public NewItemPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public NewItemPage getPage() {
        return this;
    }

    public NewItemPage sendName(String name) {
        nameField.sendKeys(name);

        return this;
    }

    public NewItemPage clearSendName() {
        nameField.clear();

        return this;
    }

    public NewItemPage selectFolder() {
        folderType.click();

        return this;
    }

    public FolderConfigurationPage selectFolderAndSubmit() {
        folderType.click();
        okButton.click();

        return new FolderConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public NewItemPage selectMultibranchPipeline() {
        multibranchPipelineType.click();

        return this;
    }

    public MultibranchProjectConfigurationPage selectMultibranchPipelineAndSubmit() {
        PageUtils.clickJS(getDriver(), multibranchPipelineType);
        okButton.click();

        return new MultibranchProjectConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public PipelineProjectConfigurationPage selectPipelineAndSubmit() {
        pipelineType.click();
        okButton.click();

        return new PipelineProjectConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public String getErrorMessageText() {
        return getWait10().until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    // дизайн сделан так, что нельзя использовать <T extends BaseConfigurationPage>
    public <T> T clickSubmit(T configurationPage) {
        okButton.click();
        getWait5().until(ExpectedConditions.visibilityOf(getGeneralTitle));

        return configurationPage;
    }

    public NewItemPage selectFreestyleProject() {
        freestyleProjectType.click();

        return this;
    }

    public FreestyleProjectConfigurationPage selectFreestyleProjectAndSubmit() {
        selectFreestyleProject();
        okButton.click();

        return new FreestyleProjectConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public NewItemPage sendNameToCopyFromAndSubmit(String name) {
        copyFromField.sendKeys(name);
        okButton.click();

        return this.waitUntilPageLoadJS();
    }

    public MultibranchProjectConfigurationPage selectMultiConfigurationAndSubmit() {
        PageUtils.clickJS(getDriver(), multiConfigurationProjectType);
        okButton.click();

        return new MultibranchProjectConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public OrganizationFolderConfigurationPage selectOrganizationFolderAndSubmit() {
        PageUtils.clickJS(getDriver(), organizationFolderType);
        okButton.click();

        return new OrganizationFolderConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public HomePage selectItemTypeAndSubmitAndGoHome(String itemType) {
        switch (itemType) {
            case "Folder":
                selectFolderAndSubmit().gotoHomePage();
                break;
            case "Freestyle project":
                selectFreestyleProjectAndSubmit().gotoHomePage();
                break;
            case "Pipeline":
                selectPipelineAndSubmit().gotoHomePage();
                break;
            case "Multi-configuration project":
                selectMultiConfigurationAndSubmit().gotoHomePage();
                break;
            case "Multibranch Pipeline":
                selectMultibranchPipelineAndSubmit().gotoHomePage();
                break;
            case "Organization Folder":
                selectOrganizationFolderAndSubmit().gotoHomePage();
                break;
            default:
                throw new IllegalArgumentException("Unknown item type: " + itemType);
        }
        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public NewItemPage selectPipeline() {
        pipelineType.click();

        return this;
    }

    public boolean isPipelineSelected() {

        return "true".equals(pipelineTypeCheck.getAttribute("aria-checked"));
    }

    public boolean isPipelineHighlighted() {

        return Objects.requireNonNull(pipelineTypeCheck.getAttribute("class")).contains("active");
    }

    public boolean isOkButtonEnabled() {

        return okButton.isEnabled();
    }

    public String getHintFromCopyFieldText() {

        return hintFromCopyField.getText();
    }

    public NewItemPage copyFromFieldIsDisplayed() {
        copyFromField.isDisplayed();

        return this;
    }

    public String getNameDataValid() {

        return nameField.getAttribute("data-valid");
    }

    public MultiConfigProjectConfigurationPage selectMultiConfigurationProjectAndSubmit() {
        multiConfigurationProjectType.click();
        okButton.click();

        return new MultiConfigProjectConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    public NewItemPage clickOkButton() {
        okButton.click();

        return this;
    }

    public String getErrorMessageForEmptyItemName() {
        return errorMessageForEmptyItemName.getText();
    }

    public Boolean validationMessagesIsDisabled() {
        return validationMessages.stream()
                .allMatch(msg -> msg.getAttribute("class").contains("input-message-disabled"));
    }
}
