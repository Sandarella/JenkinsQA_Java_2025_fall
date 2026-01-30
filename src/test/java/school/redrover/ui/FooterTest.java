package school.redrover.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.common.PageUtils;
import school.redrover.ui.page.HomePage;
import java.util.List;


public class FooterTest extends BaseTest {

 private static final String NAME_PAGE = "REST API";

    @Test
    public void testRestApiLink() {
        String linkText = new HomePage(getDriver()).getRestApiLink().getText();

        Assert.assertEquals(linkText, NAME_PAGE);
    }

    @Test
    public void testApiPageHeading() {
        String actualHeading = new HomePage(getDriver())
                .clickRestApiLink()
                .getHeaderText();

        Assert.assertEquals(actualHeading, NAME_PAGE);
    }

    @Test
    public void testApiPageContentLinks() {
        final List<String> expectedLinks = List.of(
                "XML API",
                "JSON API",
                "Python API"
        );

        List<String> actualLinks = new HomePage(getDriver())
                .clickRestApiLink()
                .getXmlJsonPythonApiLinksText();

        Assert.assertEquals(actualLinks, expectedLinks);
    }

    @Test
    public void testRestApiLinkByFocusAndEnter() {
        PageUtils.focusAndEnterByKeyboard(getDriver(), new HomePage(getDriver()).getRestApiLink());

        Assert.assertEquals(
                getDriver().getTitle(),
                "Remote API - Jenkins");
    }

    @Test
    public void testJenkinsVersion() {
        String version = new HomePage(getDriver()).getJenkinsVersionButtonText();

        Assert.assertEquals(version,"Jenkins 2.516.3");
    }

    @Test
    public void testJenkinsDropdownMenu() {
        final List<String> expectedDropdownItems = List.of(
                "About Jenkins",
                "Get involved",
                "Website"
        );

        List<String> actualDropdownItems = new HomePage(getDriver())
                .clickJenkinsVersion()
                .getDropdownElementsTextList();

        Assert.assertEquals(actualDropdownItems, expectedDropdownItems);
    }

    @Test
    public void testRestApiUserPage() {
        String actualHeading = new HomePage(getDriver())
                .clickUserAccountIcon()
                .clickRestApiLink()
                .getHeaderText();

       Assert.assertEquals(actualHeading, NAME_PAGE);
    }

    @Test
    public void testRestApiNewItemPage() {
        String actualHeading = new HomePage(getDriver())
                .clickSidebarNewItem()
                .clickRestApiLink()
                .getHeaderText();

        Assert.assertEquals(actualHeading, NAME_PAGE);
    }

    @Test
    public void testRestApiNewNodesPage() {
        String actualHeading = new HomePage(getDriver())
                .clickSetUpAnAgent()
                .clickRestApiLink()
                .getHeaderText();

        Assert.assertEquals(actualHeading, NAME_PAGE);
    }

    @Test
    public void testRestApiNodesPage() {
        String actualHeading = new HomePage(getDriver())
               .clickBuildExecutorStatus()
               .clickRestApiLink()
                .getHeaderText();

        Assert.assertEquals(actualHeading, NAME_PAGE);
    }

    @Test
    public void testRestApiBuildHistoryOfJenkinsPage() {
        String actualHeading = new HomePage(getDriver())
                .clickBuildHistory()
                .clickRestApiLink()
                .getHeaderText();

        Assert.assertEquals(actualHeading, NAME_PAGE);
    }
}
