package school.redrover.ui.trait.project_sidebar;

import org.openqa.selenium.By;

import school.redrover.common.BasePage;
import school.redrover.ui.trait.BaseTrait;


public interface SidebarBuildHistoryTrait<ProjectBuildHistoryPage extends BasePage<ProjectBuildHistoryPage>>
        extends BaseTrait {

    ProjectBuildHistoryPage getProjectBuildHistoryPage();

    default
    ProjectBuildHistoryPage clickSidebarBuildHistory() {
        getDriver().findElement(By.xpath("//a[contains(., 'Build History')]")).click();

        return getProjectBuildHistoryPage().waitUntilPageLoadJS();
    }
}
