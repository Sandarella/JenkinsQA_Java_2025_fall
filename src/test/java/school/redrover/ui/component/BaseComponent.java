package school.redrover.ui.component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import school.redrover.common.BaseModel;

public abstract class BaseComponent<Component extends BaseComponent<?>> extends BaseModel {

    public BaseComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public abstract Component waitUntilComponentLoad();
}
