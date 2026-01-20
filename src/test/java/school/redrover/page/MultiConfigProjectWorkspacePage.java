package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class MultiConfigProjectWorkspacePage extends BasePage<MultiConfigProjectWorkspacePage> {

    public MultiConfigProjectWorkspacePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigProjectWorkspacePage getPage() {
        return this;
    }
}
