package com.automation.tests;

import com.automation.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sample Test Class - Demonstrates how to write tests using BaseTest
 */
public class SampleTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SampleTest.class);

    /**
     * Test 1: Verify Google homepage title
     */
    @Test(description = "Test to verify Google homepage title")
    public void testGoogleHomepageTitle() {
        logger.info("Starting test: testGoogleHomepageTitle");
        
        // Navigate to Google
        navigateTo("https://www.google.com");
        
        // Get page title
        String pageTitle = getPageTitle();
        logger.info("Page title: " + pageTitle);
        
        // Assert
        Assert.assertTrue(pageTitle.contains("Google"), "Page title should contain 'Google'");
        logger.info("Test passed: Page title contains 'Google'");
    }

    /**
     * Test 2: Verify Google URL
     */
    @Test(description = "Test to verify Google URL")
    public void testGoogleUrl() {
        logger.info("Starting test: testGoogleUrl");
        
        // Navigate to Google
        navigateTo("https://www.google.com");
        
        // Get current URL
        String currentUrl = getCurrentUrl();
        logger.info("Current URL: " + currentUrl);
        
        // Assert
        Assert.assertTrue(currentUrl.contains("google.com"), "URL should contain 'google.com'");
        logger.info("Test passed: URL contains 'google.com'");
    }

    /**
     * Test 3: Verify search box is present
     */
    @Test(description = "Test to verify Google search box is present")
    public void testSearchBoxPresence() {
        logger.info("Starting test: testSearchBoxPresence");
        
        // Navigate to Google
        navigateTo("https://www.google.com");
        
        // Check if search box is displayed
        By searchBoxLocator = By.name("q");
        
        try {
            driver.findElement(searchBoxLocator).isDisplayed();
            logger.info("Search box is present on the page");
            Assert.assertTrue(true, "Search box is present");
        } catch (Exception e) {
            logger.error("Search box not found", e);
            Assert.fail("Search box should be present on Google homepage");
        }
    }
}
