package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;
import school.redrover.component.project.status_page.sidebar.BaseSidebarComponent;


public abstract class BaseProjectStatusPage<
        ProjectStatusPage extends BaseProjectStatusPage<?, ?>,
        SidebarComponent extends BaseSidebarComponent<SidebarComponent, ?, ?>>
        extends BasePage<ProjectStatusPage> {

    public BaseProjectStatusPage(WebDriver driver) {
        super(driver);
    }

    public abstract SidebarComponent getSidebarComponent();
}
