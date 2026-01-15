package school.redrover.cucumber;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.page.NewItemPage;
import school.redrover.page.FreestyleProjectConfigurationPage;
import school.redrover.page.FreestyleProjectStatusPage;
import school.redrover.page.HomePage;
import school.redrover.common.CucumberDriver;
import school.redrover.common.ProjectUtils;


public class FreestyleSteps {

    private HomePage homePage;
    private NewItemPage newItemPage;

    private FreestyleProjectStatusPage freestyleProjectPage;
    private FreestyleProjectConfigurationPage freestyleProjectConfigurationPage;

    @When("Go to NewJob")
    public void goToNewJob() {
        newItemPage = new HomePage(CucumberDriver.getDriver()).clickSidebarNewItem();
    }

    @And("Choose job type as {string}")
    public void setJobType(String jobType) {
        if ("FreestyleProject".equals(jobType)) {
            newItemPage = newItemPage.selectFreestyleProject();
        } else if ("Folder".equals(jobType)) {
            newItemPage = newItemPage.selectFolder();
        } else {
            throw new RuntimeException("Project type {%s} does not found.".formatted(jobType));
        }
    }

    @And("Choose job type as Freestyle")
    public void setJobTypeAsFreestyle() {
        newItemPage = newItemPage.selectFreestyleProject();
    }

    @And("Type job name {string}")
    public void enterItemName(String name) {
        newItemPage.sendName(name);
    }

    @And("Click Ok and go to config")
    public void clickOkAndGoToConfig() {
        freestyleProjectConfigurationPage = newItemPage.clickSubmit(new FreestyleProjectConfigurationPage(CucumberDriver.getDriver()));
    }

    @And("Go home")
    public void goHome() {
        ProjectUtils.get(CucumberDriver.getDriver());
        homePage = new HomePage(CucumberDriver.getDriver());
    }

    @And("Job with name {string} is exists")
    public void checkJobName(String jobName) {
        Assert.assertTrue(homePage.getProjectsNamesList().contains(jobName));
    }

    @And("Save config and go to Freestyle job")
    public void saveConfigAndGoToFreestyleJob() {
        freestyleProjectPage = freestyleProjectConfigurationPage
                .clickSave();
    }

    @Then("Freestyle job name is {string}")
    public void assertFreestyleJobName(String jobName) {
        Assert.assertEquals(freestyleProjectPage.getHeader().getText(), jobName);
    }

    @When("Click Freestyle job {string}")
    public void clickFreestyleJob(String jobName) {
        freestyleProjectPage = new HomePage(CucumberDriver.getDriver())
                .openProject(jobName, new FreestyleProjectStatusPage(CucumberDriver.getDriver()));
    }

    @And("Click Freestyle configure")
    public void clickFreestyleConfigure() {
        freestyleProjectConfigurationPage = freestyleProjectPage
                .getSidebarComponent()
                .clickSidebarConfigure();
//                .clickConfigureInSideMenu(new FreestyleProjectConfigurationPage(CucumberDriver.getDriver()));
    }

    @And("Type Freestyle job description as {string}")
    public void setFreestyleJobDescription(String jobDescription) {
        freestyleProjectConfigurationPage.sendDescription(jobDescription);
    }

    @Then("Job description is {string}")
    public void assertFreestyleJobDescription(String jobDescription) {
        Assert.assertEquals(freestyleProjectPage.getDescription(), jobDescription);
    }
}
