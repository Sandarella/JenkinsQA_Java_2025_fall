package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class PipelineProjectBuildNowPage extends BasePage<PipelineProjectBuildNowPage> {

    public PipelineProjectBuildNowPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectBuildNowPage getPage() {
        return this;
    }

}
