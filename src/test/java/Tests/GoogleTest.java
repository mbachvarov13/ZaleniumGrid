package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleTest {

    RemoteWebDriver driver;
    DesiredCapabilities dc;
    public static String GRID_URL = "http://localhost:4444/wd/hub";
    public static String APPLICATION_URL = "http://google.com";

    @BeforeTest
    @Parameters("browser")
    void setup(String browser) throws MalformedURLException {
        dc = new DesiredCapabilities();
        if (browser.equals("chrome")) {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        } else if (browser.equals("firefox")) {
            dc.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
        }
        dc.setCapability(CapabilityType.PLATFORM_NAME, Platform.LINUX);

        URL url = new URL(GRID_URL);

        driver = new RemoteWebDriver(url, dc);

        driver.get(APPLICATION_URL);
    }

    @Test
    void googleTest() {
        driver.findElement(By.xpath("//span[contains(text(),'Приемам')]")).click();
        driver.findElement(By.xpath("//input[@title='Търсене']")).sendKeys("Zalenium");
        driver.findElement(By.xpath("//div[@jsname='VlcLAe']//input[@value='Google Търсене']")).click();
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }
}
