package school.redrover.ui.trait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface BaseTrait {

    WebDriver getDriver();

    WebDriverWait getWait2();
    WebDriverWait getWait5();
    WebDriverWait getWait10();
}
