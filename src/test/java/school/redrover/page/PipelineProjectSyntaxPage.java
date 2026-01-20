package school.redrover.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.common.BasePage;

import java.util.List;


public class PipelineProjectSyntaxPage extends BasePage<PipelineProjectSyntaxPage> {

    @FindBy(className = "task")
    private List<WebElement> sideMenuButtons;


    public PipelineProjectSyntaxPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PipelineProjectSyntaxPage getPage() {
        return this;
    }

    public List<String> getListOfButtonsInSideMenu() {

        return sideMenuButtons
                .stream()
                .map(WebElement::getText)
                .toList();
    }
}
