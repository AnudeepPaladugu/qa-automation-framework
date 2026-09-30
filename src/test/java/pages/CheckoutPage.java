package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private final WebDriverWait wait;

    private final By checkoutButton =
            By.xpath("//button[@name='checkout']");
    private final By firstNameField =
            By.id("first-name");
    private final By lastNameField =
            By.id("last-name");
    private final By postalCodeField =
            By.id("postal-code");
    private final By continueButton =
            By.id("continue");
    private final By errorMessage =
            By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickCheckout() {
        clickWhenReady(checkoutButton);
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField));
    }

    public void enterFirstName(String firstName) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameField)
        ).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCodeField)
        ).sendKeys(postalCode);
    }

    public void clickContinue() {
        clickWhenReady(continueButton);
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }

    private void clickWhenReady(By locator) {
        wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        ).click();
    }
}
