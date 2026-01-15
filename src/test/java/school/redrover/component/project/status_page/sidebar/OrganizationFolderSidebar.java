package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.SidebarBuildHistoryTrait;
import school.redrover.trait.project_sidebar.SidebarCredentialsTrait;
import school.redrover.trait.project_sidebar.SidebarMoveTrait;


public class OrganizationFolderSidebar extends BaseSidebarComponent<OrganizationFolderSidebar, OrganizationFolderStatusPage,
        OrganizationFolderConfigurationPage, OrganizationFolderRenamingPage>
        implements SidebarBuildHistoryTrait<OrganizationFolderBuildHistoryPage>, SidebarMoveTrait<OrganizationFolderMovePage>,
        SidebarCredentialsTrait<OrganizationFolderCredentialsPage> {

    public OrganizationFolderSidebar(WebDriver driver) {
        super(driver);
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
    public OrganizationFolderRenamingPage getProjectRenamingPage() {
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

    @Override
    public OrganizationFolderMovePage getProjectMovePage() {
        return null;
    }
}
