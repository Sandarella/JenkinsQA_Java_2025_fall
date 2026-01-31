package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;


public class ErrorPage extends BasePage<ErrorPage> {

    @FindBy(xpath = "//h1[text()='Error']/../p")
    private WebElement errorMessage;

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ErrorPage getPage() {
        return this;
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
