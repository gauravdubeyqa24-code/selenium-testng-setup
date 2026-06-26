package com.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

/**
 * Base Test Class - Contains common setup and teardown for all test classes
 */
public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private static final int IMPLICIT_WAIT = 10;
    private static final int PAGE_LOAD_TIMEOUT = 15;

    /**
     * Setup method - Initializes WebDriver before each test
     *
     * @param browser Browser type (chrome, firefox, edge)
     */
    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        logger.info("Initializing WebDriver for browser: " + browser);
        
        driver = initializeDriver(browser);
        
        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        
        // Set page load timeout
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        
        // Maximize window
        driver.manage().window().maximize();
        
        logger.info("WebDriver initialized successfully");
    }

    /**
     * Initialize WebDriver based on browser type
     *
     * @param browser Browser name
     * @return WebDriver instance
     */
    private WebDriver initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    /**
     * Teardown method - Closes WebDriver after each test
     */
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing WebDriver");
            driver.quit();
        }
    }

    /**
     * Navigate to URL
     *
     * @param url URL to navigate to
     */
    public void navigateTo(String url) {
        logger.info("Navigating to: " + url);
        driver.navigate().to(url);
    }

    /**
     * Get page title
     *
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     *
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
