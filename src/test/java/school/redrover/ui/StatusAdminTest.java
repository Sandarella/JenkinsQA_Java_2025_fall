package school.redrover.ui;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.ui.page.HomePage;

public class StatusAdminTest extends BaseTest {

    @Test
    public void testDisplayAdminNameInUserStatusBreadcrumbs() {
        final String[] expectedUserName = {null};

        String actualUserName = new HomePage(getDriver())
                .hoverUserAccountIcon()
                .getUserName(userName -> expectedUserName[0] = userName)
                .clickUserName()
                .getAdminNameInBreadcrumbs();

        Assert.assertTrue(actualUserName.contains(expectedUserName[0]));
    }

    @Test
    public void testDisplayAdminNameInUserStatusHeading() {
        final String[] expectedUserName = {null};

        String actualUserName = new HomePage(getDriver())
                .hoverUserAccountIcon()
                .getUserName(userName -> expectedUserName[0] = userName)
                .clickUserName()
                .getUserName();

        Assert.assertEquals(actualUserName, expectedUserName[0]);
    }
}
