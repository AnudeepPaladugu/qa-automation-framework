package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;

public class CartTest extends BaseTest {

    @Test
    public void verifyAddProductToCart() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), "1");
    }

    @Test
    public void verifyCartBadgeUpdates() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();
        cartPage.addBikeLightToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), "2");
    }

    @Test
    public void verifyProductAppearsInCart() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();
        cartPage.openCart();

        Assert.assertTrue(cartPage.isBackpackDisplayed());
    }

    @Test
    public void verifyRemoveProductFromCart() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();
        cartPage.openCart();
        cartPage.removeBackpack();

        Assert.assertFalse(cartPage.isBackpackDisplayed());
    }

    @Test
    public void verifyMultipleProductsInCart() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();
        cartPage.addBikeLightToCart();

        Assert.assertEquals(cartPage.getCartItemCount(), "2");
    }

    @Test
    public void verifyRemoveOneProductFromMultipleProducts() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();
        cartPage.addBikeLightToCart();
        cartPage.openCart();
        cartPage.removeBackpack();

        Assert.assertTrue(cartPage.isBikeLightDisplayed());
        Assert.assertFalse(cartPage.isBackpackDisplayed());
    }
}
