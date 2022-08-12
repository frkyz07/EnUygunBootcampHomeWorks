import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utilities.src.FilePath;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class Driver {

    // we are creating a Webdriver public and a Properties variable to use in our java class
    public WebDriver driver;
    public Properties prop;

    public WebDriver getDriver() throws IOException {

        //
        // we are starting to check if our properties is empty or not
        if (driver == null) {
            // we are getting our broweser properties
            switch (FilePath.getProperty("browser")) {
                // if it is chrome it will open chrome browser
                case "chrome":
                    // we are setting our chrome browser here
                    WebDriverManager.chromedriver().setup();
                    // and we are creating our browser here
                    driver = new ChromeDriver();
                    // and here we are stoping the condition if it is true
                    break;

                    // rest of the browsers has the same logic

                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                    // in here in case if we want to run our code in headless mode we are that option giving to the driver
                case "headless-chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
                default:
                    // in here we run chrome as a default browser
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
            driver.manage().window().maximize();

        }
        return driver;
        }

    }


