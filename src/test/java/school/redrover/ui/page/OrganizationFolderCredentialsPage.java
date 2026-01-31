package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class OrganizationFolderCredentialsPage extends BasePage<OrganizationFolderCredentialsPage> {

    public OrganizationFolderCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public OrganizationFolderCredentialsPage getPage() {
        return this;
    }
}
