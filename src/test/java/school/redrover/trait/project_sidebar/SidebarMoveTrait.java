package school.redrover.trait.project_sidebar;

import org.openqa.selenium.By;

import school.redrover.common.BasePage;
import school.redrover.trait.BaseTrait;


public interface SidebarMoveTrait<ProjectMovePage extends BasePage<ProjectMovePage>> extends BaseTrait {

    ProjectMovePage getProjectMovePage();

    default ProjectMovePage clickSidebarMove() {
        getDriver().findElement(By.xpath("//a[contains(., 'Move')]")).click();

        return getProjectMovePage().waitUntilPageLoadJS();
    }
}
