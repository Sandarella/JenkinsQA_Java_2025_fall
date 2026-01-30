package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.ui.component.project.status_page.sidebar.OrganizationFolderSidebar;


public class OrganizationFolderStatusPage extends BaseProjectStatusPage<OrganizationFolderStatusPage, OrganizationFolderSidebar> {

    @FindBy(xpath = "//span[text()='Delete Organization Folder']/ancestor::a")
    private WebElement deleteMenuItem;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(id = "view-message")
    private WebElement descriptionTextArea;

    @FindBy(css = "button[data-id='ok']")
    private WebElement yesConfirmationDeleteButton;

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

    public String getDescription() {
        return descriptionTextArea.getText();
    }

    public HomePage clickYesConfirmationDelete() {
        yesConfirmationDeleteButton.click();

        return new HomePage(getDriver());
    }
}