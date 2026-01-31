package school.redrover.ui.trait.project_sidebar;

import org.openqa.selenium.By;

import school.redrover.common.BasePage;
import school.redrover.ui.trait.BaseTrait;


public interface SidebarBuildNowTrait<ProjectStatusPage extends BasePage<ProjectStatusPage>>
        extends BaseTrait {

    ProjectStatusPage getProjectStatusPage();

    default ProjectStatusPage clickSidebarBuildNow() {
        getDriver().findElement(By.xpath("//a[contains(., 'Build Now')]")).click();

        return getProjectStatusPage().waitUntilPageLoadJS();
    }
}
