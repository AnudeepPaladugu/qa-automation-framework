package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOverviewPage {

    private WebDriver driver;

    private By productName =
            By.cssSelector(".cart_item .inventory_item_name");

    private By itemTotal =
            By.className("summary_subtotal_label");

    private By tax =
            By.className("summary_tax_label");

    private By total =
            By.className("summary_total_label");

    private By finishButton =
            By.id("finish");

    private By backHomeButton =
            By.cssSelector("[data-test='back-to-products']");
    
    private By checkoutCompleteContainer =
            By.id("checkout_complete_container");

    private By confirmationMessage =
            By.cssSelector("#checkout_complete_container h2.complete-header");
    
    private WebDriverWait wait;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getItemTotal() {
        return driver.findElement(itemTotal).getText();
    }

    public String getTax() {
        return driver.findElement(tax).getText();
    }

    public String getTotal() {
        return driver.findElement(total).getText();
    }
    public void clickFinish() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.urlContains("checkout-step-two.html")
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public String getConfirmationMessage() {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        checkoutCompleteContainer
                )
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmationMessage
                )
        ).getText();
    }

    public void clickBackHome() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        backHomeButton
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "inventory.html"
                )
        );
    }
}