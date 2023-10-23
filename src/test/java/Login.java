import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Login {
    @Test // add test tag from Junit as runner to run the test
    public void open_browser(){
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseURL = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager

        driver = new ChromeDriver(); // apply chrome driver setup to driver
        driver.manage().window().maximize(); // maximize window
        driver.get(baseURL); // access base url
        String title = driver.getTitle();
        System.out.println(title);

        driver.close();
    }

    @Test // add test tag from Junit as runner to run the test
    public void get_title(){
        WebDriver driver; // set driver for test using webdriver from selenium
        String baseURL = "https://kasirdemo.belajarqa.com/"; // set base url

        WebDriverManager.chromedriver().setup(); // setup chrome driver automatically using web driver manager

        driver = new EdgeDriver(); // apply edge driver setup to driver
        driver.manage().window().maximize(); // maximize window
        driver.get(baseURL); // access base url
        String title = driver.getTitle();
        System.out.println(title);

        driver.close();
    }
}
