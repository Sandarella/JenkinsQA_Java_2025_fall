package school.redrover.ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;


public class CloudsPage extends BasePage<CloudsPage> {

    @FindBy(tagName = "h1")
    private WebElement header;

    @FindBy(xpath = "//p")
    private WebElement cloudsInfo;

    public CloudsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CloudsPage getPage() {
        return this;
    }

    public String getCloudsPageInfoText() {
        return cloudsInfo.getText();
    }
}
