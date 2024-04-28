package testcase;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ui.pages.beymenBasketPage.Page4;
import ui.pages.beymenEnterPage.Page1;
import ui.pages.beymenSearchPage.Page2;
import ui.pages.beymenShirtPage.Page3;
import utils.WebDriverManager;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

public class BeymenTest {

    private WebDriver driver;
    private Properties env;
    private Page1 beymenEnterPage;
    private Page2 beymenSearchPage;
    private Page3 beymenShirtPage;
    private Page4 beymenBasketPage;
    private String keyword1;
    private String keyword2;

    @Before
    public void setUp() {
        driver = WebDriverManager.createWebDriver();
        env = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/env.properties");
            env.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        beymenEnterPage = new Page1(driver);
        beymenSearchPage = new Page2(driver);
        beymenShirtPage = new Page3(driver);
        beymenBasketPage = new Page4(driver);

        try {
            FileInputStream excelFile = new FileInputStream("src/main/resources/bmen.xlsx");
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Row firstRow = sheet.getRow(0);
            keyword1 = firstRow.getCell(0).getStringCellValue();
            keyword2 = firstRow.getCell(1).getStringCellValue();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void beymenTest() throws InterruptedException {
        driver.get(env.getProperty("beymen.url"));
        beymenEnterPage.clickCookieAcceptBtn();
        beymenEnterPage.clickGenderManBtn();
        beymenEnterPage.clickDenyBtn();
        beymenEnterPage.isBeymenHeaderDisplayed();
        System.out.println("The home page has been opened successfully.");

        Thread.sleep(5000);
        beymenSearchPage.clickSearchBox();
        beymenSearchPage.searchFor(keyword1);
        beymenSearchPage.isSearchResultDisplayed();
        beymenSearchPage.clearSearchBox();
        beymenSearchPage.clickSearchBox2();
        beymenSearchPage.searchFor(keyword2);
        beymenSearchPage.isSearchResult2Displayed();
        beymenSearchPage.pressEnter();

        Thread.sleep(5000);
        beymenShirtPage.clickShirtOne();
        String productInfo = beymenShirtPage.getShirtName();
        String productPriceStr = beymenShirtPage.getShirtPrice();
        writeToFile(productInfo, productPriceStr);
        double productPrice = Double.parseDouble(productPriceStr.replace(" TL", "").replace(".", "").replace(",", "."));
        Thread.sleep(1000);
        beymenShirtPage.clickShirtSize();
        beymenShirtPage.clickAddBasketButton();
        beymenShirtPage.clickBasketButton();

        Thread.sleep(5000);
        beymenBasketPage.getOrderSummaryText();
        double basketPrice = Double.parseDouble(beymenBasketPage.getOrderSummaryPrice().replace(" TL", "").replace(".", "").replace(",", "."));
        assertEquals(productPrice, basketPrice, 0.01);
        System.out.println("Prices are equal: " + productPrice + " - " + basketPrice);
        beymenBasketPage.selectDropdownMenuValue();
        String piece = beymenBasketPage.getCheckShirtTwo();
        System.out.println("Quantity: " + piece);
        Thread.sleep(5000);
        beymenBasketPage.clickDeleteBasket();
        String notFoundMessage = beymenBasketPage.getProductNotFoundMessage();
        System.out.println("Products deleted from cart " + notFoundMessage);
    }

    @After
    public void quitChrome() {
       driver.quit();
    }
    public static void writeToFile(String productInfo, String price) {
        String fileName = "src/main/resources/output/selected_product_info.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Product Info: " + productInfo + "\n");
            writer.write("Price: " + price + "\n");

            System.out.println("Product Info " + fileName + " written to the file.");
        } catch (IOException e) {
            System.out.println("File write error: " + e.getMessage());
        }
    }
}
