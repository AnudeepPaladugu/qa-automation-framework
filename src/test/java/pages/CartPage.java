package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By backpackAddToCartButton =
            By.id("add-to-cart-sauce-labs-backpack");
    private final By bikeLightAddToCartButton =
            By.id("add-to-cart-sauce-labs-bike-light");
    private final By cartButton =
            By.className("shopping_cart_link");
    private final By cartBadge =
            By.className("shopping_cart_badge");
    private final By backpackCartItem =
            By.id("item_4_title_link");
    private final By bikeLightCartItem =
            By.id("item_0_title_link");
    private final By backpackRemoveButton =
            By.id("remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addBackpackToCart() {
        clickWhenReady(backpackAddToCartButton);
    }

    public void addBikeLightToCart() {
        clickWhenReady(bikeLightAddToCartButton);
    }

    public String getCartItemCount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    public void openCart() {
        clickWhenReady(cartButton);
    }

    public boolean isBackpackDisplayed() {
        return !driver.findElements(backpackCartItem).isEmpty();
    }

    public boolean isBikeLightDisplayed() {
        return !driver.findElements(bikeLightCartItem).isEmpty();
    }

    public void removeBackpack() {
        clickWhenReady(backpackRemoveButton);
    }

    private void clickWhenReady(By locator) {
        wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        ).click();
    }
}
