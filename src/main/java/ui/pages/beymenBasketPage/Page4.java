package ui.pages.beymenBasketPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Methods;

public class Page4 {

    private WebDriver driver;

    @FindBy(xpath = Locator4.order_summary)
    private WebElement orderSummary;

    @FindBy(xpath = Locator4.order_summary_price)
    private WebElement orderSummaryPrice;

    @FindBy(xpath = Locator4.dropdown_menu)
    private WebElement dropdownMenu;

    @FindBy(xpath = Locator4.select_dropdown_menu_value)
    private WebElement dropdownMenuValue;

    @FindBy(xpath = Locator4.check_shirt_two)
    private WebElement checkShirtTwo;

    @FindBy(xpath = Locator4.delete_basket)
    private WebElement deleteBasket;

    @FindBy(xpath = Locator4.product_not_found)
    private WebElement productNotFound;

    public Page4(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOrderSummaryText() {
        Methods.waitForElement(driver, orderSummary);
        return orderSummary.getText();
    }

    public String getOrderSummaryPrice() {
        return orderSummaryPrice.getText();
    }

    public void selectDropdownMenuValue() {
        Methods.waitForElement(driver, dropdownMenu);
        dropdownMenu.click();
        Methods.waitForElement(driver, dropdownMenuValue);
        dropdownMenuValue.click();
    }

    public String getCheckShirtTwo() {
        return checkShirtTwo.getText();
    }

    public void clickDeleteBasket() {
        Methods.waitForElement(driver, deleteBasket);
        deleteBasket.click();
    }

    public String getProductNotFoundMessage() {
        return productNotFound.getText();
    }
}
