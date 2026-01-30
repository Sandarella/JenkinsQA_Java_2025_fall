package school.redrover.ui.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.ui.page.MultiBranchProjectBuildHistoryPage;
import school.redrover.ui.page.MultiBranchProjectCredentialsPage;
import school.redrover.ui.page.MultibranchProjectConfigurationPage;
import school.redrover.ui.page.MultibranchProjectStatusPage;
import school.redrover.ui.trait.project_sidebar.SidebarBuildHistoryTrait;
import school.redrover.ui.trait.project_sidebar.SidebarCredentialsTrait;


public class MultiBranchProjectSidebar extends BaseSidebarComponent<
        MultiBranchProjectSidebar, MultibranchProjectStatusPage, MultibranchProjectConfigurationPage>
        implements SidebarBuildHistoryTrait<MultiBranchProjectBuildHistoryPage>, SidebarCredentialsTrait<MultiBranchProjectCredentialsPage> {

    public MultiBranchProjectSidebar(WebDriver driver) {
        super(driver, MultibranchProjectStatusPage::new);
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
    public MultiBranchProjectBuildHistoryPage getProjectBuildHistoryPage() {
        return new MultiBranchProjectBuildHistoryPage(getDriver());
    }

    @Override
    public MultiBranchProjectCredentialsPage getProjectCredentialsPage() {
        return new MultiBranchProjectCredentialsPage(getDriver());
    }
}
