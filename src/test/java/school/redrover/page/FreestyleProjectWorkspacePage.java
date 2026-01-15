package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class FreestyleProjectWorkspacePage extends BasePage<FreestyleProjectWorkspacePage> {

    public FreestyleProjectWorkspacePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectWorkspacePage getPage() {
        return this;
    }

    @Override
    public FreestyleProjectWorkspacePage waitUntilPageLoad() {
        return null;
    }
}
