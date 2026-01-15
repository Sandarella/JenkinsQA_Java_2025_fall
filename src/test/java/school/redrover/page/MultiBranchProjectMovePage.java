package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class MultiBranchProjectMovePage extends BasePage<MultiBranchProjectMovePage> {

    public MultiBranchProjectMovePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiBranchProjectMovePage getPage() {
        return this;
    }

    @Override
    public MultiBranchProjectMovePage waitUntilPageLoad() {
        return null;
    }
}
