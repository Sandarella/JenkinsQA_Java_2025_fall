package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class PipelineProjectRenamingPage extends BasePage<PipelineProjectRenamingPage> {

    public PipelineProjectRenamingPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectRenamingPage getPage() {
        return this;
    }

    @Override
    public PipelineProjectRenamingPage waitUntilPageLoad() {
        return null;
    }
}
