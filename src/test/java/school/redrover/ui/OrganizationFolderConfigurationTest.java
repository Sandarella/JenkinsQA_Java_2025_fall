package school.redrover.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.ui.page.HomePage;
import school.redrover.ui.page.OrganizationFolderStatusPage;

import java.util.List;

public class OrganizationFolderConfigurationTest extends BaseTest {

    private static final String FOLDER_NAME = "Organization Folder %d".formatted(System.currentTimeMillis());

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
    public void testDisplayNameTooltipLink() {
        String expectedExternalLink = "https://plugins.jenkins.io/branch-api";

        String displayNameTooltipLink = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(FOLDER_NAME)
                .selectOrganizationFolderAndSubmit()
                .clickDisplayNameLink()
                .getDisplayNameTooltipLink();

        Assert.assertEquals(displayNameTooltipLink, expectedExternalLink);
    }

    @Test
    public void testRepositorySourcesFilterByNamePart() {
        String searchString = "github";
        String expectedRepositorySourceName = "GitHub Organization";

        List<String> repositorySourcesList = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(FOLDER_NAME)
                .selectOrganizationFolderAndSubmit()
                .filterRepositorySources(searchString)
                .getRepositorySourceNames();

        Assert.assertEquals(repositorySourcesList.size(), 1);
        Assert.assertListContainsObject(
                repositorySourcesList,
                expectedRepositorySourceName,
                "No <%s> in list".formatted(expectedRepositorySourceName));
    }
}
