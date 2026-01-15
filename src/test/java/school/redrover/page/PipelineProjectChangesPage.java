package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class PipelineProjectChangesPage extends BasePage<PipelineProjectChangesPage> {

    public PipelineProjectChangesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectChangesPage getPage() {
        return this;
    }

    @Override
    public PipelineProjectChangesPage waitUntilPageLoad() {
        return null;
    }
}
