package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import school.redrover.common.BasePage;


public class ErrorPage extends BasePage<ErrorPage> {

    public ErrorPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ErrorPage getPage() {
        return this;
    }

    public String getErrorMessage() {
        return getWait5()
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Error']/../p")))
                .getText();
    }
}
