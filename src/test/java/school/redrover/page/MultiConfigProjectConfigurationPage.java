package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.PageUtils;

import java.util.List;


public class MultiConfigProjectConfigurationPage extends BaseProjectConfigurationPage<MultiConfigProjectConfigurationPage, MultiConfigProjectStatusPage> {

    @FindBy(id = "configuration-matrix")
    private WebElement configurationMatrix;

    @FindBy(css = "[suffix=axis]")
    private WebElement addAxisButton;

    @FindBy(className = "jenkins-dropdown__item")
    private List<WebElement> addAxisDropdownList;

    @FindBy(xpath = "//section[2]//button[contains(@class, 'advanced-button')]")
    private WebElement advancedDropdownButton;

    @FindBy(xpath = "//label[text()='Quiet period']")
    private WebElement quietPeriodCheckbox;

    @FindBy(name = "quiet_period")
    private WebElement quietPeriodInput;

    @FindBy (css = "label[for='enable-disable-project']")
    private WebElement projectToggle;

    public MultiConfigProjectConfigurationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected MultiConfigProjectStatusPage getProjectStatusPage() {
        return new MultiConfigProjectStatusPage(getDriver());
    }

    @Override
    public MultiConfigProjectConfigurationPage getPage() {
        return this;
    }

    @Override
    public MultiConfigProjectConfigurationPage waitUntilPageLoad() {
        getWait5().until(ExpectedConditions.visibilityOf(configurationMatrix));

        return this;
    }

    public String getConfigurationMatrixText() {
        return getWait5().until(ExpectedConditions.visibilityOf(configurationMatrix)).getText().trim();
    }

    public MultiConfigProjectConfigurationPage clickAddAxisButton() {
        PageUtils.scrollToElement(getDriver(), addAxisButton);
        getWait10().until(ExpectedConditions.elementToBeClickable(addAxisButton));
        addAxisButton.click();

        getWait10().until(ExpectedConditions.visibilityOf(addAxisDropdownList.get(0)));

        return this;
    }

    public List<String> getAddAxisDropdownItemTextList() {
        return addAxisDropdownList.stream().map(WebElement::getText).toList();
    }

    public MultiConfigProjectConfigurationPage clickAdvancedDropdownButton() {
        advancedDropdownButton.click();

        return this;
    }

    public MultiConfigProjectConfigurationPage clickQuietPeriodCheckbox() {
        PageUtils.scrollToElement(getDriver(), quietPeriodCheckbox);
        quietPeriodCheckbox.click();

        return this;
    }

    public MultiConfigProjectConfigurationPage setQuietPeriodInput(String seconds) {
        quietPeriodInput.clear();
        quietPeriodInput.sendKeys(seconds);

        return this;
    }

    public MultiConfigProjectConfigurationPage clickProjectToggle(){
        projectToggle.click();

        return this;
    }

    public boolean isProjectToggleSelected(){
        return projectToggle.isSelected();
    }
}
