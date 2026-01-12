package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ConfigureSystemTest extends BaseTest {

    private final String SYSTEM_MESSAGE = "Hello redRover School!";

    @Test
    public void testCreateSystemMessage() {

        new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .clearSystemMessage()
                .setSystemMessage(SYSTEM_MESSAGE)
                .clickSave();

        String actualSystemMessage = new HomePage(getDriver())
                .gotoHomePage()
                .getSystemMessageText();

        Assert.assertEquals(
                actualSystemMessage,
                SYSTEM_MESSAGE);
    }

    @Test(dependsOnMethods = "testCreateSystemMessage")
    public void testSystemMessagePreview() {

        final String addToPreviewMessage = " This is the best project!";

        String actualPreviewMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setSystemMessage(addToPreviewMessage)
                .getPreviewSystemMessage();

        Assert.assertEquals(SYSTEM_MESSAGE + addToPreviewMessage, actualPreviewMessage);
    }

    @Ignore
    @Test(dependsOnMethods = "testSystemMessagePreview")
    public void testChangeSystemMessage() {

        final String addToSystemMessage = "!";

        new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setSystemMessage(addToSystemMessage)
                .clickSave();

        String actualSystemMessage = new HomePage(getDriver())
                .gotoHomePage()
                .getSystemMessageText();

        Assert.assertEquals(
                actualSystemMessage, SYSTEM_MESSAGE + addToSystemMessage);
    }

    @Test
    public void testChangeNumberOfExecutors() {

        final String numberOfExecutors = "5";

        new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setNumberOfExecutors(numberOfExecutors)
                .clickSave();

        String actualNumberOfExecutors = new HomePage(getDriver())
                .gotoHomePage()
                .getNumberOfExecutors();

        Assert.assertEquals(actualNumberOfExecutors, numberOfExecutors);
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

    @Ignore
    @Test
    public void testHintAppearsForInvalidComputerRetentionCheckInterval() {
        final String incorrectInterval = "61";
        final String expectedErrorMessage = "This value should be between 1 and 60";

        String actualErrorMessage = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickConfigurationSystem()
                .setInputComputerRetentionCheckIntervalValue(incorrectInterval)
                .clickApply()
                .getInvalidComputerRetentionCheckIntervalHint()
                .getText();

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
                .getQuietPeriodHint()
                .getText();

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

    @Ignore
    @Test
    public void testGlobalProperties() {

        final List<String> expectedGlobalProperties = List.of(
                "Disable deferred wipeout on this node",
                "Disk Space Monitoring Thresholds",
                "Environment variables",
                "Tool Locations");

        getSystemConfigurePage();

        List<String> actualGlobalProperties = getDriver().findElements(By.xpath("//div[@id='global-properties']/..//label"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(actualGlobalProperties, expectedGlobalProperties);
    }

    @Ignore
    @Test
    public void testDiskSpaceMonitoringThresholds() {

        final String testFreeDiskSpaceThreshold = "1.1GiB";
        final String testDiskSpaceWarningThreshold = "1.2GiB";
        final String testTempSpaceThreshold = "1.3GiB";
        final String testTempSpaceWarningThreshold = "1.4GiB";

        getSystemConfigurePage();

        WebElement diskSpaceMonitoringThresholds = getDriver()
                .findElement(By.xpath("//input[contains(@name, 'DiskSpaceMonitorNodeProperty')]/following-sibling::label"));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", diskSpaceMonitoringThresholds);
        diskSpaceMonitoringThresholds.click();

        WebElement freeDiskSpaceThreshold = getDriver().findElement(By.name("_.freeDiskSpaceThreshold"));
        freeDiskSpaceThreshold.clear();
        freeDiskSpaceThreshold.sendKeys(testFreeDiskSpaceThreshold);

        WebElement freeDiskSpaceWarningThreshold = getDriver().findElement(By.name("_.freeDiskSpaceWarningThreshold"));
        freeDiskSpaceWarningThreshold.clear();
        freeDiskSpaceWarningThreshold.sendKeys(testDiskSpaceWarningThreshold);

        WebElement freeTempSpaceThreshold = getDriver().findElement(By.name("_.freeTempSpaceThreshold"));
        freeTempSpaceThreshold.clear();
        freeTempSpaceThreshold.sendKeys(testTempSpaceThreshold);

        WebElement freeTempSpaceWarningThreshold = getDriver().findElement(By.name("_.freeTempSpaceWarningThreshold"));
        freeTempSpaceWarningThreshold.clear();
        freeTempSpaceWarningThreshold.sendKeys(testTempSpaceWarningThreshold);

        getDriver().findElement(By.name("Submit")).click();

        getSystemConfigurePage();

        Assert.assertEquals(
                getDriver().findElement(By.name("_.freeDiskSpaceThreshold")).getAttribute("value"),
                testFreeDiskSpaceThreshold);
        Assert.assertEquals(
                getDriver().findElement(By.name("_.freeDiskSpaceWarningThreshold")).getAttribute("value"),
                testDiskSpaceWarningThreshold);
        Assert.assertEquals(
                getDriver().findElement(By.name("_.freeTempSpaceThreshold")).getAttribute("value"),
                testTempSpaceThreshold);
        Assert.assertEquals(
                getDriver().findElement(By.name("_.freeTempSpaceWarningThreshold")).getAttribute("value"),
                testTempSpaceWarningThreshold);
    }

    @Ignore
    @Test
    public void testEnvironmentVariables() {

        final String testVariableName = UUID.randomUUID().toString();
        final String testVariableValue = UUID.randomUUID().toString();

        getSystemConfigurePage();

        WebElement addButton = getDriver().findElement(By.xpath(
                "//input[contains(@name, 'EnvironmentVariablesNodeProperty')]/ancestor::div[contains(@class, 'optionalBlock')]//button[text()='Add']"));

        if (!addButton.isDisplayed()) {
            WebElement environmentVariables = getDriver()
                    .findElement(By.xpath("//input[contains(@name, 'EnvironmentVariablesNodeProperty')]/following-sibling::label"));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", environmentVariables);
            environmentVariables.click();
        }

        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", addButton);
        addButton.click();

        List<WebElement> variableNames = getDriver().findElements(By.xpath("//input[@name='env.key']"));
        WebElement variableName = getLastElement(variableNames);
        variableName.clear();
        variableName.sendKeys(testVariableName);

        List<WebElement> variableValues = getDriver().findElements(By.xpath("//input[@name='env.value']"));
        WebElement variableValue = getLastElement(variableValues);
        variableValue.clear();
        variableValue.sendKeys(testVariableValue);

        getDriver().findElement(By.name("Submit")).click();

        getSystemConfigurePage();

        Assert.assertTrue(
                getDriver().findElements(By.xpath("//input[@name='env.key']")).stream()
                        .map(name -> name.getAttribute("value")).toList()
                        .contains(testVariableName));
        Assert.assertTrue(
                getDriver().findElements(By.xpath("//input[@name='env.value']")).stream()
                        .map(value -> value.getAttribute("value")).toList()
                        .contains(testVariableValue));
    }

    private void getSystemConfigurePage() {
        getWait10().until(visibilityOfElementLocated(By.cssSelector("a[href$='manage']"))).click();
        getWait10().until(visibilityOfElementLocated(By.cssSelector("a[href$='configure']"))).click();
    }

    private <T> T getLastElement(List<T> list) {
        if (list != null && !list.isEmpty()) {
            return list.get(list.size() - 1);
        }

        return null;
    }
}
