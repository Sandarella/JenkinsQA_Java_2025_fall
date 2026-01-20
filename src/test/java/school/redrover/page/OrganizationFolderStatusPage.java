package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.component.project.status_page.sidebar.OrganizationFolderSidebar;


public class OrganizationFolderStatusPage extends BaseProjectStatusPage<OrganizationFolderStatusPage, OrganizationFolderSidebar> {

    @FindBy(xpath = "//span[text()='Delete Organization Folder']/ancestor::a")
    private WebElement deleteMenuItem;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    public OrganizationFolderStatusPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderSidebar getSidebarComponent() {
        return new OrganizationFolderSidebar(getDriver());
    }

    @Override
    public OrganizationFolderStatusPage getPage() {
        return this;
    }

    public String getDisplayNameOrganizationFolder() {
        return getHeaderText();
    }

    public String getDescriptionOrganizationFolder() {
        return getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.id("view-message"))).getText();
    }

    public HomePage clickYesConfirmationDelete() {
        getWait2().until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-id='ok']"))).click();

        return new HomePage(getDriver());
    }
}