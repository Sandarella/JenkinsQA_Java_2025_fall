package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.common.BaseTest;
import school.redrover.page.HomePage;


public class ConfigureAppearanceTest extends BaseTest {

   @Test
    public void testSystemThemeAndApplyButtonPopUp() {
        String expectedText = "Saved";
        String popUpApplyButtonText = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickAppearanceLink()
                .clickSystemTheme()
                .clickApplyButton()
                .getApplyPopUpText();

        Assert.assertEquals(popUpApplyButtonText, expectedText);
    }

   @Test(dependsOnMethods = "testSystemThemeAndApplyButtonPopUp")
    public void testChangeDarkThemeAndSaveButton() {
        String expectedTeg = "dark";
        String themaHtmlText = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickAppearanceLink()
                .clickDarkTheme()
                .checkAllowTheme()
                .clickSaveButton()
                .getHTMLAttributeThemeText();

        Assert.assertEquals(themaHtmlText, expectedTeg);
    }

    @Test(dependsOnMethods = "testChangeDarkThemeAndSaveButton")
    public void changeThemeLight() {
        String finalTheme = "Saved";
        String checkingLight = new HomePage(getDriver())
                .clickManageJenkinsGear()
                .clickAppearanceLink()
                .clickLightTheme()
                .checkAllowTheme()
                .clickApplyButton()
                .getApplyPopUpText();

        Assert.assertEquals(checkingLight, finalTheme);
    }
}
