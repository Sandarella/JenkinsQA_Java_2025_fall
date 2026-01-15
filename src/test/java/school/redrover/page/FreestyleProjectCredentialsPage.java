package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class FreestyleProjectCredentialsPage extends BasePage<FreestyleProjectCredentialsPage> {

    public FreestyleProjectCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectCredentialsPage getPage() {
        return this;
    }

    @Override
    public FreestyleProjectCredentialsPage waitUntilPageLoad() {
        return null;
    }
}
