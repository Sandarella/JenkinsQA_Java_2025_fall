package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class AppearancePage extends BasePage<AppearancePage> {

    @FindBy(xpath = "//label[span[text() = 'Light']]")
    private WebElement lightThemeRadioButton;

    @FindBy(xpath = "//label[span[text() = 'Dark (System)']]")
    private WebElement systemThemeRadioButton;

    @FindBy(xpath = "//label[span[text() = 'Dark']]")
    private WebElement darkThemeRadioButton;

    @FindBy(xpath = "//label[contains(., 'Do not allow users to select a different theme')]")
    private WebElement doNotAllowDifferentThemeCheckbox;

    @FindBy(css = "input[name='_.disableUserThemes']")
    private WebElement disableUserThemesInput;

    @FindBy(css = "button.jenkins-button.apply-button")
    private WebElement applyButton;

    @FindBy(css = "button.jenkins-submit-button")
    private WebElement saveButton;

    @FindBy(xpath = "//a[contains(text(), 'Find plugins')]")
    private WebElement findPluginsButton;

    @FindBy(xpath = "//*[@id='notification-bar']")
    private WebElement applyPopUp;

    public AppearancePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public AppearancePage getPage() {
        return this;
    }

    @Override
    public AppearancePage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.elementToBeClickable(findPluginsButton));
        return this;
    }

    public AppearancePage clickLightTheme() {
        lightThemeRadioButton.click();

        return this;
    }

    public AppearancePage clickSystemTheme() {
        systemThemeRadioButton.click();

        return this;
    }

    public AppearancePage clickDarkTheme() {
        darkThemeRadioButton.click();

        return this;
    }

    public AppearancePage checkAllowTheme() {
        if (!disableUserThemesInput.isSelected()) {
            doNotAllowDifferentThemeCheckbox.click();
        }

        return this;
    }

    public AppearancePage clickApplyButton() {
        applyButton.click();

        return this;
    }

    public String getApplyPopUpText() {
        return getWait10().until(ExpectedConditions.visibilityOf(applyPopUp)).getText();
    }

    public JenkinsManagementPage clickSaveButton() {
        saveButton.click();

        return new JenkinsManagementPage(getDriver()).waitUntilPageLoad();
    }
}

