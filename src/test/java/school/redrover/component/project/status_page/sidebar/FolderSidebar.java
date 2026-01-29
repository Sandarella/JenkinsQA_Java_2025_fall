package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.SidebarBuildHistoryTrait;
import school.redrover.trait.project_sidebar.SidebarCredentialsTrait;


public class FolderSidebar extends BaseSidebarComponent<FolderSidebar, FolderStatusPage, FolderConfigurationPage>
        implements SidebarBuildHistoryTrait<FolderBuildHistoryPage>, SidebarCredentialsTrait<FolderCredentialsPage> {

    @FindBy(xpath = "//a[contains(@href, '/newJob')]")
    private WebElement sidebarNewItem;


    public FolderSidebar(WebDriver driver) {
        super(driver, FolderStatusPage::new);
    }

    @Override
    public FolderSidebar waitUntilComponentLoad() {
        getWait5().until(ExpectedConditions.elementToBeClickable(sidebarNewItem));

        return this;
    }

    @Override
    public FolderStatusPage getProjectStatusPage() {
        return new FolderStatusPage(getDriver()).waitUntilPageLoadJS();
    }

    @Override
    public FolderConfigurationPage getProjectConfigurationPage() {
        return new FolderConfigurationPage(getDriver()).waitUntilPageLoadJS();
    }

    @Override
    public FolderBuildHistoryPage getProjectBuildHistoryPage() {
        return new FolderBuildHistoryPage(getDriver()).waitUntilPageLoadJS();
    }

    @Override
    public FolderCredentialsPage getProjectCredentialsPage() {
        return new FolderCredentialsPage(getDriver()).waitUntilPageLoadJS();
    }

    public NewItemPage clickSidebarNewItem() {
        sidebarNewItem.click();

        return new NewItemPage(getDriver()).waitUntilPageLoadJS();
    }
}