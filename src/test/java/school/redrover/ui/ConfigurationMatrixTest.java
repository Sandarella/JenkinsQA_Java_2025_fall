package school.redrover.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.ui.page.FreestyleProjectConfigurationPage;
import school.redrover.ui.page.HomePage;

import java.util.List;


public class ConfigurationMatrixTest extends BaseTest {

    private static final String PROJECT_NAME = "Multiconfiguration project name";

    @Test
    public void testSectionDisplayed() {
        String configurationMatrixText = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(PROJECT_NAME)
                .selectMultiConfigurationProjectAndSubmit()
                .getConfigurationMatrixText();

        Assert.assertEquals(configurationMatrixText, "Configuration Matrix",
                "Configuration Matrix header is not visible");
    }

    @Test
    public void testAddAxisMenuOnlyUserDefined() {
        List<String> configurationMatrixAddAxisList = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(PROJECT_NAME)
                .selectMultiConfigurationProjectAndSubmit()
                .clickAddAxisButton()
                .getAddAxisDropdownItemTextList();

        Assert.assertEquals(configurationMatrixAddAxisList.size(), 1, "Dropdown should contain exactly one item");
        Assert.assertEquals(configurationMatrixAddAxisList.get(0).trim(), "User-defined Axis",
                "The only option must be 'User-defined Axis'");
    }

    @Test
    public void testSetUpEnvironmentAfterRefresh() {
        new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectFreestyleProjectAndSubmit()
                .clickEnvironmentMenuOption();

        String urlBeforeRefresh = new FreestyleProjectConfigurationPage(getDriver())
                .getConfigUrl();

        String urlAfterRefresh = new FreestyleProjectConfigurationPage(getDriver())
                .refreshPage()
                .getConfigUrl();

        Assert.assertEquals(urlAfterRefresh, urlBeforeRefresh);
    }
}
