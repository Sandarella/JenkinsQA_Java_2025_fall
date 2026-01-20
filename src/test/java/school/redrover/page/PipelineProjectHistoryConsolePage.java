package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;


public class PipelineProjectHistoryConsolePage extends BasePage<PipelineProjectHistoryConsolePage> {

    @FindBy(id = "out")
    public WebElement consoleOutput;

    public PipelineProjectHistoryConsolePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectHistoryConsolePage getPage() {
        return this;
    }

    public String getConsoleOutputText() {
        return consoleOutput.getText();
    }
}
