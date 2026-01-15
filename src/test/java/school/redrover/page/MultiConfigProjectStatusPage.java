package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.component.project.status_page.sidebar.MultiConfigProjectSidebar;


public class MultiConfigProjectStatusPage extends BaseProjectStatusPage<MultiConfigProjectStatusPage, MultiConfigProjectSidebar> {

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(id = "description-link")
    private WebElement editDescriptionLink;

    @FindBy(name = "description")
    private WebElement descriptionField;

    @FindBy(xpath = "//div[@id='description-content']")
    private WebElement projectDescription;

    @FindBy(css = "a[href$='/confirm-rename']")
    private WebElement sidebarRenameLink;

    @FindBy(xpath = "//span[text()='Delete Multi-configuration project']/ancestor::a")
    private WebElement deleteMenuItem;

    @FindBy(xpath = "//*[contains(@class, 'hoverable-children-model-link')]")
    private WebElement hoverElement;

    @FindBy(css = "[href$='confirm-rename']")
    private WebElement dropdownMenuRenameLink;

    @FindBy (xpath = "//*[@id='enable-project']")
    private WebElement warning;

    @FindBy (css = "h1.job-index-headline.page-headline")
    private WebElement projectName;


    public MultiConfigProjectStatusPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigProjectSidebar getSidebarComponent() {
        return new MultiConfigProjectSidebar(getDriver());
    }

    @Override
    public MultiConfigProjectStatusPage getPage() {
        return this;
    }

    @Override
    public MultiConfigProjectStatusPage waitUntilPageLoad() {
        getWait10().until(ExpectedConditions.visibilityOf(deleteMenuItem));

        return this;
    }

    public MultiConfigProjectStatusPage clearDescriptionField() {
        editDescriptionLink.click();
        descriptionField.clear();
        return this;
    }

    public MultiConfigProjectStatusPage sendDescription(String description) {
        descriptionField.sendKeys(description);
        submitButton.click();

        return this;
    }

    public String getDescription() {
        getWait5().until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@id='description-content']")));
        return projectDescription.getText();
    }

    public MultiConfigProjectRenamingPage clickRenameViaDashboardDropDownMenu() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(hoverElement, 10, 10).perform();
        dropdownMenuRenameLink.click();

        return new MultiConfigProjectRenamingPage(getDriver());
    }

    public String getBreadcrumbItem() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[contains(text(),'Configuration')]"))).getText();
    }

    public boolean isWarningVisible() {

        return warning.isDisplayed();
    }

    public String getProjectName(String project) {

        return projectName.getText();
    }
}
