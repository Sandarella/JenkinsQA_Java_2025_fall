package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class PipelineProjectHistoryPage extends BasePage<PipelineProjectHistoryPage> {

    public PipelineProjectHistoryPage(WebDriver driver) { super(driver); }

    @Override
    public PipelineProjectHistoryPage getPage() {
        return this;
    }

    @Override
    public PipelineProjectHistoryPage waitUntilPageLoad() {
        return null;
    }

    public PipelineProjectHistoryConsolePage clickConsoleOutput() {
        getDriver().findElement(By.xpath("//a[substring-before(@href, 'console')]"))
                .click();

        return new PipelineProjectHistoryConsolePage(getDriver());
    }
}
