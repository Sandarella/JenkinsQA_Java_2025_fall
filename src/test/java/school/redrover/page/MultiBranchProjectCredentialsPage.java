package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class MultiBranchProjectCredentialsPage extends BasePage<MultiBranchProjectCredentialsPage> {

    public MultiBranchProjectCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MultiBranchProjectCredentialsPage getPage() {
        return this;
    }

}
