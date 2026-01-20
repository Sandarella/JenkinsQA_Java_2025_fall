package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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

    @FindBy(xpath = "//*[@id='enable-project']")
    private WebElement warning;

    @FindBy(css = "h1.job-index-headline.page-headline")
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

    public MultiConfigProjectStatusPage clearDescriptionField() {
        editDescriptionLink.click();
        descriptionField.clear();
        return this;
    }

    public MultiConfigProjectStatusPage sendDescription(String description) {
        descriptionField.sendKeys(description);
        submitButton.click();

        return new MultiConfigProjectStatusPage(getDriver()).waitUntilPageLoadJS();
    }

    public String getDescription() {
        return projectDescription.getText();
    }

    public boolean isWarningVisible() {
        return warning.isDisplayed();
    }

    public String getProjectName(String project) {
        return projectName.getText();
    }

}
