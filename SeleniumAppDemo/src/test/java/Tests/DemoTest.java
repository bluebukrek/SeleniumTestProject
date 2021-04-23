package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
public class DemoTest {

    public  WebDriver driver;

    @Before
    public void setupDriver(){

        System.setProperty("webdriver.gecko.driver","C:\\Users\\SeleniumWebAutomation-master\\SeleniumAppDemo\\geckodriver.exe");
        driver = new FirefoxDriver();
        /* www.gittigidiyor.com sitesine erişim sağlanır. */
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test
    public void TestHome(){
        /* giriş kısmının ikonuna tıklanır ve giriş yap butonuna tıklanır */
        WebElement locationbuttton= driver.findElement(By.cssSelector("#main-header > div:nth-child(3) > div > div > div.sc-1yvp483-0.jUYNgf > div.sc-1nx8ums-0.fQSWwJ > div > div:nth-child(1) > div > div.gekhq4-0.koEZsC > div > div"));
        locationbuttton.click();

        WebElement loginbutton= driver.findElement(By.className("kNKwwK"));
        loginbutton.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        /* kullanıcıadı çubuğuna tıklanır ve kullanıcı adı girilir ve şifre çubuğuna tıklanır ve şifre girilir */
        WebElement usernamebox= driver.findElement(By.id("L-UserNameField"));
        usernamebox.click();
        usernamebox.sendKeys("eren199781@gmail.com");

        WebElement passwordbox = driver.findElement(By.id("L-PasswordField"));
        passwordbox.click();
        passwordbox.sendKeys("123456eren");
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.id("gg-login-enter")).click();

    }
    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();
    }
}

