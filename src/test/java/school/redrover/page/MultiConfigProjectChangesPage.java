package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class MultiConfigProjectChangesPage extends BasePage<MultiConfigProjectChangesPage> {

    public MultiConfigProjectChangesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiConfigProjectChangesPage getPage() {
        return this;
    }
   }
