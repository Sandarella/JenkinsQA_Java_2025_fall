package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class PipelineProjectCredentialsPage extends BasePage<PipelineProjectCredentialsPage> {

    public PipelineProjectCredentialsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectCredentialsPage getPage() {
        return this;
    }
}
