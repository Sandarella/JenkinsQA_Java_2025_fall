package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.*;


public class MultiConfigProjectSidebar extends BaseSidebarComponent<MultiConfigProjectSidebar, MultiConfigProjectStatusPage,
        MultiConfigProjectConfigurationPage>
        implements SidebarChangesTrait<MultiConfigProjectChangesPage>, SidebarWorkspaceTrait<MultiConfigProjectWorkspacePage>,
        SidebarBuildNowTrait<MultiConfigProjectStatusPage>, SidebarMoveTrait<MultiConfigProjectMovePage>,
        SidebarCredentialsTrait<MultiConfigProjectCredentialsPage> {

    public MultiConfigProjectSidebar(WebDriver driver) {
        super(driver, MultiConfigProjectStatusPage::new);
    }

    @Override
    public MultiConfigProjectSidebar waitUntilComponentLoad() {
        return this;
    }

    @Override
    public MultiConfigProjectStatusPage getProjectStatusPage() {
        return new MultiConfigProjectStatusPage(getDriver());
    }

    @Override
    public MultiConfigProjectConfigurationPage getProjectConfigurationPage() {
        return new MultiConfigProjectConfigurationPage(getDriver());
    }

    @Override
    public MultiConfigProjectChangesPage getProjectChangesPage() {
        return new MultiConfigProjectChangesPage(getDriver());
    }

    @Override
    public MultiConfigProjectCredentialsPage getProjectCredentialsPage() {
        return new MultiConfigProjectCredentialsPage(getDriver());
    }

    @Override
    public MultiConfigProjectMovePage getProjectMovePage() {
        return new MultiConfigProjectMovePage(getDriver());
    }

    @Override
    public MultiConfigProjectWorkspacePage getProjectWorkspacePage() {
        return new MultiConfigProjectWorkspacePage(getDriver());
    }
}
