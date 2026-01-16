package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.PipelineProjectConfigurationPage;
import school.redrover.page.HomePage;
import school.redrover.page.PipelineProjectStatusPage;

import java.util.List;


public class PipelineConfigurationTest extends BaseTest {

    private static final String PIPELINE_NAME = "PipelineName";
    private static final String PIPELINE_NAME_FOR_CHANGE = "pipeline_01";

    @Test
    public void testEnableProject() {

        String toggleLabelText = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PIPELINE_NAME)
                .selectPipelineAndSubmit()
                .getToggleCheckedLabelText();

        Assert.assertEquals(toggleLabelText, "Enabled");
    }

    @Test(dependsOnMethods = "testEnableProject")
    public void testDisableProject() {

        String toggleLabelText = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickToggle()
                .getToggleUncheckedLabelText();

        Assert.assertEquals(toggleLabelText, "Disabled");
    }

    @Test(dependsOnMethods = "testDisableProject")
    public void testActivityStatusProject() {

        String actualProjectStatus = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickToggle()
                .clickSave()
                .gotoHomePage()
                .getProjectStatus(PIPELINE_NAME);

        Assert.assertEquals(actualProjectStatus, "Disabled");
    }

    @Test
    public void testDisablePipelineProject() {

        String warningMessage = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PIPELINE_NAME)
                .selectPipelineAndSubmit()
                .clickToggle()
                .clickSave()
                .getWarningMessage();

        Assert.assertEquals(warningMessage, "This project is currently disabled\n" +
                "Enable");
    }

    // Advanced Section
    @Test
    public void testNavigationToAdvancedByScrollingDown() {
        String actualAdvancedSectionTitle = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PIPELINE_NAME)
                .selectPipelineAndSubmit()
                .scrollDownToAdvancedSection()
                .getAdvancedTitleText();

        Assert.assertEquals(actualAdvancedSectionTitle, "Advanced");
    }

    @Test(dependsOnMethods = "testNavigationToAdvancedByScrollingDown")
    public void testNavigationToAdvancedBySideMenu() {
        String actualAdvancedSectionTitle = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickAdvancedLinkInSideMenu()
                .getAdvancedTitleText();

        Assert.assertEquals(actualAdvancedSectionTitle, "Advanced");
    }

    @Test(dependsOnMethods = "testNavigationToAdvancedByScrollingDown")
    public void testAdvancedSectionQuietPeriodElements() {
        String actualQuietPeriodLabel = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickAdvancedButton()
                .getQuietPeriodLabelText();

        Assert.assertEquals(actualQuietPeriodLabel, "Quiet period");
        Assert.assertFalse(new PipelineProjectConfigurationPage(getDriver())
                .quietPeriodCheckboxIsSelected(), "Default Checkbox should not be selected");
    }

    @Test(dependsOnMethods = "testNavigationToAdvancedByScrollingDown")
    public void testAdvancedSectionDisplayNameFieldElements() {
        String actualDisplayNameLabel = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickAdvancedButton()
                .getDisplayNameLabelText();

        Assert.assertEquals(actualDisplayNameLabel, "Display Name");
        Assert.assertTrue(new PipelineProjectConfigurationPage(getDriver()).displayNameValueIsEmpty(),
                "Default Display Name field should be empty");
    }

    @Test(dependsOnMethods = "testAdvancedSectionQuietPeriodElements")
    public void testAdvancedSectionQuietPeriodElementsAfterSelecting() {
        String actualNumberOfSecondsLabel = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickAdvancedButton()
                .clickQuitePeriod()
                .getNumberOfSecondsLabelText();

        Assert.assertEquals(actualNumberOfSecondsLabel, "Number of seconds");
        Assert.assertTrue(new PipelineProjectConfigurationPage(getDriver())
                .quietPeriodCheckboxIsSelected(), "Checkbox should be selected");
        Assert.assertTrue(new PipelineProjectConfigurationPage(getDriver())
                .isNumberOfSecondsInputDisplayed());
    }

    @Test
    public void testAdvancedSectionSendDisplayName() {
        final String displayName = "PL_01";

        String actualDisplayNameInStatus = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PIPELINE_NAME_FOR_CHANGE)
                .selectPipelineAndSubmit()
                .clickAdvancedButton()
                .sendDisplayName(displayName)
                .clickSave()
                .getStatusDisplayNameText();

        Assert.assertEquals(actualDisplayNameInStatus, displayName);
        Assert.assertEquals(new PipelineProjectStatusPage(getDriver()).
                getDisplayNameInBreadcrumbBar(displayName), displayName);

        List<String> actualProjectList = new PipelineProjectStatusPage(getDriver())
                .gotoHomePage()
                .getProjectsNamesList();
        Assert.assertTrue(actualProjectList.contains(displayName),
                String.format("Project with Display Name '%s' not found in Project List", displayName));
    }

    @Test(dependsOnMethods = "testNavigationToAdvancedByScrollingDown")
    public void testAdvancedSectionVerifyTooltips() {
        final List<String> expectedTooltipList = List.of(
                "Help for feature: Quiet period",
                "Help for feature: Display Name");

        List<String> actualTooltipList = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickAdvancedButton()
                .getTooltipList();

        Assert.assertEquals(actualTooltipList, expectedTooltipList);
    }

    @Test(dependsOnMethods = "testAdvancedSectionVerifyTooltips")
    public void testAdvancedSectionHelpAreaIsDisplayed() {
        boolean isHelpElementDisplayed = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineProjectStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .clickAdvancedButton()
                .isHelpElementDisplayed();

        Assert.assertTrue(isHelpElementDisplayed);
    }
}