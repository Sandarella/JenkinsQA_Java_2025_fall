package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.*;


public class MultiConfigurationProjectTest extends BaseTest {
    private static final String PROJECT_NAME = "ProjectName";
    private static final String RENAMED_PROJECT = "Renamed multiconfiguration project";
    private static final String PROJECT_DESCRIPTION = "Project description...";

    @DataProvider(name = "validQuietPeriodValues")
    public Object[][] validQuietPeriodValues() {
        return new String[][]{
                {"0"},
                {"1"},
                {"30"},
                {"60"},
                {"300"},
                {"3600"},
                {"86400"},
                {""}
        };
    }

    @Test
    public void testCreateProject() {
        String actualProjectName = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(PROJECT_NAME)
                .selectMultiConfigurationProjectAndSubmit()
                .clickSave()
                .getHeader()
                .getText();

        Assert.assertEquals(actualProjectName, PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testAddDescriptionToProject() {
        String description = new HomePage(getDriver())
                .openProject(PROJECT_NAME, new MultiConfigProjectStatusPage(getDriver()))
                .waitUntilPageLoad()
                .clearDescriptionField()
                .sendDescription(PROJECT_DESCRIPTION)
                .getDescription();

        Assert.assertEquals(description, PROJECT_DESCRIPTION);
    }

    @Test
    public void testRenameViaSidebar() {
        String actualProjectName = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(PROJECT_NAME)
                .selectMultiConfigurationProjectAndSubmit()
                .clickSave()
                .getSidebarComponent()
                .clickSidebarRename()
                .clearNameField()
                .sendNewProjectName(RENAMED_PROJECT)
                .getHeader()
                .getText();

        Assert.assertEquals(actualProjectName, RENAMED_PROJECT);
    }

    @Test
    public void testRenameViaDashboardDropdownMenu() {
        String actualProjectName = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(PROJECT_NAME)
                .selectMultiConfigurationProjectAndSubmit()
                .clickSave()
                .clickRenameViaDashboardDropDownMenu()
                .clearNameField()
                .sendNewProjectName(RENAMED_PROJECT)
                .getHeader()
                .getText();

        Assert.assertEquals(actualProjectName, RENAMED_PROJECT);
    }

    @Test(dataProvider = "validQuietPeriodValues")
    public void testValidQuietPeriodValues(String seconds) {
        String configPage = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(PROJECT_NAME)
                .selectMultiConfigurationProjectAndSubmit()
                .clickAdvancedDropdownButton()
                .clickQuietPeriodCheckbox()
                .setQuietPeriodInput(seconds)
                .clickSave()
                .getHeader()
                .getText();

        Assert.assertEquals(configPage, PROJECT_NAME);
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testDisableProject() {
        boolean isProjectEnabled = new HomePage(getDriver())
                .clickProject(PROJECT_NAME)
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickProjectToggle()
                .isProjectToggleSelected();

        Assert.assertFalse(isProjectEnabled, "'Disabled' must be shown");
    }

    @Test(dependsOnMethods = "testCreateProject")
    public void testIconWhenDisable() {
        boolean disabledIconDisplayed = new HomePage(getDriver())
                .clickProject(PROJECT_NAME)
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickProjectToggle()
                .clickSave()
                .gotoHomePage()
                .isDisabledIconDisplayed();

        Assert.assertTrue(disabledIconDisplayed);
    }

    @Test(dependsOnMethods = {"testIconWhenDisable", "testCreateProject"})
    public void testWarningWhenDisable() {
        boolean hasWarning = new HomePage(getDriver())
                .clickProject(PROJECT_NAME)
                .isWarningVisible();

        Assert.assertTrue(hasWarning, "Warning text should contain 'This project is currently disabled'");
    }
}
