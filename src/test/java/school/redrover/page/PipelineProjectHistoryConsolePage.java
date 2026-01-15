package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class PipelineProjectHistoryConsolePage extends BasePage<PipelineProjectHistoryConsolePage> {

    public PipelineProjectHistoryConsolePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectHistoryConsolePage getPage() {
        return this;
    }

    @Override
    public PipelineProjectHistoryConsolePage waitUntilPageLoad() {
        return null;
    }

    public String getConsoleOutput() {
        return getDriver().findElement(By.id("out")).getText();
    }
}
