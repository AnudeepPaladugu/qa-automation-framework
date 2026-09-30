package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.LoginPage;
import utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ConfigReader.loadProperties();

        String browser = ConfigReader.getProperty("browser");
        String applicationUrl = ConfigReader.getProperty("url");
        boolean headless = Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        ConfigReader.getProperty("headless")
                )
        );

        driver = createDriver(browser, headless);
        driver.manage().window().maximize();
        driver.get(applicationUrl);
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void loginAsStandardUser() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername(
                ConfigReader.getProperty("standard.username")
        );
        loginPage.enterPassword(
                ConfigReader.getProperty("standard.password")
        );
        loginPage.clickLogin();
    }

    private WebDriver createDriver(String browser, boolean headless) {
        if (!browser.equalsIgnoreCase("chrome")) {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        options.setExperimentalOption(
                "prefs",
                java.util.Map.of(
                        "credentials_enable_service", false,
                        "profile.password_manager_enabled", false,
                        "profile.password_manager_leak_detection", false
                )
        );

        return new ChromeDriver(options);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
