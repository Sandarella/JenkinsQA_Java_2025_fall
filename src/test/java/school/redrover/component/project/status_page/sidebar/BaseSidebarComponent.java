package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;
import school.redrover.component.BaseComponent;
import school.redrover.page.BaseProjectConfigurationPage;
import school.redrover.page.BaseProjectStatusPage;


public abstract class BaseSidebarComponent<
        SidebarComponent extends BaseSidebarComponent<?, ?, ?, ?>,
        ProjectStatusPage extends BaseProjectStatusPage<ProjectStatusPage, ?>,
        ProjectConfigurationPage extends BaseProjectConfigurationPage<ProjectConfigurationPage, ?>,
        ProjectRenamingPage extends BasePage<ProjectRenamingPage>>
        extends BaseComponent<SidebarComponent> {

    @FindBy(xpath = "//a[contains(., 'Status')]")
    private WebElement statusMenuItem;

    @FindBy(xpath = "//a[contains(@href, '/configure')]")
    private WebElement configureMenuItem;

    @FindBy(xpath = "//a[contains(., 'Rename')]")
    private WebElement renameMenuItem;

    @FindBy(xpath = "//a[contains(., 'Delete')]")
    private WebElement deleteMenuItem;


    public BaseSidebarComponent(WebDriver driver) {
        super(driver);
    }

    public abstract ProjectStatusPage getProjectStatusPage();
    public abstract ProjectConfigurationPage getProjectConfigurationPage();
    public abstract ProjectRenamingPage getProjectRenamingPage();

    public ProjectStatusPage clickStatusInSideMenu() {
        statusMenuItem.click();

        return getProjectStatusPage().waitUntilPageLoadJS();
    }

    public ProjectConfigurationPage clickSidebarConfigure() {
        configureMenuItem.click();

        return getProjectConfigurationPage().waitUntilPageLoadJS();
    }

    public ProjectRenamingPage clickSidebarRename() {
        renameMenuItem.click();

        return getProjectRenamingPage().waitUntilPageLoadJS();
    }

    public ProjectStatusPage clickSidebarDelete() {
        deleteMenuItem.click();

        return getProjectStatusPage().waitUntilPageLoadJS();
    }
}
