package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class MultiBranchProjectBuildHistoryPage extends BasePage<MultiBranchProjectBuildHistoryPage> {

    public MultiBranchProjectBuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiBranchProjectBuildHistoryPage getPage() {
        return this;
    }

}
