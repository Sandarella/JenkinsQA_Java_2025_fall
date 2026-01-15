package school.redrover.trait.project_sidebar;

import org.openqa.selenium.By;

import school.redrover.common.BasePage;
import school.redrover.trait.BaseTrait;


public interface SidebarCredentialsTrait<ProjectCredentialsPage extends BasePage<ProjectCredentialsPage>> extends BaseTrait {

    ProjectCredentialsPage getProjectCredentialsPage();

    default
    ProjectCredentialsPage clickSidebarCredentials() {
        getDriver().findElement(By.xpath("//a[contains(., 'Credentials')]")).click();

        return getProjectCredentialsPage().waitUntilPageLoadJS();
    }
}
