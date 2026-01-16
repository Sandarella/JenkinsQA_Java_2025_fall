package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.List;

public class JenkinsManagementTest extends BaseTest {
    private final String TITLE_TEXT = "Dashboard - Jenkins";
    private static final String SETTING_TITLE = "System";

    public List<String> checking = List.of("System Configuration",
            "Security",
            "Status Information",
            "Troubleshooting",
            "Tools and Actions"
    );


    @DataProvider
    public Object[][] systemConfigurationItems() {
        return new String[][]{
                {"System"},
                {"Tools"},
                {"Plugins"},
                {"Nodes"},
                {"Clouds"},
                {"Appearance"}
        };
    }

    @Test
    public void testDeferredWipeoutSettingIsSaved() {
        boolean globalPropertiesDisableWipeoutCheckbox = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .checkGlobalPropertiesDisableWipeoutCheckbox()
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .isGlobalPropertiesDisableWipeoutCheckboxSelected();

        Assert.assertTrue(globalPropertiesDisableWipeoutCheckbox);
    }

    @Test
    public void testDeferredWipeoutTooltip() {
        final String expectedTooltipText = "Help for feature: Disable deferred wipeout on this node";

        String actualTooltipText = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .getGlobalPropertiesDisableWipeoutCheckboxTooltipOnHoverText();

        Assert.assertEquals(actualTooltipText, expectedTooltipText, "Unexpected tooltip");
    }

    @Test
    public void testDeferredWipeoutHintText() {
        final String expectedHintText =
                "During the workspace cleanup disable improved deferred wipeout method. " +
                        "By default deferred wipeout is used if desired.";

        String actualHintText = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .clickCheckboxTooltip()
                .getHintText();

        Assert.assertEquals(actualHintText, expectedHintText, "Unexpected tooltip");
    }

    @Test
    public void testCheckAccessDashboardFromLogo() {
        String title = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .gotoHomePage()
                .getTitle();

        Assert.assertEquals(title, TITLE_TEXT);
    }

    @Test
    public void testSearchResultsList() {
        List<String> searchResults = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .sendTitle(SETTING_TITLE)
                .getSearchResults();

        Assert.assertEquals(searchResults, List.of("System", "System Information", "System Log"));
    }

    @Test(dataProvider = "systemConfigurationItems")
    public void testSearchDropdownItemsSystemConfiguration(String itemName) {
        String dropdownResult = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .sendTitle(itemName)
                .getSearchResultName();

        Assert.assertEquals(dropdownResult, itemName, "Названия пунктов настроек не совпадают");
    }

    @Test
    public void testSearchAndOpenSetting() {
        String searchHeading = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .sendTitle(SETTING_TITLE)
                .clickSearchResult()
                .getHeaderText();

        Assert.assertEquals(searchHeading, "System");
    }

    @Test
    public void checkManageJenkins() {
            List<String> checks = new HomePage(getDriver())
                    .clickManageJenkinsGear()
                    .checkSystemConfiguration();

            Assert.assertEquals(checks, checking);
        }
}
