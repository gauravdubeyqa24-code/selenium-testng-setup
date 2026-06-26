package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

/**
 * Base Page Class - Contains common methods for all page objects
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);
    private static final int WAIT_TIMEOUT = 10;

    /**
     * Constructor - Initialize WebDriver and WebDriverWait
     *
     * @param driver WebDriver instance
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT));
    }

    /**
     * Click on element
     *
     * @param locator By locator
     */
    public void click(By locator) {
        logger.info("Clicking on element: " + locator);
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    /**
     * Send keys to element
     *
     * @param locator By locator
     * @param text Text to send
     */
    public void sendKeys(By locator, String text) {
        logger.info("Sending keys to element: " + locator + " with text: " + text);
        WebElement element = waitForPresenceOfElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Get text from element
     *
     * @param locator By locator
     * @return Element text
     */
    public String getText(By locator) {
        logger.info("Getting text from element: " + locator);
        WebElement element = waitForPresenceOfElement(locator);
        return element.getText();
    }

    /**
     * Check if element is displayed
     *
     * @param locator By locator
     * @return true if displayed, false otherwise
     */
    public boolean isElementDisplayed(By locator) {
        logger.info("Checking if element is displayed: " + locator);
        try {
            WebElement element = waitForPresenceOfElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            logger.warn("Element not displayed: " + locator);
            return false;
        }
    }

    /**
     * Wait for element to be present
     *
     * @param locator By locator
     * @return WebElement
     */
    protected WebElement waitForPresenceOfElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for element to be clickable
     *
     * @param locator By locator
     * @return WebElement
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Wait for element to be visible
     *
     * @param locator By locator
     * @return WebElement
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Get attribute value
     *
     * @param locator By locator
     * @param attribute Attribute name
     * @return Attribute value
     */
    public String getAttributeValue(By locator, String attribute) {
        logger.info("Getting attribute: " + attribute + " from element: " + locator);
        WebElement element = waitForPresenceOfElement(locator);
        return element.getAttribute(attribute);
    }

    /**
     * Get current page URL
     *
     * @return Current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get page title
     *
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}
