package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriverWait wait;

    private By cartButton =
            By.className("shopping_cart_link");

    private By checkoutButton =
            By.xpath("//button[@name='checkout']");

    private By firstNameField =
            By.id("first-name");

    private By lastNameField =
            By.id("last-name");

    private By postalCodeField =
            By.id("postal-code");

    private By continueButton =
            By.id("continue");

    private By errorMessage =
            By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {

        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void openCart() {

        wait.until(
                ExpectedConditions.elementToBeClickable(cartButton)
        ).click();
    }

    public void clickCheckout() {

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-one.html"
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstNameField
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastNameField
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        postalCodeField
                )
        );
    }

    public void enterFirstName(String firstName) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        firstNameField
                )
        ).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        lastNameField
                )
        ).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        postalCodeField
                )
        ).sendKeys(postalCode);
    }

    public void clickContinue() {

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueButton
                )
        ).click();
    }

    public String getErrorMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();
    }
}