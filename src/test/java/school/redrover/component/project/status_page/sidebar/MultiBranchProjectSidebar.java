package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.SidebarBuildHistoryTrait;
import school.redrover.trait.project_sidebar.SidebarCredentialsTrait;
import school.redrover.trait.project_sidebar.SidebarMoveTrait;


public class MultiBranchProjectSidebar extends BaseSidebarComponent<
        MultiBranchProjectSidebar, MultibranchProjectStatusPage, MultibranchProjectConfigurationPage, MultibranchProjectRenamingPage>
        implements SidebarBuildHistoryTrait<MultiBranchProjectBuildHistoryPage>, SidebarMoveTrait<MultiBranchProjectMovePage>,
        SidebarCredentialsTrait<MultiBranchProjectCredentialsPage> {

    public MultiBranchProjectSidebar(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiBranchProjectSidebar waitUntilComponentLoad() {
        return null;
    }

    @Override
    public MultibranchProjectStatusPage getProjectStatusPage() {
        return new MultibranchProjectStatusPage(getDriver());
    }

    @Override
    public MultibranchProjectConfigurationPage getProjectConfigurationPage() {
        return new MultibranchProjectConfigurationPage(getDriver());
    }

    @Override
    public MultibranchProjectRenamingPage getProjectRenamingPage() {
        return new MultibranchProjectRenamingPage(getDriver());
    }

    @Override
    public MultiBranchProjectBuildHistoryPage getProjectBuildHistoryPage() {
        return new MultiBranchProjectBuildHistoryPage(getDriver());
    }

    @Override
    public MultiBranchProjectCredentialsPage getProjectCredentialsPage() {
        return new MultiBranchProjectCredentialsPage(getDriver());
    }

    @Override
    public MultiBranchProjectMovePage getProjectMovePage() {
        return new MultiBranchProjectMovePage(getDriver());
    }
}
