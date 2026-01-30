package school.redrover.ui.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.ui.page.PipelineProjectChangesPage;
import school.redrover.ui.page.PipelineProjectConfigurationPage;
import school.redrover.ui.page.PipelineProjectCredentialsPage;
import school.redrover.ui.page.PipelineProjectStatusPage;
import school.redrover.ui.trait.project_sidebar.SidebarBuildNowTrait;
import school.redrover.ui.trait.project_sidebar.SidebarChangesTrait;
import school.redrover.ui.trait.project_sidebar.SidebarCredentialsTrait;


public class PipelineProjectSidebar extends BaseSidebarComponent<PipelineProjectSidebar, PipelineProjectStatusPage,
        PipelineProjectConfigurationPage>
        implements SidebarChangesTrait<PipelineProjectChangesPage>,
        SidebarBuildNowTrait<PipelineProjectStatusPage>,
        SidebarCredentialsTrait<PipelineProjectCredentialsPage> {

    public PipelineProjectSidebar(WebDriver driver) {
        super(driver, PipelineProjectStatusPage::new);
    }

    @Override
    public PipelineProjectSidebar waitUntilComponentLoad() {
        return null;
    }

    @Override
    public PipelineProjectStatusPage getProjectStatusPage() {
        return new PipelineProjectStatusPage(getDriver());
    }

    @Override
    public PipelineProjectConfigurationPage getProjectConfigurationPage() {
        return new PipelineProjectConfigurationPage(getDriver());
    }

    @Override
    public PipelineProjectChangesPage getProjectChangesPage() {
        return new PipelineProjectChangesPage(getDriver());
    }

    @Override
    public PipelineProjectCredentialsPage getProjectCredentialsPage() {
        return new PipelineProjectCredentialsPage(getDriver());
    }

}
