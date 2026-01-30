package school.redrover.ui;

import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.ui.page.HomePage;


public class PipelineBuildTriggersTest extends BaseTest {
    private static final String pipelineName = "pipeline_name";

    @Test
    public void testSelectTriggers() {
        WebElement[] triggersSelected =
                new HomePage(getDriver()).clickSidebarNewItem()
                .sendName(pipelineName)
                .selectPipelineAndSubmit()
                .selectAllTriggers();

        for (WebElement trigger : triggersSelected){
            Assert.assertTrue(trigger.isEnabled());
        }
        Assert.assertNotNull(triggersSelected);
    }
}
