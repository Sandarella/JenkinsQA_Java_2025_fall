package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.component.BaseComponent;
import school.redrover.page.BaseProjectConfigurationPage;
import school.redrover.page.BaseProjectStatusPage;
import school.redrover.page.ProjectMovePage;
import school.redrover.page.ProjectRenamingPage;

import java.util.function.Function;


public abstract class BaseSidebarComponent<
        SidebarComponent extends BaseSidebarComponent<?, ?, ?>,
        ProjectStatusPage extends BaseProjectStatusPage<ProjectStatusPage, ?>,
        ProjectConfigurationPage extends BaseProjectConfigurationPage<ProjectConfigurationPage, ?>>
        extends BaseComponent<SidebarComponent> {

    @FindBy(xpath = "//a[contains(., 'Status')]")
    private WebElement statusMenuItem;

    @FindBy(xpath = "//a[contains(@href, '/configure')]")
    private WebElement configureMenuItem;

    @FindBy(xpath = "//a[contains(., 'Delete')]")
    private WebElement deleteMenuItem;

    @FindBy(xpath = "//a[contains(., 'Move')]")
    private WebElement moveMenuItem;

    @FindBy(xpath = "//a[contains(., 'Rename')]")
    private WebElement renameMenuItem;


    private final Function<WebDriver, ProjectStatusPage> projectStatusPageFactory;

    public BaseSidebarComponent(WebDriver driver, Function<WebDriver, ProjectStatusPage> projectStatusPageFactory) {
        super(driver);
        this.projectStatusPageFactory = projectStatusPageFactory;
    }

    public abstract ProjectStatusPage getProjectStatusPage();

    public abstract ProjectConfigurationPage getProjectConfigurationPage();

    public ProjectStatusPage clickSidebarStatus() {
        statusMenuItem.click();

        return getProjectStatusPage().waitUntilPageLoadJS();
    }

    public ProjectConfigurationPage clickSidebarConfigure() {
        configureMenuItem.click();

        return getProjectConfigurationPage().waitUntilPageLoadJS();
    }

    public ProjectStatusPage clickSidebarDelete() {
        deleteMenuItem.click();

        return getProjectStatusPage().waitUntilPageLoadJS();
    }

    public ProjectMovePage<ProjectStatusPage> clickSidebarMove() {
        moveMenuItem.click();

        return new ProjectMovePage<>(getDriver(), projectStatusPageFactory).waitUntilPageLoadJS();
    }

    public ProjectRenamingPage<ProjectStatusPage> clickSidebarRename() {
        renameMenuItem.click();

        return new ProjectRenamingPage<>(getDriver(), projectStatusPageFactory);
    }


}
