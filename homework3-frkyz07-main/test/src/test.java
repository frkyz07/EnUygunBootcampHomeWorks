import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public abstract class test extends Driver {


    @BeforeTest
    // in here before even the test starts we are giving the profile to open with profile
    public void AddProfile(){
        // in here we are creating a google chrome option to give options that we want to the our chrome driver
        ChromeOptions options = new ChromeOptions();
        // we are adding the argument that we want it
        // addimg my edge profile path to the options method
        options.addArguments("user-data-dir=C:\\Users\\frkyz\\AppData\\Local\\Google\\Chrome\\User Data\\Default");
        options.addArguments("--start-maximized");
        // giving that options to the chromedriver object
        driver = new ChromeDriver(options);
    }
    @Test
    public void openGoogle(){
        // here a basic test

        driver.get(prop.getProperty("url"));
        driver.findElement(By.id("q")).sendKeys("Something");
        driver.findElement(By.xpath("//div//input[@name='btnK']")).click();


    }
    @AfterTest
    public void killIt(){
        driver.close();
    }
}
