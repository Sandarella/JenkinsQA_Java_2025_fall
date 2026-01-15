package school.redrover.trait.project_sidebar;

import org.openqa.selenium.By;

import school.redrover.common.BasePage;
import school.redrover.trait.BaseTrait;


public interface SidebarWorkspaceTrait<ProjectWorkspacePage extends BasePage<ProjectWorkspacePage>> extends BaseTrait {

    ProjectWorkspacePage getProjectWorkspacePage();

    default ProjectWorkspacePage clickSidebarWorkspace() {
        getDriver().findElement(By.xpath("//a[contains(., 'Workspace')]")).click();

        return getProjectWorkspacePage().waitUntilPageLoadJS();
    }
}
