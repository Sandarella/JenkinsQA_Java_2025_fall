package school.redrover.ui;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.ui.page.HomePage;

import java.util.*;

public class ConfigureSystemTest extends BaseTest {

    private static final String SYSTEM_MESSAGE = "Hello redRover School!";

    @DataProvider(name = "tooltips")
    private Iterator<Object[]> getTooltipNameList() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{"Home directory"});
        data.add(new Object[]{"Usage"});
        data.add(new Object[]{"Computer Retention Check Interval"});
        data.add(new Object[]{"Quiet period"});
        data.add(new Object[]{"Jenkins URL"});
        data.add(new Object[]{"System Admin e-mail address"});
        data.add(new Object[]{"Resource Root URL"});
        data.add(new Object[]{"Disable deferred wipeout on this node"});
        return data.iterator();
    }

    @Test(dataProvider = "tooltips")
    public void testTooltips(String tooltipName) {
        Integer actualNumberOfTooltip = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .clickTooltip(tooltipName)
                .getNumberOfOpenTooltips();

        Assert.assertEquals(actualNumberOfTooltip, 1);
    }

    @Test
    public void testCreateSystemMessage() {
        String actualSystemMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .clearSystemMessage()
                .setSystemMessage(SYSTEM_MESSAGE)
                .clickSave()
                .getSystemMessageText();

        Assert.assertEquals(actualSystemMessage, SYSTEM_MESSAGE);
    }

    @Test(dependsOnMethods = "testCreateSystemMessage")
    public void testSystemMessagePreview() {
        final String addToPreviewMessage = " This is the best project!";

        String actualPreviewMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setSystemMessage(addToPreviewMessage)
                .getPreviewSystemMessageText();

        Assert.assertEquals(actualPreviewMessage, SYSTEM_MESSAGE + addToPreviewMessage);
    }

    @Test(dependsOnMethods = "testSystemMessagePreview")
    public void testChangeSystemMessage() {
        final String addToSystemMessage = "!";

        String actualSystemMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setSystemMessage(addToSystemMessage)
                .clickSave()
                .getSystemMessageText();

        Assert.assertEquals(actualSystemMessage, SYSTEM_MESSAGE + addToSystemMessage);
    }

    @Test
    public void testChangeNumberOfExecutors() {
        final String numberOfExecutors = "5";

        String actualNumberOfExecutors = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setNumberOfExecutors(numberOfExecutors)
                .clickSave()
                .getNumberOfExecutors();

        Assert.assertEquals(actualNumberOfExecutors, numberOfExecutors);
    }

    @Test
    public void testUsageVariants() {
        final List<String> expectedVariants = List.of(
                "Use this node as much as possible",
                "Only build jobs with label expressions matching this node");

        List<String> actualVariants = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .getUsageModeOptions();

        Assert.assertEquals(actualVariants, expectedVariants);
    }

    /**
     * Этот тест проходит только при первом запуске, в "чистом" Jenkins.
     * Между тестами это значение не сбрасывается автоматически.
     * Если вы пишете новый тест, который изменяет это поле - то → сделайте его зависимым от этого теста
     */
    @Test
    public void testIntervalDefaultValue() {
        final String defaultIntervalValue = "60";

        String actualIntervalValue = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .getInputComputerRetentionCheckIntervalValue();

        Assert.assertEquals(actualIntervalValue, defaultIntervalValue);
    }

    @Test(dependsOnMethods = "testIntervalDefaultValue")
    public void testChangeComputerRetentionCheckIntervalPositive() {
        final String testIntervalValue = "59";

        String actualIntervalValue = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setInputComputerRetentionCheckIntervalValue(testIntervalValue)
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .getInputComputerRetentionCheckIntervalValue();

        Assert.assertEquals(actualIntervalValue, testIntervalValue);
    }

    @Test
    public void testSaveInvalidComputerRetentionCheckIntervalShowsError() {
        final String invalidIntervalValue = "61";
        final String expectedErrorMessage = "java.lang.IllegalArgumentException: interval must be below or equal 60s";

        String actualErrorMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setInputComputerRetentionCheckIntervalValue(invalidIntervalValue)
                .clickSaveButtonWithInvalidValue()
                .getErrorMessage();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testHintAppearsForInvalidComputerRetentionCheckInterval() {
        final String incorrectInterval = "61";
        final String expectedErrorMessage = "This value should be between 1 and 60";

        String actualErrorMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setInputComputerRetentionCheckIntervalValue(incorrectInterval)
                .clickApply()
                .getInvalidComputerRetentionCheckIntervalText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testQuietPeriodHint() {
        final String incorrectQuietPeriod = "-2";
        final String expectedErrorMessage = "This value should be larger than 0";

        String actualErrorMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .clearQuietPeriod()
                .setQuietPeriod(incorrectQuietPeriod)
                .getQuietPeriodText();

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }

    @Test
    public void testChangeQuietPeriodPositive() {
        final String setSecondsQuietPeriod = "10";

        String actualQuietPeriod = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .clearQuietPeriod()
                .setQuietPeriod(setSecondsQuietPeriod)
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .getInputQuietPeriodValue();

        Assert.assertEquals(actualQuietPeriod, setSecondsQuietPeriod);
    }

    @Test
    public void testGlobalProperties() {
        final List<String> expectedGlobalProperties = List.of(
                "Disable deferred wipeout on this node",
                "Disk Space Monitoring Thresholds",
                "Environment variables",
                "Tool Locations");

        List<String> actualGlobalProperties = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .getGlobalPropertiesListText();

        Assert.assertEquals(actualGlobalProperties, expectedGlobalProperties);
    }

    @Test
    public void testSaveFreeDiskSpaceThreshold() {
        final String freeDiskSpaceThreshold = "1.1GiB";

        String actualFreeDiskSpaceThreshold = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .checkGlobalPropertiesDiskSpaceMonitoringThresholds()
                .sendFreeDiskSpaceThreshold(freeDiskSpaceThreshold)
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .checkGlobalPropertiesDiskSpaceMonitoringThresholds()
                .getFreeDiskSpaceThresholdText();

        Assert.assertEquals(actualFreeDiskSpaceThreshold, freeDiskSpaceThreshold);
    }

    @Test(dependsOnMethods = "testSaveFreeDiskSpaceThreshold")
    public void testSaveFreeDiskSpaceWarningThreshold() {
        final String freeDiskSpaceWarningThreshold = "1.2GiB";

        String actualFreeDiskSpaceWarningThreshold = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .sendFreeDiskSpaceWarningThreshold(freeDiskSpaceWarningThreshold)
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .checkGlobalPropertiesDiskSpaceMonitoringThresholds()
                .getFreeDiskSpaceWarningThresholdText();

        Assert.assertEquals(actualFreeDiskSpaceWarningThreshold, freeDiskSpaceWarningThreshold);
    }

    @Test(dependsOnMethods = "testSaveFreeDiskSpaceWarningThreshold")
    public void testSaveFreeTempSpaceThreshold() {
        final String freeTempSpaceThreshold = "1.3GiB";

        String actualFreeTempSpaceThreshold = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .sendFreeTempSpaceThreshold(freeTempSpaceThreshold)
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .checkGlobalPropertiesDiskSpaceMonitoringThresholds()
                .getFreeTempSpaceThresholdText();

        Assert.assertEquals(actualFreeTempSpaceThreshold, freeTempSpaceThreshold);
    }

    @Test(dependsOnMethods = "testSaveFreeTempSpaceThreshold")
    public void testSaveFreeTempSpaceWarningThreshold() {
        final String freeTempSpaceWarningThreshold = "1.4GiB";

        String actualFreeTempSpaceWarningThreshold = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .sendFreeTempSpaceWarningThreshold(freeTempSpaceWarningThreshold)
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .checkGlobalPropertiesDiskSpaceMonitoringThresholds()
                .getFreeTempSpaceWarningThresholdText();

        Assert.assertEquals(actualFreeTempSpaceWarningThreshold, freeTempSpaceWarningThreshold);
    }

    @Test
    public void testEnvironmentVariables() {
        final String variableName = UUID.randomUUID().toString();
        final String variableValue = UUID.randomUUID().toString();

        Map<String, String> actualEnvironmentValues = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .checkGlobalPropertiesEnvironmentVariables()
                .clickAddVariables()
                .sendVariableName(variableName)
                .sendVariableValue(variableValue)
                .clickSave()
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .getEnvironmentVariablesValues();

        Assert.assertTrue(actualEnvironmentValues.containsKey(variableName));
        Assert.assertEquals(actualEnvironmentValues.get(variableName), variableValue);
    }

}
