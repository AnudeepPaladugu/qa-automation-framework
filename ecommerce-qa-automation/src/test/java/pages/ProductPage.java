package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    private WebDriver driver;

    private By productsTitle = By.className("title");
    private By backpackAddToCartButton =
            By.id("add-to-cart-sauce-labs-backpack");
    private By bikeLightAddToCartButton =
            By.id("add-to-cart-sauce-labs-bike-light");
    private By fleeceJacketAddToCartButton =
            By.id("add-to-cart-sauce-labs-fleece-jacket");
    private By sortDropdown =
            By.className("product_sort_container");
    private By cartBadge =
            By.className("shopping_cart_badge");
    private By backpackProductName =
            By.id("item_4_title_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductsTitle() {
        return driver.findElement(productsTitle).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(backpackAddToCartButton).click();
    }

    public void addBikeLightToCart() {
        driver.findElement(bikeLightAddToCartButton).click();
    }

    public void addFleeceJacketToCart() {
        driver.findElement(fleeceJacketAddToCartButton).click();
    }

    public void selectSortOption(String option) {
        driver.findElement(sortDropdown)
                .sendKeys(option);
    }

    public String getCartItemCount() {
        return driver.findElement(cartBadge).getText();
    }
    
    public void openBackpackDetails() {
        driver.findElement(backpackProductName).click();
    }
}