package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import school.redrover.page.PipelineConfigurationPage;
import school.redrover.page.PipelineStatusPage;

import java.util.List;

public class PipelineTest extends BaseTest {

    private static final String PIPELINE_NAME = "PipelineName";

    @DataProvider
    public Object[][] validAliases() {
        return new String[][]{
                {"@yearly"},
                {"@annually"},
                {"@monthly"},
                {"@weekly"},
                {"@daily"},
                {"@midnight"},
                {"@hourly"}
        };
    }

    @DataProvider
    public Object[][] invalidCronSyntaxAndAliases() {
        return new String[][]{
                {"60 * * * *", "60 is an invalid value. Must be within 0 and 59"},
                {"* 25 * * *", "25 is an invalid value. Must be within 0 and 23"},
                {"* * 32 * *", "32 is an invalid value. Must be within 1 and 31"},
                {"* * * 13 * *", "13 is an invalid value. Must be within 1 and 12"},
                {"* * * * 8", "8 is an invalid value. Must be within 0 and 7"},
                {"@", "mismatched input"},
                {"*****", "missing whitespace"}
        };
    }

    @Test
    public void testCreateNewPipeline() {
        createPipeline(PIPELINE_NAME);
        List<String> actualProjectList = new HomePage(getDriver())
                .getProjectsNamesList();

        Assert.assertTrue(actualProjectList.contains(PIPELINE_NAME),
                String.format("Pipeline with name '%s' was not created", PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testSyntaxDocumentationViaSideMenu() {

        final List<String> expectedSideMenu = List.of(
                "Snippet Generator",
                "Declarative Directive Generator",
                "Declarative Online Documentation",
                "Steps Reference",
                "Global Variables Reference",
                "Online Documentation",
                "Examples Reference",
                "IntelliJ IDEA GDSL"
        );

        List<String> actualSideMenu = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickPipelineSyntax()
                .getListOfButtonsInSideMenu();

        Assert.assertEquals(actualSideMenu, expectedSideMenu);
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testSyntaxDocumentationViaDropDownMenu() {

        final List<String> expectedSideMenu = List.of(
                "Snippet Generator",
                "Declarative Directive Generator",
                "Declarative Online Documentation",
                "Steps Reference",
                "Global Variables Reference",
                "Online Documentation",
                "Examples Reference",
                "IntelliJ IDEA GDSL"
        );

        List<String> actualSideMenu = new HomePage(getDriver())
                .openDropdownMenu(PIPELINE_NAME)
                .clickPipelineSyntaxInDropdownMenu()
                .getListOfButtonsInSideMenu();

        Assert.assertEquals(actualSideMenu, expectedSideMenu);
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testCancelDeletePipelineViaSideMenu() {
        List<String> actualProjectList = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickDeletePipeline()
                .cancelDelete()
                .gotoHomePage()
                .getProjectsNamesList();

        Assert.assertTrue(actualProjectList.contains(PIPELINE_NAME));
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testBuildPipeline() {

        String consoleOutput = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickBuildNow()
                .clickBuildHistory()
                .clickConsoleOutput()
                .getConsoleOutput();

        Assert.assertTrue(consoleOutput.contains("Finished:"),
                "Build output should contain 'Finished:'");
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testAddDescription() {
        final String textDescription = "@0*8nFP'cRU0k.|6Gz-wO*se h~OtJ4kz0!)cl0ZAE3vN>q";

        String descriptionText = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickAddDescriptionButton()
                .addDescriptionAndSave(textDescription)
                .getDescription();

        Assert.assertEquals(descriptionText, textDescription);
    }

    @Test(dependsOnMethods = "testAddDescription")
    public void testEditDescription() {
        final String textDescription = "D0XVcGo8k(=D7myr/.YC6umm>]\"gY)?X_E|#HPku6T5im[oYHD-\\|B`";

        String descriptionText = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickEditDescriptionButton()
                .clearDescription()
                .addDescriptionAndSave(textDescription)
                .getDescription();

        Assert.assertEquals(
                descriptionText,
                textDescription,
                "Не совпал текст description после его редактирования");
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testCancelDeletePipelineViaDropDownMenu() {
        List<String> actualProjectList = new HomePage(getDriver())
                .openDropdownMenu(PIPELINE_NAME)
                .clickDeleteItemInDropdownMenu()
                .cancelDelete()
                .getProjectsNamesList();

        Assert.assertTrue(actualProjectList.contains(PIPELINE_NAME));
    }

    @Test
    public void testDeletePipelineViaDropDownMenu() {
        final String expectedHomePageHeading = "Welcome to Jenkins!";

        createPipeline(PIPELINE_NAME);

        String actualHomePageHeading = new HomePage(getDriver())
                .openDropdownMenu(PIPELINE_NAME)
                .clickDeleteItemInDropdownMenu()
                .confirmDelete()
                .getHeader()
                .getText();

        Assert.assertEquals(actualHomePageHeading, expectedHomePageHeading);
    }

    @Test
    public void testDeletePipelineViaSideMenu() {
        final String expectedHomePageHeading = "Welcome to Jenkins!";

        createPipeline(PIPELINE_NAME);

        String actualHomePageHeading = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickDeletePipeline()
                .confirmDeleteAtJobPage()
                .getHeader()
                .getText();

        Assert.assertEquals(actualHomePageHeading, expectedHomePageHeading);
    }

    @Test(dependsOnMethods = "testCreateNewPipeline", dataProvider = "validAliases")
    public void testScheduleWithValidData(String validTimePeriod) {

        String textAreaValidationMessage = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickConfigureInSideMenu(new PipelineConfigurationPage(getDriver()))
                .clickTriggersSectionButton()
                .selectBuildPeriodicallyCheckbox()
                .sendScheduleText(validTimePeriod)
                .clickApply()
                .getTextAreaValidationMessage()
                .getText();

        Assert.assertEquals(new PipelineConfigurationPage(getDriver()).getSavedMessage(),
                "Saved");
        Assert.assertTrue(textAreaValidationMessage.matches(
                        "(?s)Would last have run at .*; would next run at .*"),
                "Alias " + validTimePeriod + " не прошёл валидацию");
    }

    @Test(dependsOnMethods = "testCreateNewPipeline", dataProvider = "invalidCronSyntaxAndAliases")
    public void testScheduleWithInvalidData(String invalidTimePeriod, String expectedErrorMessage) {

        String actualTextErrorMessage = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickConfigureInSideMenu(new PipelineConfigurationPage(getDriver()))
                .clickTriggersSectionButton()
                .selectBuildPeriodicallyCheckbox()
                .sendScheduleText(invalidTimePeriod)
                .clickApply()
                .getErrorMessage()
                .getText();

        new PipelineConfigurationPage(getDriver()).closeModalWindow();

        Assert.assertTrue(actualTextErrorMessage.contains(expectedErrorMessage),
                String.format("Сообщение: '%s', не содержит ожидаемую ключевую информацию об ошибке: '%s'",
                        actualTextErrorMessage, expectedErrorMessage));
        Assert.assertEquals(new PipelineConfigurationPage(getDriver()).getErrorDescriptionModalWindow(),
                "A problem occurred while processing the request");
    }

    @Test(dependsOnMethods = "testCreateNewPipeline")
    public void testTriggerNewBuildManually() {

        int size = new HomePage(getDriver())
                .openProject(PIPELINE_NAME, new PipelineStatusPage(getDriver()))
                .clickBuildNow()
                .getSizeBuildsList();

        Assert.assertEquals(size, 1);
    }

    private void createPipeline(String name) {
        new HomePage(getDriver())
                .clickCreateJob()
                .sendName(name)
                .selectPipelineAndSubmit()
                .gotoHomePage();
    }
}
