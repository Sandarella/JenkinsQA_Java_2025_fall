package school.redrover.page;

import org.openqa.selenium.WebDriver;
import school.redrover.common.BasePage;


public class PipelineProjectMovePage extends BasePage<PipelineProjectMovePage> {

    public PipelineProjectMovePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectMovePage getPage() {
        return this;
    }

}
