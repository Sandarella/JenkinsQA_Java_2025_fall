package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MultibranchProjectConfigurationPage extends BaseProjectConfigurationPage<MultibranchProjectConfigurationPage, MultibranchProjectStatusPage> {

    @FindBy(css = "[data-title='Disabled']")
    private WebElement toggleSwitcher;

    @FindBy(id = "toggle-switch-enable-disable-project")
    private WebElement toggleTooltipOnHover;


    public MultibranchProjectConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected MultibranchProjectStatusPage getProjectStatusPage() {
        return new MultibranchProjectStatusPage(getDriver());
    }

    @Override
    public MultibranchProjectConfigurationPage getPage() {
        return this;
    }

    public MultibranchProjectConfigurationPage sendDisplayName(String name) {
        getDriver().findElement(By.xpath("//input[@name='_.displayNameOrNull']")).sendKeys(name);

        return this;
    }

    public MultibranchProjectConfigurationPage clickToggle() {
        toggleSwitcher.click();

        return this;
    }

    public String getToggleState() {
        try {
            return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("jenkins-toggle-switch__label__unchecked-title")))
                    .getText();
        } catch (Exception ignore) {
        }

        return "Enabled";
    }

    public String getToggleTooltipTextOnHover() {
        new Actions(getDriver()).moveToElement(toggleTooltipOnHover).perform();

        return getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.className("tippy-content")))
                .getText();
    }

    public String getBreadcrumbItem() {
        return getWait10().until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//span[contains(text(),'Configuration')]"))).getText();
    }
}
