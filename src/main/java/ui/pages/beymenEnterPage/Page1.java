package ui.pages.beymenEnterPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Methods;

public class Page1 {

    private WebDriver driver;

    @FindBy(xpath = Locator1.cookie_accept_btn)
    private WebElement cookieAcceptBtn;

    @FindBy(xpath = Locator1.gender_man_btn)
    private WebElement genderManBtn;

    @FindBy(xpath = Locator1.deny_btn)
    private WebElement denyBtn;

    @FindBy(xpath = Locator1.beymen_header)
    private WebElement beymenHeader;

    public Page1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickCookieAcceptBtn() {
        Methods.waitForElement(driver, cookieAcceptBtn);
    }

    public void clickGenderManBtn() {
        Methods.waitForElement(driver, genderManBtn);
    }

    public void clickDenyBtn() {
        Methods.waitForElement(driver, denyBtn);
    }

    public boolean isBeymenHeaderDisplayed() {
        return beymenHeader.isDisplayed();
    }
}
