package KasirAja;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class LoginDDT {
    // Login menggunakan fitur Data Drive Test (DDT)
    @Test
    public void login_ddt(){
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseURL = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager
        ChromeOptions opt = new ChromeOptions();
        opt.setHeadless(false);

        String csvDir = System.getProperty("user.dir")+ "/src/test/data/test-data.csv";

        try(CSVReader reader = new CSVReader(new FileReader(csvDir))){ // try read csv data
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null){
                String email = nextLine[0]; // read column 1 for email
                String password = nextLine[1]; // read column 2 for password
                String status = nextLine[2]; // read column 3 for expected login status

                driver = new ChromeDriver(opt); // apply chrome driver setup to driver
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); // set timeout for web driver on waiting element
                driver.manage().window().maximize(); // maximize window
                driver.get(baseURL); // access base url
                driver.findElement(By.id("email")).sendKeys(email);
                driver.findElement(By.id("password")).sendKeys(password);
                driver.findElement(By.xpath("//button[@type='submit']")).click();

                if (status.equals("success")){
                    driver.findElement(By.xpath("//div[contains(text(),'dashboard')]"));
                    String username = driver.findElement(By.xpath("//dd[contains(text(),'hai')]/preceding-sibling::dt")).getText();
                    Assert.assertEquals(username, "ttd-selenium");
                } else{
                    String errorLogin = driver.findElement(By.xpath("//div[@role='alert']")).getText();
                    Assert.assertEquals(errorLogin, "Kredensial yang Anda berikan salah");
                }
            }
        } catch (CsvValidationException | IOException e){
            throw new RuntimeException(e);
        }

    }

}
