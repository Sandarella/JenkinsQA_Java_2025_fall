package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;

import static school.redrover.common.ProjectUtils.log;

public class ProjectRenamingPage<T extends BasePage<T>> extends BasePage<ProjectRenamingPage<?>> {

    private final Class<T> projectStatusPageClass;

    @FindBy(name = "newName")
    private WebElement newNameField;

    @FindBy(name = "Submit")
    private WebElement renameButton;

    public ProjectRenamingPage(WebDriver driver, Class<T> projectStatusPageClass) {
        super(driver);
        this.projectStatusPageClass = projectStatusPageClass;
    }

    @Override
    public ProjectRenamingPage<T> getPage() {
        return this;
    }

    @Override
    public ProjectRenamingPage<T> waitUntilPageLoad() {
        return null;
    }

    public ProjectRenamingPage<T> sendNewName(String newName) {
        newNameField.sendKeys(newName);

        return this;
    }

    public ProjectRenamingPage<T> clearName() {
        newNameField.clear();

        return this;
    }

    public T clickRenameButton() {
        renameButton.click();

        try {
            return projectStatusPageClass.getDeclaredConstructor(WebDriver.class).newInstance(getDriver()).waitUntilPageLoadJS();
        } catch (Exception e) {
            log(e.getMessage());
            return null;
        }
    }

    public ErrorPage clickRenameButtonWithInvalidValue() {
        renameButton.click();

        return new ErrorPage(getDriver());
    }
}
