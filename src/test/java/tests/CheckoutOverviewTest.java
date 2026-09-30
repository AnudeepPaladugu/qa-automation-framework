package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import utils.ConfigReader;

public class CheckoutOverviewTest extends BaseTest {

    private void loginAndReachOverview() {
        loginAsStandardUser();

        CartPage cartPage = new CartPage(driver);
        cartPage.addBackpackToCart();
        cartPage.openCart();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickCheckout();

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
    }

    @Test
    public void verifyProductDisplayedInOverview() {
        loginAndReachOverview();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertEquals(
                overviewPage.getProductName(),
                "Sauce Labs Backpack"
        );
    }

    @Test
    public void verifyItemTotal() {
        loginAndReachOverview();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertEquals(
                overviewPage.getItemTotal(),
                "Item total: $29.99"
        );
    }

    @Test
    public void verifyTax() {
        loginAndReachOverview();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertEquals(
                overviewPage.getTax(),
                "Tax: $2.40"
        );
    }

    @Test
    public void verifyFinalTotal() {
        loginAndReachOverview();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        Assert.assertEquals(
                overviewPage.getTotal(),
                "Total: $32.39"
        );
    }

    @Test
    public void verifyOrderPlacement() {
        loginAndReachOverview();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        overviewPage.clickFinish();

        Assert.assertEquals(
                overviewPage.getConfirmationMessage(),
                "Thank you for your order!"
        );
    }

    @Test
    public void verifyBackHome() {
        loginAndReachOverview();

        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        overviewPage.clickFinish();
        overviewPage.clickBackHome();

        Assert.assertTrue(
                driver.getCurrentUrl().endsWith("/inventory.html"),
                "User should be returned to the inventory page."
        );
    }
}
