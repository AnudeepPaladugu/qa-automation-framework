package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductPage;

public class ProductTest extends BaseTest {

    private void login() {
        loginAsStandardUser();
    }

    @Test
    public void verifyProductsDisplayed() {
        login();

        ProductPage productPage = new ProductPage(driver);

        Assert.assertEquals(
                productPage.getProductsTitle(),
                "Products"
        );
    }

    @Test
    public void verifyProductDetails() {
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.openBackpackDetails();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory-item.html?id=4"),
                "Backpack details page should be displayed."
        );
    }

    @Test
    public void verifySortNameAZ() {
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectSortOption("Name (A to Z)");

        Assert.assertEquals(
                productPage.getFirstProductName(),
                "Sauce Labs Backpack"
        );
    }

    @Test
    public void verifySortNameZA() {
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectSortOption("Name (Z to A)");

        Assert.assertEquals(
                productPage.getFirstProductName(),
                "Test.allTheThings() T-Shirt (Red)"
        );
    }

    @Test
    public void verifySortPriceLowToHigh() {
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectSortOption("Price (low to high)");

        Assert.assertEquals(
                productPage.getFirstProductPrice(),
                "$7.99"
        );
    }

    @Test
    public void verifySortPriceHighToLow() {
        login();

        ProductPage productPage = new ProductPage(driver);
        productPage.selectSortOption("Price (high to low)");

        Assert.assertEquals(
                productPage.getFirstProductPrice(),
                "$49.99"
        );
    }
}
