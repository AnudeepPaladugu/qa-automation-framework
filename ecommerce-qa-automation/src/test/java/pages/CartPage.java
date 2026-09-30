package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By backpackAddToCartButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private By bikeLightAddToCartButton =
            By.id("add-to-cart-sauce-labs-bike-light");

    private By cartButton =
            By.className("shopping_cart_link");

    private By cartBadge =
            By.className("shopping_cart_badge");

    private By backpackCartItem =
            By.id("item_4_title_link");

    private By bikeLightCartItem =
            By.id("item_0_title_link");

    private By backpackRemoveButton =
            By.id("remove-sauce-labs-backpack");

    private By bikeLightRemoveButton =
            By.id("remove-sauce-labs-bike-light");

    public CartPage(WebDriver driver) {

        this.driver = driver;

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void addBackpackToCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        backpackAddToCartButton
                )
        ).click();
    }

    public void addBikeLightToCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        bikeLightAddToCartButton
                )
        ).click();
    }

    public String getCartItemCount() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        cartBadge
                )
        ).getText();
    }

    public void openCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        cartButton
                )
        ).click();
    }

    public boolean isBackpackDisplayed() {

        return !driver.findElements(
                backpackCartItem
        ).isEmpty();
    }

    public boolean isBikeLightDisplayed() {

        return !driver.findElements(
                bikeLightCartItem
        ).isEmpty();
    }

    public void removeBackpack() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        backpackRemoveButton
                )
        ).click();
    }

    public void removeBikeLight() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        bikeLightRemoveButton
                )
        ).click();
    }
}