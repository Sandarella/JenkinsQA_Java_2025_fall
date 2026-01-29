package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

import java.util.function.Function;

public class ProjectRenamingPage<T extends BasePage<T>> extends BasePage<ProjectRenamingPage<?>> {

    private final Function<WebDriver, T> projectStatusPageFactory;

    @FindBy(name = "newName")
    private WebElement newNameField;

    @FindBy(name = "Submit")
    private WebElement renameButton;

    public ProjectRenamingPage(WebDriver driver, Function<WebDriver, T> projectStatusPageFactory) {
        super(driver);
        this.projectStatusPageFactory = projectStatusPageFactory;
    }

    @Override
    public ProjectRenamingPage<T> getPage() {
        return this;
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

        return projectStatusPageFactory.apply(getDriver()).waitUntilPageLoadJS();
    }

    public ErrorPage clickRenameButtonWithInvalidValue() {
        renameButton.click();

        return new ErrorPage(getDriver());
    }
}
