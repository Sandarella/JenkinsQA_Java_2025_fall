package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;
import java.util.List;

public class NavigateToDashboardTest extends BaseTest {
    private final String freestyleName = "TestProject_";
    private final int countOfItem = 4;


    @Test
    public void testVerifyDashboardDisplay() {
        for (int i = 1; i <= countOfItem; i++) {
            new HomePage(getDriver())
                    .clickSidebarNewItem()
                    .sendName(freestyleName + i)
                    .selectFreestyleProjectAndSubmit()
                    .gotoHomePage();
        }

        Assert.assertTrue(new HomePage(getDriver()).getProjectsNamesList().size() >= countOfItem,
                "Not all created Jenkins items are displayed on the Dashboard");

        for (int i = 1; i <= countOfItem; i++) {
            Assert.assertTrue(new HomePage(getDriver()).isIconTableVisible("TestProject_" + i), "status icon is visible");
            Assert.assertEquals(new HomePage(getDriver()).getProjectStatus("TestProject_" + i), "Not built");
        }
    }

    @Test(dependsOnMethods = "testVerifyDashboardDisplay")
    public void testVerifyNavigationBehavior() {
        List<String> createdProjects = new HomePage(getDriver()).getProjectsNamesList();

        for (String project : createdProjects) {
            String check = new HomePage(getDriver())
                    .gotoHomePage()
                    .clickProject(project)
                    .getProjectName(project);
            String check2 = new HomePage(getDriver())
                    .gotoHomePage()
                    .clickSidebarNewItem()
                    .getHeaderText();

            Assert.assertEquals(check, project);
            Assert.assertEquals(check2, "New Item");
        }
    }
}