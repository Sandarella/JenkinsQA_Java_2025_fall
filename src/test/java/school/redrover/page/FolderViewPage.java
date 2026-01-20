package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class FolderViewPage extends BasePage<FolderViewPage> {

    public FolderViewPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FolderViewPage getPage() {
        return this;
    }
}
