package school.redrover;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.BuildHistoryOfJenkinsPage;
import school.redrover.page.HomePage;

import java.util.Arrays;
import java.util.List;

public class BuildHistoryAccessTest extends BaseTest {

    @DataProvider
    private Object[][] iconSize() {
        return new Object[][]{
                {"Small"},
                {"Medium"},
                {"Large"}
        };
    }

    @Test
    public void testQuickAccessBuildHistory() {
        String actualHeading = new HomePage(getDriver())
                .clickBuildHistory()
                .getHeaderText();

        Assert.assertEquals(actualHeading, "Build History of Jenkins");
    }

    @Test
    public void testValidateBuildHistoryEmptyTable() {
        final List<String> expectedHeaders = Arrays.asList("S", "Build", "Time Since", "Status");

        BuildHistoryOfJenkinsPage buildHistoryOfJenkinsPage = new HomePage(getDriver())
                .clickBuildHistory();

        Assert.assertTrue(buildHistoryOfJenkinsPage.isBuildHistoryEmpty());
        Assert.assertEquals(buildHistoryOfJenkinsPage.getTableHeadersText(), expectedHeaders);
    }

    @Test(dataProvider = "iconSize")
    public void testChangeIconSize(String size) {
        String actualIconSize = new HomePage(getDriver())
                .clickBuildHistory()
                .checkIconSize(size)
                .getIconSize();

        Assert.assertEquals(actualIconSize, size);
    }

}
