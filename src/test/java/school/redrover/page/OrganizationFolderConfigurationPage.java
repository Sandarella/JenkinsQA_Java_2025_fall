package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class OrganizationFolderConfigurationPage extends BaseProjectConfigurationPage<OrganizationFolderConfigurationPage, OrganizationFolderStatusPage> {

    @FindBy(xpath = "//span[text()='Delete Organization Folder']/ancestor::a")
    private WebElement deleteMenuItem;

    @FindBy(name = "_.displayNameOrNull")
    private WebElement displayNameField;

    @FindBy(name = "_.description")
    private WebElement descriptionTestArea;

    @FindBy(css = "a[tooltip='Help for feature: Display Name']")
    private WebElement displayNameHelpLink;

    @FindBy(linkText = "Branch API Plugin")
    private WebElement displayNameTooltipLink;

    @FindBy(css = "input[placeholder='Filter']")
    private WebElement filterField;

    @FindBy(css = "button[suffix='navigators']")
    private WebElement addRepositorySourceButton;

    @FindBy(css = "button.jenkins-dropdown__item:not([style*='display: none'])")
    private List<WebElement> repositorySourceButtons;

    public OrganizationFolderConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected OrganizationFolderStatusPage getProjectStatusPage() {
        return new OrganizationFolderStatusPage(getDriver());
    }

    @Override
    public OrganizationFolderConfigurationPage getPage() {
        return this;
    }

    public OrganizationFolderConfigurationPage inputDisplayName(String name) {
        displayNameField.sendKeys(name);

        return this;
    }

    public OrganizationFolderConfigurationPage inputDescription(String name) {
        descriptionTestArea.sendKeys(name);

        return this;
    }

    public OrganizationFolderConfigurationPage clickDisplayNameLink() {
        displayNameHelpLink.click();

        return this;
    }

    public String getDisplayNameTooltipLink() {
        return displayNameTooltipLink.getAttribute("href");
    }

    public OrganizationFolderConfigurationPage filterRepositorySources(String sourceName) {
        addRepositorySourceButton.click();
        getWait5().until(ExpectedConditions.visibilityOf(filterField)).sendKeys(sourceName);

        return this;
    }

    public List<String> getRepositorySourceNames() {
        return repositorySourceButtons
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
