package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;


public class PipelineProjectHistoryPage extends BasePage<PipelineProjectHistoryPage> {

    @FindBy(xpath = "//a[substring-before(@href, 'console')]")
    public WebElement consoleOutputButton;

    public PipelineProjectHistoryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectHistoryPage getPage() {
        return this;
    }

    public PipelineProjectHistoryConsolePage clickConsoleOutput() {
        consoleOutputButton.click();

        return new PipelineProjectHistoryConsolePage(getDriver());
    }
}
