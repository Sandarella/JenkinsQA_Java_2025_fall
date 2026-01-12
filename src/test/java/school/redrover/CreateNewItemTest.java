package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import school.redrover.common.BaseTest;
import school.redrover.page.FreestyleProjectConfigurationPage;
import school.redrover.page.FreestyleProjectStatusPage;
import school.redrover.page.HomePage;
import school.redrover.page.NewItemPage;

import java.time.Duration;
import java.util.List;

public class CreateNewItemTest extends BaseTest {

    private static final String PROJECT_NAME = "New Project";

    @Test
    public void testNewItemPageByClickingCreateAJobLink() {
        String result = new HomePage(getDriver())
                .clickCreateJob()
                .getHeader()
                .getText();

        Assert.assertEquals(result, "New Item");
    }

    @Test
    public void testNewItemPageByClickingNewItemLink() {
        String result = new HomePage(getDriver())
                .clickSidebarNewItem()
                .getHeader()
                .getText();

        Assert.assertEquals(result, "New Item");
    }

    @Test
    public void testEnterAnItemNameIsDisplayedOkButtonIdDisabled() {
          Boolean result = new HomePage(getDriver())
                  .clickCreateJob()
                  .isOkButtonEnabled();

          Assert.assertFalse(result);
    }

    @Test
    public void testItemNameInput() {
        Boolean validationMessageDisabled = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .areValidationMessagesDisabled();

        Assert.assertTrue(validationMessageDisabled,
                "All validation messages should be disabled for valid input");
    }

    @Test
    public void testInvalidItemNameField() {

        final String invalidChars = " !@#$%^&*[]|\\;:<>?/";

        NewItemPage newItemPage = new HomePage(getDriver()).clickCreateJob();

        for (char ch : invalidChars.toCharArray()) {
            newItemPage
                    .clearSendName()
                    .sendName(String.valueOf(ch));

            String dataValid = newItemPage.getNameDataValid();

            Assert.assertEquals(dataValid, "false", "Character '" + ch + "' should not be allowed");
        }
    }

    @Test
    public void testPipelineTypeCanBeSelected() {
        boolean isSelected = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectPipeline()
                .isPipelineSelected();

        Assert.assertTrue(isSelected);
    }

    @Test
    public void testPipelineTypeHighlightAndOkButtonEnables() {
        NewItemPage newItemPage = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectPipeline();

        Assert.assertTrue(newItemPage.isPipelineHighlighted());
        Assert.assertTrue(newItemPage.isOkButtonEnabled());
    }

    @Test
    public void testErrorMessageForDuplicateItemNames() {
        String errorMessage = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectFreestyleProjectAndSubmit()
                .gotoHomePage()
                .clickSidebarNewItem()
                .sendName(PROJECT_NAME)
                .selectFolder()
                .getErrorMessage()
                .getText();

        Assert.assertEquals(errorMessage, "» A job already exists with the name ‘New Project’");
    }

    @Test
    public void testAcceptsAlphanumericAndUnderscores() {

        List<String> projectList = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectPipelineAndSubmit()
                .gotoHomePage()
                .getProjectsNamesList();

        Assert.assertNotEquals(projectList.size(), 0);
        Assert.assertEquals(projectList.get(0), PROJECT_NAME);
    }

    @Test
    public void testConfigurationPageIsVisible() {
        String headingText = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectFreestyleProjectAndSubmit()
                .getHeader()
                .getText();

        Assert.assertEquals(headingText, "Configure");
    }
    @Test
    public void testBuildStepsFilterNames() {
        new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectFreestyleProjectAndSubmit();

        WebElement addBuildStep = getWait2().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add build')]"))
        );

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView({block: 'center'});", addBuildStep);
        addBuildStep.click();

        getWait2().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[normalize-space()='Execute Windows batch command']")
        ));

        String[] testSteps = {"Execute Windows batch command", "Execute shell", "Invoke Ant"};

        for (String step : testSteps) {
            WebElement filter = getDriver().findElement(By.xpath("//input[@type='search' and @placeholder='Filter']"));
            filter.clear();
            filter.sendKeys(step.substring(0, Math.min(5, step.length())));

            WebElement actualStep = new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//button[contains(@style,'inline-flex') and normalize-space()='%s']".formatted(step))
                    ));

            Assert.assertEquals(actualStep.getText(), step, "Filter didn't match expected build step");
        }
    }

    @Test
    public void createNewFreeStyleProjectTest() {
        String projectTitle = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(PROJECT_NAME)
                .selectFreestyleProjectAndSubmit()
                .clickSave()
                .getHeader()
                .getText();

        Assert.assertEquals(projectTitle, PROJECT_NAME);
    }

    @Test
    public void createMultibranchPipelineProjectItemTest() {
        final String jobName = "NEW_TEST_JOB";

        String newProject = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(jobName)
                .selectMultibranchPipelineAndSubmit()
                .gotoHomePage()
                .getProjectsNamesList()
                .get(0);

        Assert.assertEquals(jobName, newProject);
    }

    @Test
    public void testNewItemByCopyingFromExistingItem() {
        final String originalName = "originalProject";
        final String newName = "newProject";
        final String descriptionText = "RedRover is the best";
        final String daysToKeepText = "10";
        final String maxOfBuildsText = "20";
        final String gitHubUrl = "git.com/testUrl";
        final String buildsUrl = "example.com/test";

        List <String> originalItemSettingsList =
                List.of(descriptionText, daysToKeepText, maxOfBuildsText, gitHubUrl, buildsUrl);

        List<String> copiedItemSettingsList = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(originalName)
                .selectFreestyleProjectAndSubmit()
                .sendDescription(descriptionText)
                .setCheckBoxDiscardAndSetDaysNum(daysToKeepText, maxOfBuildsText)
                .setCheckBoxGitHubAndSendUrl(gitHubUrl)
                .setCheckBoxTriggerBuildsAndSendUrl(buildsUrl)
                .clickSave()
                .gotoHomePage()
                .clickSidebarNewItem()
                .sendName(newName)
                .sendNameToCopyFromAndSubmit(originalName)
                .gotoHomePage()
                .openProject(newName, new FreestyleProjectStatusPage(getDriver()))
                .clickConfigureInSideMenu(new FreestyleProjectConfigurationPage(getDriver()))
                .getSettingsToList();

        Assert.assertEquals(originalItemSettingsList, copiedItemSettingsList);
    }
    @Test
    public void testErrorDisplayedForEmptyItemName(){
        String errorMessage = new HomePage(getDriver())
                .clickSidebarNewItem()
                .clickOkButton()
                .getErrorDisplayedForEmptyItemName();

        Assert.assertEquals(errorMessage, "» This field cannot be empty, please enter a valid name");
    }
}

