package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.*;

import java.util.List;

public class FolderManagementTest extends BaseTest {

    private static final String FOLDER_NAME = "MyFolder";
    private static final String VIEW_NAME = "MyView";
    private static final String CREDENTIAL_NAME = "MyCredential";

    @DataProvider
    public Object[][] itemsData() {
        return new Object[][]{
                {"freestyle01", "Freestyle project"},
                {"pipeline01", "Pipeline"},
                {"multiConfig01", "Multi-configuration project"},
                {"folder01", "Folder"},
                {"Multibranch01", "Multibranch Pipeline"},
                {"orgFolder01", "Organization Folder"}
        };
    }

    public void createFolder() {
        new HomePage(getDriver())
                .clickCreateJob()
                .sendName(FOLDER_NAME)
                .selectFolderAndSubmit()
                .gotoHomePage();
    }

    @Test(dataProvider = "itemsData")
    public void testConfigureMenuItemInDropdownForEachJob(String itemName, String itemType) {
        final String menuItem = "Configure";

        createFolder();

        boolean isConfigureMenuItemDisplayed = new HomePage(getDriver())
                .openProject(FOLDER_NAME, new FolderStatusPage(getDriver()))
                .clickSidebarNewItem()
                .sendName(itemName)
                .selectItemTypeAndSubmitAndGoHome(itemType)
                .openProject(FOLDER_NAME, new FolderStatusPage(getDriver()))
                .openItemDropdownMenu(itemName)
                .isMenuItemInDropdownDisplayed(menuItem);

        Assert.assertTrue(isConfigureMenuItemDisplayed);
    }

    @Test
    public void testNavigateToConfigurationViaSideMenuForPipeline() {
        final String itemName = "pipeline01";

        createFolder();

        String actualHeadingText = new HomePage(getDriver())
                .openProject(FOLDER_NAME, new FolderStatusPage(getDriver()))
                .clickSidebarNewItem()
                .sendName(itemName)
                .selectPipelineAndSubmit()
                .gotoHomePage()
                .openProject(FOLDER_NAME, new FolderStatusPage(getDriver()))
                .openSubItemPage(itemName, new PipelineStatusPage(getDriver()))
                .clickConfigureInSideMenu(new PipelineConfigurationPage(getDriver()))
                .getHeader()
                .getText();

        Assert.assertEquals(actualHeadingText, "Configure");
    }

    @Test
    public void addNewCredentials() {
        createFolder();

        String actualCredentialName = new HomePage(getDriver())
                .openProject(FOLDER_NAME, new FolderStatusPage(getDriver()))
                .clickCredentialsLink()
                .clickGlobalLink()
                .clickAddCredentialsButton()
                .enterUsername(CREDENTIAL_NAME)
                .clickCreateButton()
                .clickCredentials()
                .getCredentialsName(CREDENTIAL_NAME);

        Assert.assertTrue(actualCredentialName.contains(CREDENTIAL_NAME), "Credential name is not found in text: " + actualCredentialName);
    }

    @Test
    public void testViewCreationForm() {
        createFolder();

        new HomePage(getDriver())
                .openProject(FOLDER_NAME, new FolderStatusPage(getDriver()))
                .clickNewView()
                .sendName(VIEW_NAME)
                .selectTypeGlobalView()
                .selectTypeListView()
                .selectTypeMyView()
                .clickCreate();
    }
}
