package school.redrover.ui.page;

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

    @FindBy(name = "_.displayNameOrNull")
    private WebElement displayNameField;

    @FindBy(className = "jenkins-toggle-switch__label__unchecked-title")
    private WebElement stateToggle;

    @FindBy(className = "tippy-content")
    private WebElement toggleTooltipText;

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
        displayNameField.sendKeys(name);

        return this;
    }

    public MultibranchProjectConfigurationPage clickToggle() {
        toggleSwitcher.click();

        return this;
    }

    public String getStateToggleText() {
        try {
            return getWait2().until(ExpectedConditions.visibilityOf(stateToggle)).getText();
        } catch (Exception ignore) {
        }

        return "Enabled";
    }

    public String getToggleTooltipText() {
        new Actions(getDriver()).moveToElement(toggleTooltipOnHover).perform();

        return getWait5().until(ExpectedConditions.visibilityOf(toggleTooltipText)).getText();
    }

}
