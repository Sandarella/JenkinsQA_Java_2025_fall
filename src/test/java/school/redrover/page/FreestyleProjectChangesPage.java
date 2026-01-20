package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class FreestyleProjectChangesPage extends BasePage<FreestyleProjectChangesPage> {

    public FreestyleProjectChangesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectChangesPage getPage() {
        return this;
    }
}
