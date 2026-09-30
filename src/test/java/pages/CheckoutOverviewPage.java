package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productName =
            By.cssSelector(".cart_item .inventory_item_name");
    private final By itemTotal =
            By.className("summary_subtotal_label");
    private final By tax =
            By.className("summary_tax_label");
    private final By total =
            By.className("summary_total_label");
    private final By finishButton =
            By.id("finish");
    private final By backHomeButton =
            By.cssSelector("[data-test='back-to-products']");
    private final By confirmationMessage =
            By.cssSelector("#checkout_complete_container h2.complete-header");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(productName)
        ).getText();
    }

    public String getItemTotal() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(itemTotal)
        ).getText();
    }

    public String getTax() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(tax)
        ).getText();
    }

    public String getTotal() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(total)
        ).getText();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }

    public String getConfirmationMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(confirmationMessage)
        ).getText();
    }

    public void clickBackHome() {
        wait.until(
                ExpectedConditions.elementToBeClickable(backHomeButton)
        ).click();

        wait.until(ExpectedConditions.urlContains("inventory.html"));
    }
}
