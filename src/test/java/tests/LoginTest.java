package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("standard.username")
        );
        loginPage.enterPassword(
                ConfigReader.getProperty("standard.password")
        );
        loginPage.clickLogin();

        Assert.assertTrue(
                driver.getCurrentUrl().endsWith("/inventory.html"),
                "User should be redirected to the inventory page."
        );
    }

    @Test
    public void verifyInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("invalid.username")
        );
        loginPage.enterPassword(
                ConfigReader.getProperty("invalid.password")
        );
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service"
        );
    }

    @Test
    public void verifyEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword(
                ConfigReader.getProperty("standard.password")
        );
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username is required"
        );
    }

    @Test
    public void verifyEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("standard.username")
        );
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Password is required"
        );
    }

    @Test
    public void verifyLockedOutUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("locked.username")
        );
        loginPage.enterPassword(
                ConfigReader.getProperty("standard.password")
        );
        loginPage.clickLogin();

        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out."
        );
    }
}
