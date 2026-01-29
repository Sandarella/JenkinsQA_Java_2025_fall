package school.redrover.component.project.status_page.sidebar;

import org.openqa.selenium.WebDriver;
import school.redrover.page.*;
import school.redrover.trait.project_sidebar.SidebarBuildNowTrait;
import school.redrover.trait.project_sidebar.SidebarChangesTrait;
import school.redrover.trait.project_sidebar.SidebarCredentialsTrait;
import school.redrover.trait.project_sidebar.SidebarMoveTrait;


public class PipelineProjectSidebar extends BaseSidebarComponent<PipelineProjectSidebar, PipelineProjectStatusPage,
        PipelineProjectConfigurationPage>
        implements SidebarChangesTrait<PipelineProjectChangesPage>, SidebarBuildNowTrait<PipelineProjectStatusPage>,
        SidebarMoveTrait<PipelineProjectMovePage>, SidebarCredentialsTrait<PipelineProjectCredentialsPage> {

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

    @Override
    public PipelineProjectMovePage getProjectMovePage() {
        return new PipelineProjectMovePage(getDriver());
    }
}
