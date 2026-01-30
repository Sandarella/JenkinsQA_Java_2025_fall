package school.redrover.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.ui.page.HomePage;
import school.redrover.ui.page.OrganizationFolderStatusPage;
import java.util.List;


public class OrganizationFolderTest extends BaseTest {

    private static final String FOLDER_NAME = "Organization Folder";

    @Test
    public void testCreateOrganisationFolder() {
        String OrganizationFolderName = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(FOLDER_NAME)
                .selectOrganizationFolderAndSubmit()
                .clickSave()
                .getDisplayNameOrganizationFolder();

        Assert.assertEquals(OrganizationFolderName, FOLDER_NAME);
    }

    @Test
    public void testAddDisplayNameAndDescription() {
        String expectedDisplayName = "New folder name";
        String expectedDescription = "New Description";

        OrganizationFolderStatusPage organizationFolderPage = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(FOLDER_NAME)
                .selectOrganizationFolderAndSubmit()
                .inputDisplayName(expectedDisplayName)
                .inputDescription(expectedDescription)
                .clickSave();

        String actualDisplayName = organizationFolderPage.getDisplayNameOrganizationFolder();
        String actualDescription = organizationFolderPage.getDescription();

        Assert.assertEquals(actualDisplayName, expectedDisplayName);
        Assert.assertEquals(actualDescription, expectedDescription);
    }
    @Test
    public void testDeleteOrganizationFolder() {
        List<String> actualListOfItems = new HomePage(getDriver())
                .clickSidebarNewItem()
                .sendName(FOLDER_NAME)
                .selectOrganizationFolderAndSubmit()
                .clickSave()
                .getSidebarComponent()
                .clickSidebarDelete()
                .clickYesConfirmationDelete()
                .getProjectsNamesList();

        Assert.assertFalse(actualListOfItems.contains(FOLDER_NAME),
                "Folder should be deleted but is still present in the list");
    }
}