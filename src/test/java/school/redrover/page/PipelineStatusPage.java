package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.trait.project_sidebar.SidebarBuildNowTrait;
import school.redrover.trait.project_sidebar.SidebarChangesTrait;
import school.redrover.trait.project_sidebar.SidebarCredentialsTrait;
import school.redrover.trait.project_sidebar.SidebarMoveTrait;

import java.util.List;


public class PipelineStatusPage extends BaseProjectStatusPage<PipelineStatusPage>
        implements SidebarChangesTrait, SidebarBuildNowTrait, SidebarMoveTrait, SidebarCredentialsTrait {

    @FindBy(xpath = "//a[contains(@href, '/configure')]")
    private WebElement configureMenuItem;

    @FindBy(id = "description-link")
    private WebElement descriptionButton;

    @FindBy(xpath = "//a[@href = 'editDescription']")
    private WebElement editDescriptionButton;

    @FindBy(name = "description")
    private WebElement descriptionTextarea;

    @FindBy(name = "Submit")
    private WebElement descriptionSubmitButton;

    @FindBy(id = "description-content")
    private WebElement descriptionContent;

    @FindBy(xpath = "//a[@data-build-success='Build scheduled']")
    private WebElement buildNow;

    @FindBy(xpath = "//span[text()='Delete Pipeline']/ancestor::a")
    private WebElement deletePipeline;

    @FindBy(xpath = "//a[@href='/job/PipelineName/pipeline-syntax']")
    private WebElement pipelineSyntax;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(css = "div[page-entry-id]")
    private List<WebElement> buildsList;

    @FindBy(id = "enable-project")
    private WebElement enableProjectWarning;

    @FindBy(id = "jenkins-build-history")
    private WebElement buildsItems;

    @FindBy(css = "[data-id='ok']")
    private WebElement confirmDeletePipeline;

    public PipelineStatusPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineStatusPage getPage() {
        return this;
    }

    @Override
    public PipelineStatusPage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.visibilityOf(deletePipeline));

        return this;
    }

    public String getDisplayNameInStatus() {
        return getHeader().getText();
    }

    public String getDisplayNameInBreadcrumbBar(String displayName) {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath(".//a[text()='%s']".formatted(displayName)))).getText();
    }

    public String getWarningMessage() {

        return getWait5().until(ExpectedConditions.visibilityOf(enableProjectWarning)).getText();
    }

    public PipelineStatusPage clickAddDescriptionButton() {
        descriptionButton.click();
        return this;
    }

    public PipelineStatusPage addDescriptionAndSave(String description) {
        descriptionTextarea.sendKeys(description);
        descriptionSubmitButton.click();

        return this.waitUntilPageLoadJS();
    }

    public PipelineStatusPage clearDescription() {
        descriptionTextarea.clear();
        return this;
    }

    public PipelineStatusPage clickEditDescriptionButton() {
        editDescriptionButton.click();
        return this;
    }

    public String getDescriptionText() {
        return getWait5().until(ExpectedConditions.visibilityOf(descriptionContent)).getText();
    }

    public PipelineStatusPage clickBuildNow() {
        buildNow.click();
        return this;
    }

    public PipelineStatusPage clickDeletePipeline() {
        deletePipeline.click();

        return this.waitUntilPageLoadJS();
    }

    public PipelineSyntaxPage clickPipelineSyntax() {
        pipelineSyntax.click();
        return new PipelineSyntaxPage(getDriver());
    }

    public PipelineHistoryPage clickBuildHistory() {

        getWait10().until(ExpectedConditions.elementToBeClickable(buildsItems)).click();

        return new PipelineHistoryPage(getDriver());
    }

    public HomePage confirmDeleteAtJobPage() {
        confirmDeletePipeline.click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public PipelineStatusPage cancelDelete() {
        WebElement cancelDeleteButton = getWait2().until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//dialog[@open]//button[@data-id='cancel']"))
        );
        cancelDeleteButton.click();
        getWait5().until(ExpectedConditions.stalenessOf(cancelDeleteButton));

        return this;
    }

    public int getSizeBuildsList() {
        return buildsList.size();
    }
}