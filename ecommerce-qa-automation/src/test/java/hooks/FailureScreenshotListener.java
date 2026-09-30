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
    public void onTestSuccess(ITestResult result) {

        captureScreenshot(
                result,
                "Passed Test Screenshot"
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {

        captureScreenshot(
                result,
                "Failed Test Screenshot"
        );
    }

    private void captureScreenshot(
            ITestResult result,
            String screenshotName) {

        try {

            Object currentClass = result.getInstance();

            if (!(currentClass instanceof BaseTest)) {
                return;
            }

            WebDriver driver =
                    ((BaseTest) currentClass).driver;

            if (driver == null) {
                return;
            }

            byte[] screenshot =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(
                                    OutputType.BYTES
                            );

            Allure.addAttachment(
                    screenshotName,
                    "image/png",
                    new ByteArrayInputStream(screenshot),
                    ".png"
            );

        } catch (Exception e) {

            System.out.println(
                    "Screenshot capture failed: "
                            + e.getMessage()
            );
        }
    }
}