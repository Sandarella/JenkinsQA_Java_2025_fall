package school.redrover.ui.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.ui.page.*;
import school.redrover.ui.trait.project_sidebar.*;


public class FreestyleProjectSidebar extends BaseSidebarComponent<
        FreestyleProjectSidebar, FreestyleProjectStatusPage, FreestyleProjectConfigurationPage>
        implements SidebarChangesTrait<FreestyleProjectChangesPage>, SidebarWorkspaceTrait<FreestyleProjectWorkspacePage>,
        SidebarBuildNowTrait<FreestyleProjectStatusPage>, SidebarCredentialsTrait<FreestyleProjectCredentialsPage> {

    public FreestyleProjectSidebar(WebDriver driver) {
        super(driver, FreestyleProjectStatusPage::new);
    }

    @Override
    public FreestyleProjectSidebar waitUntilComponentLoad() {
        return this;
    }

    @Override
    public FreestyleProjectStatusPage getProjectStatusPage() {
        return new FreestyleProjectStatusPage(getDriver());
    }

    @Override
    public FreestyleProjectConfigurationPage getProjectConfigurationPage() {
        return new FreestyleProjectConfigurationPage(getDriver());
    }

    @Override
    public FreestyleProjectChangesPage getProjectChangesPage() {
        return new FreestyleProjectChangesPage(getDriver());
    }

    @Override
    public FreestyleProjectCredentialsPage getProjectCredentialsPage() {
        return new FreestyleProjectCredentialsPage(getDriver());
    }

    @Override
    public FreestyleProjectWorkspacePage getProjectWorkspacePage() {
        return new FreestyleProjectWorkspacePage(getDriver());
    }
}
