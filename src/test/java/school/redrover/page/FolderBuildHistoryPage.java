package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class FolderBuildHistoryPage extends BasePage<FolderBuildHistoryPage> {

    public FolderBuildHistoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FolderBuildHistoryPage getPage() {
        return this;
    }
}
