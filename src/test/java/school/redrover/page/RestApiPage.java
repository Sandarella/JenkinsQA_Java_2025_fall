package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

import java.util.List;


public class RestApiPage extends BasePage<RestApiPage> {

    @FindBy(xpath = "//dt/a[@href]")
    public List<WebElement> xmlJsonPythonApiLinks;

    public RestApiPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public RestApiPage getPage() {
        return this;
    }

    public List<String> getXmlJsonPythonApiLinksText() {
        return xmlJsonPythonApiLinks
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
