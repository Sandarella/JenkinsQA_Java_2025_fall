package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.FolderStatusPage;
import school.redrover.page.HomePage;


import java.util.List;


public class FolderSettingsTest extends BaseTest {

    private static final String FOLDER_NAME = "Just folder";
    private static final String DISPLAY_NAME = "Look at this";

    @Test
    public void testDisplayName() {
        List<String> projectList = new HomePage(getDriver())
                .clickCreateJob()
                .sendName(FOLDER_NAME)
                .selectFolderAndSubmit()
                .setDisplayName(DISPLAY_NAME)
                .clickSave()
                .gotoHomePage()
                .getProjectsNamesList();

        Assert.assertNotEquals(projectList.size(), 0);
        Assert.assertEquals(projectList.get(0), DISPLAY_NAME);
    }

    @Test(dependsOnMethods = "testDisplayName")
    public void testFolderInfo() {
        final String description = "This is lorem text... or not.";
        final List<String> expectedInfo = List.of(DISPLAY_NAME, description);

        FolderStatusPage.FolderInfo actualInfo = new HomePage(getDriver())
                .openProject(DISPLAY_NAME, new FolderStatusPage(getDriver()))
                .getSidebarComponent()
                .clickSidebarConfigure()
                .sendDescription(description)
                .clickSave()
                .getInfo();

        Assert.assertTrue(expectedInfo.containsAll(List.of(actualInfo.getDisplayName(), actualInfo.getDescription())));
    }
}
