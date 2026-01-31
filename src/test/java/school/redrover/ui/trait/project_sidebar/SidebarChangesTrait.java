package school.redrover.ui.trait.project_sidebar;

import org.openqa.selenium.By;

import school.redrover.common.BasePage;
import school.redrover.ui.trait.BaseTrait;


public interface SidebarChangesTrait<ProjectChangesPage extends BasePage<ProjectChangesPage>> extends BaseTrait {

    ProjectChangesPage getProjectChangesPage();

    default ProjectChangesPage clickSidebarChanges() {
        getDriver().findElement(By.xpath("//a[contains(., 'Changes')]")).click();

        return getProjectChangesPage().waitUntilPageLoadJS();
    }
}
