package school.redrover.ui.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import school.redrover.ui.page.*;
import school.redrover.common.CucumberDriver;
import school.redrover.common.ProjectUtils;

import java.util.List;

public class ViewSteps {

    private HomePage homePage;

    private NewItemPage newItemPage;

    private FreestyleProjectConfigurationPage freestyleProjectConfigurationPage;

    private FreestyleProjectStatusPage freestyleProjectPage;

    private PipelineProjectConfigurationPage pipelineProjectConfigurationPage;

    private PipelineProjectStatusPage pipelinePage;

    private OrganizationFolderConfigurationPage organizationFolderConfigurationPage;

    private OrganizationFolderStatusPage organizationFolderPage;

    private CreateViewPage createViewPage;

    @When("Go to New Item via Create a job")
    public void goToNewItemViaCreateAJob() {
        newItemPage = new HomePage(CucumberDriver.getDriver()).clickSidebarNewItem();
    }

    @And("Enter Item name {string}")
    public void enterItemName(String name) {
        newItemPage = newItemPage.sendName(name);
    }

    @And("Set Item type as Freestyle project, click Ok and go to Configure page")
    public void setItemTypeAsFreestyleAndClickOk() {
        freestyleProjectConfigurationPage = newItemPage.selectFreestyleProjectAndSubmit();
    }

    @And("Save configuration and go to Freestyle project page")
    public void saveConfigAndGoToFreestyleProject() {
        freestyleProjectPage = freestyleProjectConfigurationPage.clickSave();
    }

    @And("Go to home page")
    public void goHome() {
        ProjectUtils.get(CucumberDriver.getDriver());
        homePage = new HomePage(CucumberDriver.getDriver());
    }

    @And("Go to New Job to create another item")
    public void goToNewJobToCreateAnotherItem() {
        newItemPage = homePage.clickSidebarNewItem();
    }

    @And("Set Item type as Pipeline, click Ok and go to Configure page")
    public void setItemTypeAsPipelineAndClickOk() {
        pipelineProjectConfigurationPage = newItemPage.selectPipelineAndSubmit();
    }

    @And("Save configuration and go to Pipeline project page")
    public void saveConfigAndGoToPipelinePage() {
        pipelinePage = pipelineProjectConfigurationPage.clickSave();
    }

    @And("Set Item type as Organization Folder, click Ok and go to Configure page")
    public void setItemTypeAsOrganizationFolderAndClickOk() {
        organizationFolderConfigurationPage = newItemPage.selectOrganizationFolderAndSubmit();
    }

    @And("Save configuration and go to Organization Folder page")
    public void saveConfigAndGoToOrganizationFolderPage() {
        organizationFolderPage = organizationFolderConfigurationPage.clickSave();
    }

    @And("Click '+' to create New View")
    public void clickPlusToCreateNewView() {
        createViewPage = homePage.clickPlusToCreateView();
    }

    @And("Type View name {string}")
    public void typeViewName(String name) {
        createViewPage = createViewPage.sendViewName(name);
    }

    @And("Set View type as 'My View'")
    public void setViewTypeAsMyView() {
        createViewPage = createViewPage.clickMyViewTypeRadioButton();
    }

    @And("Click Create button upon choosing My View")
    public void clickCreateButtonUponChoosingMyView() {
        homePage = createViewPage.clickCreateButtonForNewView();
    }

    @Then("Sorting column text is {string}")
    public void assertSortingColumnText(String name) {
        Assert.assertEquals(homePage.getNameColumnText(), name);
    }

    @Then("Sorted items by name in view list order should be:")
    public void assertProjectNamesListInViewOrder(List<String> expectedSortedItemsByNameList) {
        Assert.assertEquals(homePage.getProjectsNamesList(), expectedSortedItemsByNameList);
    }
}
