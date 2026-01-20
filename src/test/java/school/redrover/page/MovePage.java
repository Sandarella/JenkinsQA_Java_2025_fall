package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import school.redrover.common.BasePage;


public class MovePage extends BasePage<MovePage> {

    @FindBy(className = "jenkins-select__input")
    private WebElement selectFolder;

    @FindBy(name = "Submit")
    private WebElement moveButton;

    public MovePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MovePage getPage() {
        return this;
    }

    public MovePage selectDestinationFolder(String folderName) {
        new Select(selectFolder).selectByVisibleText("Jenkins » %s".formatted(folderName));

        return this;
    }

    public void clickMoveButtonAndGoHome() {
        String urlBeforeMoving = getDriver().getCurrentUrl();
        moveButton.click();
        getWait5().until(ExpectedConditions.not(ExpectedConditions.urlToBe(urlBeforeMoving)));
        gotoHomePage();
    }
}
