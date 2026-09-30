package tests;

import base.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import utils.ConfigReader;

public class CheckoutOverviewTest extends BaseTest {

	private void loginAndReachOverview() {

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

	    checkoutPage.enterFirstName("John");
	    checkoutPage.enterLastName("Doe");
	    checkoutPage.enterPostalCode("500001");
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

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html"
        );
    }
}