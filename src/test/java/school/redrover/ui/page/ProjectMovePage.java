package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import school.redrover.common.BasePage;

import java.util.function.Function;


public class ProjectMovePage<ProjectStatusPage extends BaseProjectStatusPage<ProjectStatusPage, ?>>
        extends BasePage<ProjectMovePage<ProjectStatusPage>> {

    @FindBy(className = "jenkins-select__input")
    private WebElement selectFolder;

    @FindBy(name = "Submit")
    private WebElement moveButton;

    private final Function<WebDriver, ProjectStatusPage> projectStatusPageFactory;


    public ProjectMovePage(WebDriver driver, Function<WebDriver, ProjectStatusPage> projectStatusPageFactory) {
        super(driver);
        this.projectStatusPageFactory = projectStatusPageFactory;
    }

    @Override
    public ProjectMovePage<ProjectStatusPage> getPage() {
        return this;
    }

    public ProjectMovePage<ProjectStatusPage> selectDestinationFolder(String folderName) {
        new Select(selectFolder).selectByVisibleText("Jenkins » %s".formatted(folderName));

        return this;
    }

    public ProjectStatusPage clickMoveButton() {
        moveButton.click();

        return projectStatusPageFactory.apply(getDriver()).waitUntilPageLoadJS();
    }
}
