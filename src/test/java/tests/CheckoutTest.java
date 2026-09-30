package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ConfigReader;

public class CheckoutTest extends BaseTest {

    private void loginAndOpenCheckout() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();
        cartPage.openCart();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();
    }

    @Test
    public void verifyCheckoutNavigation() {
        loginAndOpenCheckout();

        Assert.assertTrue(
                driver.getCurrentUrl().endsWith("/checkout-step-one.html"),
                "Checkout information page should be displayed."
        );
    }

    @Test
    public void verifyValidCheckoutInformation() {
        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterFirstName(
                ConfigReader.getProperty("test.firstName")
        );
        checkoutPage.enterLastName(
                ConfigReader.getProperty("test.lastName")
        );
        checkoutPage.enterPostalCode(
                ConfigReader.getProperty("test.postalCode")
        );
        checkoutPage.clickContinue();

        Assert.assertTrue(
                driver.getCurrentUrl().endsWith("/checkout-step-two.html"),
                "Checkout overview page should be displayed."
        );
    }

    @Test
    public void verifyEmptyFirstName() {
        loginAndOpenCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterLastName(
                ConfigReader.getProperty("test.lastName")
        );
        checkoutPage.enterPostalCode(
                ConfigReader.getProperty("test.postalCode")
        );
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

        checkoutPage.enterFirstName(
                ConfigReader.getProperty("test.firstName")
        );
        checkoutPage.enterPostalCode(
                ConfigReader.getProperty("test.postalCode")
        );
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

        checkoutPage.enterFirstName(
                ConfigReader.getProperty("test.firstName")
        );
        checkoutPage.enterLastName(
                ConfigReader.getProperty("test.lastName")
        );
        checkoutPage.clickContinue();

        Assert.assertEquals(
                checkoutPage.getErrorMessage(),
                "Error: Postal Code is required"
        );
    }
}
