package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class FreestyleProjectWorkspacePage extends BasePage<FreestyleProjectWorkspacePage> {

    @FindBy(xpath = "//span[text()='Wipe Out Current Workspace']")
    private WebElement wipeOutWorkspaceLink;

    public FreestyleProjectWorkspacePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public FreestyleProjectWorkspacePage getPage() {
        return this;
    }
}
