package KasirAja;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.w3c.dom.html.HTMLImageElement;

import java.util.concurrent.TimeUnit;

public class Login {
    @Test
    public void success_login_case(){
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseURL = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager

        driver = new ChromeDriver(); // apply chrome driver setup to driver
        driver.manage().window().maximize(); // maximize window
        driver.get(baseURL); // access base url

        // Assert hai, kasirAja in login page
        String LoginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
        Assert.assertEquals(LoginPageAssert,"hai, kasirAja");

        // input email
        driver.findElement(By.id("email")).sendKeys("admin@jaya.com");
        // input password
        driver.findElement(By.id("password")).sendKeys("123456");
        // click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // quit
        driver.close();
    }

    @Test
    public void failed_login_case(){
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseURL = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager

        driver = new ChromeDriver(); // apply chrome driver setup to driver
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize(); // maximize window
        driver.get(baseURL); // access base url

        // Assert hai, kasirAja in login page
        String LoginPageAssert = driver.findElement(By.xpath("//h2[contains(text(),'hai, kasirAja')]")).getText();
        Assert.assertEquals(LoginPageAssert,"hai, kasirAja");

        // input email
        driver.findElement(By.id("email")).sendKeys("admin@jaya.com");
        // input wrong password
        driver.findElement(By.id("password")).sendKeys("www123456");
        // click login
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Assert error message
        String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
        Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");

        // quit
        driver.close();
    }

}
