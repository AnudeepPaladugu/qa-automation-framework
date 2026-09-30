package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CartTest extends BaseTest {

    private void login() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("standard.username")
        );

        loginPage.enterPassword(
                ConfigReader.getProperty("standard.password")
        );
        loginPage.clickLogin();
    }

    @Test
    public void verifyAddProductToCart() {

        login();

        CartPage cartPage = new CartPage(driver);

        cartPage.addBackpackToCart();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                "1"
        );
    }

    @Test
    public void verifyCartBadgeUpdates() {

        login();

        CartPage cartPage = new CartPage(driver);

        cartPage.addBackpackToCart();
        cartPage.addBikeLightToCart();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                "2"
        );
    }

    @Test
    public void verifyProductAppearsInCart() {

        login();

        CartPage cartPage = new CartPage(driver);

        cartPage.addBackpackToCart();
        cartPage.openCart();

        Assert.assertTrue(
                cartPage.isBackpackDisplayed()
        );
    }

    @Test
    public void verifyRemoveProductFromCart() {

        login();

        CartPage cartPage = new CartPage(driver);

        cartPage.addBackpackToCart();
        cartPage.openCart();
        cartPage.removeBackpack();

        Assert.assertFalse(
                cartPage.isBackpackDisplayed()
        );
    }

    @Test
    public void verifyMultipleProductsInCart() {

        login();

        CartPage cartPage = new CartPage(driver);

        cartPage.addBackpackToCart();
        cartPage.addBikeLightToCart();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                "2"
        );
    }

    @Test
    public void verifyRemoveOneProductFromMultipleProducts() {

        login();

        CartPage cartPage = new CartPage(driver);

        cartPage.addBackpackToCart();
        cartPage.addBikeLightToCart();
        cartPage.openCart();
        cartPage.removeBackpack();

        Assert.assertTrue(
                cartPage.isBikeLightDisplayed()
        );

        Assert.assertFalse(
                cartPage.isBackpackDisplayed()
        );
    }
}