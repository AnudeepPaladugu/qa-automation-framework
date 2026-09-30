package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CheckoutTest extends BaseTest {

    private void loginAndOpenCheckout() {

        LoginPage loginPage = new LoginPage(driver);


        loginPage.enterUsername(
                ConfigReader.getProperty("standard.username")
        );

        loginPage.enterPassword(
                ConfigReader.getProperty("standard.password")
        );

        loginPage.clickLogin();

        CartPage cartPage = new CartPage(driver);

        cartPage.addBackpackToCart();
        cartPage.openCart();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.clickCheckout();
    }

    @Test
    public void verifyCheckoutNavigation() {

        loginAndOpenCheckout();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html"
        );
    }

    @Test
    public void verifyValidCheckoutInformation() {

        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("500001");
        checkoutPage.clickContinue();

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-two.html"
        );
    }

    @Test
    public void verifyEmptyFirstName() {

        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterLastName("Doe");
        checkoutPage.enterPostalCode("500001");
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: First Name is required"
        );
    }

    @Test
    public void verifyEmptyLastName() {

        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("John");
        checkoutPage.enterPostalCode("500001");
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: Last Name is required"
        );
    }

    @Test
    public void verifyEmptyPostalCode() {

        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName("John");
        checkoutPage.enterLastName("Doe");
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: Postal Code is required"
        );
    }
}