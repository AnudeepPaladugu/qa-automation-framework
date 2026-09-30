package hooks;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;
import io.qameta.allure.Allure;

public class FailureScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        attachScreenshot(result, "Failed Test Screenshot");
    }

    private void attachScreenshot(
            ITestResult result,
            String attachmentName) {

        try {
            Object testInstance = result.getInstance();

            if (!(testInstance instanceof BaseTest baseTest)) {
                return;
            }

            WebDriver driver = baseTest.getDriver();

            if (driver == null) {
                return;
            }

            byte[] screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.BYTES);

            Allure.addAttachment(
                    attachmentName,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );

        } catch (Exception exception) {
            System.err.println(
                    "Unable to capture failure screenshot: "
                            + exception.getMessage()
            );
        }
    }
}
