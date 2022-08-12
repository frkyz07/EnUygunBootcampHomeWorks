package org.example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

class SearchBoxTest {

    // now we are creating a method
    @Test
    public void SearchBoxTest() throws InterruptedException {

        // we need to set our system properties for opening our browser
        System.setProperty("webdriver.chrome.driver",
                System.getProperty("user.dir") + "\\chromedriver.exe");
        // we are creating a driver object to open our browser
        ChromeDriver driver = new ChromeDriver();
        // i want to see my chrome window in the maximize size
        driver.manage().window().maximize();
        // we are need to give the path that we want to go
        driver.get("https://demoqa.com/elements");
        // after i run the i always get the "click intercepted error"
        // in order the fix the error i scroll down the page
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,250)");

        // we give the weatable's link to click
        driver.findElement(By.xpath("//div//ul//li[@id='item-3'][1]")).click();
        // in here we are giving the searchbox's xpath to the assertTrue assertion for comparing if it is displayed or not
        assertTrue(driver.findElement(By.id("searchBox")).isDisplayed());

    }
}