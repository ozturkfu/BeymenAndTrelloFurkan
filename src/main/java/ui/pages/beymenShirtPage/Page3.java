package ui.pages.beymenShirtPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Methods;

public class Page3 {
    private WebDriver driver;

    @FindBy(xpath = Locator3.shirt_one)
    private WebElement shirtOne;

    @FindBy(xpath = Locator3.get_shirt_name)
    private WebElement keepShirtName;

    @FindBy(xpath = Locator3.get_shirt_price)
    private WebElement keepShirtPrice;

    @FindBy(xpath = Locator3.shirt_size)
    private WebElement shirtSize;

    @FindBy(xpath = Locator3.add_basket_btn)
    private WebElement addBasketButton;

    @FindBy(xpath = Locator3.click_basket_btn)
    private WebElement clickBasketButton;

    public Page3(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickShirtOne() {
        Methods.waitForElement(driver, shirtOne);
    }

    public String getShirtName() {
        return keepShirtName.getText();
    }

    public String getShirtPrice() {
        return keepShirtPrice.getText();
    }

    public void clickShirtSize() {
        Methods.waitForElement(driver, shirtSize);
    }

    public void clickAddBasketButton() {
        Methods.waitForElement(driver, addBasketButton);
    }

    public void clickBasketButton() {
        Methods.waitForElement(driver, clickBasketButton);
    }
}
