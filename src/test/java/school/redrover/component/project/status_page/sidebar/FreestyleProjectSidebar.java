package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.*;


public class FreestyleProjectSidebar extends BaseSidebarComponent<
        FreestyleProjectSidebar, FreestyleProjectStatusPage, FreestyleProjectConfigurationPage>
        implements SidebarChangesTrait<FreestyleProjectChangesPage>, SidebarWorkspaceTrait<FreestyleProjectWorkspacePage>,
        SidebarBuildNowTrait<FreestyleProjectStatusPage>, SidebarMoveTrait<FreestyleProjectMovePage>, SidebarCredentialsTrait<FreestyleProjectCredentialsPage> {

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
    public FreestyleProjectMovePage getProjectMovePage() {
        return new FreestyleProjectMovePage(getDriver());
    }

    @Override
    public FreestyleProjectWorkspacePage getProjectWorkspacePage() {
        return new FreestyleProjectWorkspacePage(getDriver());
    }
}
