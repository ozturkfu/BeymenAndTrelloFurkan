package ui.pages.beymenBasketPage;

public class Locator4 {

    public static final String order_summary = "//span[@class='m-orderSummary__name' and contains(text(), 'Ödenecek Tutar')]";
    public static final String order_summary_price = "//span[@class='m-orderSummary__value' and contains(text(), '2.099,00 TL')]";
    public static final String dropdown_menu = "//select[@id='quantitySelect0-key-0']";
    public static final String select_dropdown_menu_value = "//select[@id='quantitySelect0-key-0']/option[@value='2']";
    public static final String check_shirt_two = "//select[@aria-label='2 adet']/option[@value='2']";
    public static final String delete_basket = "//*[name()='svg' and @class='icon icon-remove-new']";
    public static final String product_not_found = "//strong[contains(text(), 'Sepetinizde Ürün Bulunmamaktadır')]";
}
