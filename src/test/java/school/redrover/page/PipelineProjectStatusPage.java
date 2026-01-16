package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.component.project.status_page.sidebar.PipelineProjectSidebar;

import java.util.List;


public class PipelineProjectStatusPage extends BaseProjectStatusPage<PipelineProjectStatusPage, PipelineProjectSidebar> {

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

    public PipelineProjectStatusPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectSidebar getSidebarComponent() {
        return new PipelineProjectSidebar(getDriver());
    }

    @Override
    public PipelineProjectStatusPage getPage() {
        return this;
    }

    @Override
    public PipelineProjectStatusPage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.visibilityOf(deletePipeline));

        return this;
    }

    public String getStatusDisplayNameText() {
        return getHeaderText();
    }

    public String getDisplayNameInBreadcrumbBar(String displayName) {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath(".//a[text()='%s']".formatted(displayName)))).getText();
    }

    public String getWarningMessage() {

        return getWait5().until(ExpectedConditions.visibilityOf(enableProjectWarning)).getText();
    }

    public PipelineProjectStatusPage clickAddDescriptionButton() {
        descriptionButton.click();
        return this;
    }

    public PipelineProjectStatusPage addDescriptionAndSave(String description) {
        descriptionTextarea.sendKeys(description);
        descriptionSubmitButton.click();

        return this.waitUntilPageLoadJS();
    }

    public PipelineProjectStatusPage clearDescription() {
        descriptionTextarea.clear();
        return this;
    }

    public PipelineProjectStatusPage clickEditDescriptionButton() {
        editDescriptionButton.click();
        return this;
    }

    public String getDescriptionText() {
        return getWait5().until(ExpectedConditions.visibilityOf(descriptionContent)).getText();
    }

    public PipelineProjectSyntaxPage clickPipelineSyntax() {
        pipelineSyntax.click();
        return new PipelineProjectSyntaxPage(getDriver());
    }

    public PipelineProjectHistoryPage clickBuildHistory() {

        getWait10().until(ExpectedConditions.elementToBeClickable(buildsItems)).click();

        return new PipelineProjectHistoryPage(getDriver());
    }

    public HomePage confirmDeleteAtJobPage() {
        confirmDeletePipeline.click();

        return new HomePage(getDriver()).waitUntilPageLoadJS();
    }

    public PipelineProjectStatusPage cancelDelete() {
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