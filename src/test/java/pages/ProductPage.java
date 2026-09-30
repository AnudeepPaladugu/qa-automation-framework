package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    private final WebDriver driver;

    private final By productsTitle = By.className("title");
    private final By sortDropdown = By.className("product_sort_container");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By backpackProductName = By.id("item_4_title_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductsTitle() {
        return driver.findElement(productsTitle).getText();
    }

    public void selectSortOption(String option) {
        new Select(driver.findElement(sortDropdown))
                .selectByVisibleText(option);
    }

    public String getFirstProductName() {
        return driver.findElements(productNames)
                .get(0)
                .getText();
    }

    public String getFirstProductPrice() {
        return driver.findElements(productPrices)
                .get(0)
                .getText();
    }

    public void openBackpackDetails() {
        driver.findElement(backpackProductName).click();
    }
}
