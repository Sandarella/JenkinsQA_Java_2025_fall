package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class MultiConfigProjectCredentialsPage extends BasePage<MultiConfigProjectCredentialsPage> {

    public MultiConfigProjectCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigProjectCredentialsPage getPage() {
        return this;
    }

}
