package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.SidebarBuildHistoryTrait;
import school.redrover.trait.project_sidebar.SidebarCredentialsTrait;


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
