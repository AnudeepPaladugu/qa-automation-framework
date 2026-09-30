package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import utils.ConfigReader;

public class ProductTest extends BaseTest {

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

        Assert.assertEquals(
                driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory-item.html?id=4"
        );
    }

    @Test
    public void verifySortNameAZ() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectSortOption("Name (A to Z)");

        Assert.assertEquals(
                driver.findElements(
                        org.openqa.selenium.By.className("inventory_item_name")
                ).get(0).getText(),
                "Test.allTheThings() T-Shirt (Red)"
        );
    }

    @Test
    public void verifySortNameZA() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectSortOption("Name (Z to A)");

        Assert.assertEquals(
                driver.findElements(
                        org.openqa.selenium.By.className("inventory_item_name")
                ).get(0).getText(),
                "Test.allTheThings() T-Shirt (Red)"
        );
    }

    @Test
    public void verifySortPriceLowToHigh() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectSortOption("Price (low to high)");

        Assert.assertEquals(
                driver.findElements(
                        org.openqa.selenium.By.className("inventory_item_price")
                ).get(0).getText(),
                "$7.99"
        );
    }

    @Test
    public void verifySortPriceHighToLow() {

        login();

        ProductPage productPage = new ProductPage(driver);

        productPage.selectSortOption("Price (high to low)");

        Assert.assertEquals(
                driver.findElements(
                        org.openqa.selenium.By.className("inventory_item_price")
                ).get(0).getText(),
                "$7.99");
            
        }
}