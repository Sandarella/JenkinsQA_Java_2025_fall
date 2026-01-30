package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.SidebarBuildHistoryTrait;
import school.redrover.trait.project_sidebar.SidebarCredentialsTrait;


public class OrganizationFolderSidebar extends BaseSidebarComponent<OrganizationFolderSidebar, OrganizationFolderStatusPage,
        OrganizationFolderConfigurationPage>
        implements SidebarBuildHistoryTrait<OrganizationFolderBuildHistoryPage>, SidebarCredentialsTrait<OrganizationFolderCredentialsPage> {

    public OrganizationFolderSidebar(WebDriver driver) {
        super(driver, OrganizationFolderStatusPage::new);
    }

    @Override
    public OrganizationFolderSidebar waitUntilComponentLoad() {
        return null;
    }

    @Override
    public OrganizationFolderStatusPage getProjectStatusPage() {
        return new OrganizationFolderStatusPage(getDriver());
    }

    @Override
    public OrganizationFolderConfigurationPage getProjectConfigurationPage() {
        return null;
    }

    @Override
    public OrganizationFolderBuildHistoryPage getProjectBuildHistoryPage() {
        return null;
    }

    @Override
    public OrganizationFolderCredentialsPage getProjectCredentialsPage() {
        return null;
    }
}
