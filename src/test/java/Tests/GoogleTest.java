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
    public static String APPLICATION_URL = "https://opensource.zalando.com/zalenium/";

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
        dc.setCapability("name", "googleTest");

        URL url = new URL(GRID_URL);

        driver = new RemoteWebDriver(url, dc);

        driver.get(APPLICATION_URL);
    }

    @Test
    void googleTest() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href='#why']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='#try-it']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='#features']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='#docker']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='#docker-swarm']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[@href='#kubernetes']")).click();
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }
}
