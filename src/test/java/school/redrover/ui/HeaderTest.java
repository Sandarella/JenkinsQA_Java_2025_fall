package school.redrover.ui;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.ui.page.FolderStatusPage;
import school.redrover.ui.page.HomePage;

import java.util.List;

public class HeaderTest extends BaseTest {

    @Test
    public void testSearchResultsAppear() {
        final String[] listOfTypes = new String[]{"Folder", "Freestyle project", "Pipeline", "Multi-configuration project", "Multibranch Pipeline", "Organization Folder"};

        HomePage homePage = new HomePage(getDriver());
        for (String title : listOfTypes) {
            homePage.clickSidebarNewItem()
                    .sendName(title)
                    .selectItemTypeAndSubmitAndGoHome(title);
        }
        List<String> results = homePage.clickSearchButton()
                .searchFor("F")
                .searchResults();

        Assert.assertEquals(results.size(), 4);
    }

    @Test(dependsOnMethods = "testSearchResultsAppear")
    public void testSearchResultsActions() {
        new HomePage(getDriver())
                .clickSearchButton()
                .searchFor("Folder");

        new Actions(getDriver()).sendKeys(Keys.ENTER).perform();

        Assert.assertTrue(new FolderStatusPage(getDriver()).checkURLContains("/job/Folder/"));
    }
}
