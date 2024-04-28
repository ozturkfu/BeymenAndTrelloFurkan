package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Methods {

    public static void waitForElement (WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
    }
}
