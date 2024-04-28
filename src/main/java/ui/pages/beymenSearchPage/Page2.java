package ui.pages.beymenSearchPage;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Methods;

public class Page2 {
    private WebDriver driver;

    @FindBy(xpath = Locator2.search_box)
    private WebElement searchBox;

    @FindBy(xpath = Locator2.search_box2)
    private WebElement searchBox2;

    @FindBy(xpath = Locator2.check_result)
    private WebElement checkResult;

    @FindBy(xpath = Locator2.check_result2)
    private WebElement checkResult2;

    public Page2(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSearchBox() {
        Methods.waitForElement(driver, searchBox);
    }

    public void clickSearchBox2() {
        Methods.waitForElement(driver, searchBox2);
    }

    public void searchFor(String keyword) {
        searchBox2.sendKeys(keyword);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isSearchResultDisplayed() {
        return checkResult.isDisplayed();
    }

    public boolean isSearchResult2Displayed() {
        return checkResult2.isDisplayed();
    }

    public void clearSearchBox() {
        searchBox2.clear();
    }

    public void pressEnter() {
        searchBox2.sendKeys(Keys.ENTER);
    }
}
