package Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import java.util.Random;

public class DemoTest2 {

    public WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor)driver;

    @Before
    public void setupDriver(){
        System.setProperty("webdriver.gecko.driver","C:\\Users\\SeleniumWebAutomation-master\\SeleniumAppDemo\\geckodriver.exe");
        driver = new FirefoxDriver();
        /* www.gittigidiyor.com sitesine erişim sağlanır. */
        String url = "https://www.gittigidiyor.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

    }
    @Test
    public void TestSearch() throws InterruptedException {

        /* Arama çubuğunda tıklanır ve 'Bilgisayar' ifadesi aratılır */
        WebElement searchBox = driver.findElement(By.className("itMXHg"));
        searchBox.click();
        searchBox.sendKeys("Bilgisayar");
        driver.findElement(By.className("hKfdXF")).click();

        /* Arama sonuç sayfasının sonuna doğru inilir ve ikinci sayfaya geçilir ve burda rastgele bir ürün seçilip üstüne tıklanır */
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,6750)", "");
        driver.findElement(By.cssSelector("#best-match-right > div.pager.pt30.hidden-m.gg-d-24 > ul > li:nth-child(2)")).click();

        List<WebElement> elements = driver.findElements(By.className("srp-item-list"));
        Thread.sleep(5000);
        Random rand = new Random();
        int upperbound = 48;
        //bir sayfada 48 ürün olduğundan dolayı 48 üründen rastgele bir ürün seçilir
        int int_random = rand.nextInt(upperbound);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(int_random));
        Thread.sleep(5000);
        elements.get(int_random).click();

        /* Seçilen ürünün fiyatı daha sonra karşılaştırma yapmak için bir değerin içinde saklanır */
        WebElement price= driver.findElement(By.xpath("//*[@id=\"sp-price-highPrice\"]"));
        String priceText= price.getText();

        /* Karşılaştırma işleminin doğru olup olmadığının kontrol edilmesi için yazılan kod

        WebElement buyitnow_adetBox = driver.findElement(By.id("buyitnow_adet"));
        buyitnow_adetBox.sendKeys("2"); */

        /* Ürün sayfasında aşağı inilir ve ürün sepete eklenir */

        JavascriptExecutor jsee = (JavascriptExecutor)driver;
        jsee.executeScript("window.scrollBy(0,270)", "");
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);

        driver.findElement(By.id("add-to-basket")).click();
        Thread.sleep(5000);

        /* Kontrol işlemi için sepete gidilir */
        driver.findElement(By.className("icon-sepet-line-wrapper")).click();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);

        /* Ürün sayfasındaki fiyat ile sepetteki fiyat karşılaştırlır */
        WebElement priceNew= driver.findElement(By.className("new-price"));
        String priceText2= priceNew.getText();

        if(priceText.compareTo(priceText2)<0){

            /* Sepetteki ürün fazla ise(yani fiyat karşılaştırılmasında uyuşmazlık varsa) silme işlemleri gerçekleştirilerek, ürün adetinde düzeltme gerçekleştirilir*/
            WebElement comparisonPrice = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/form/div/div[2]/div[2]/div/div/div[6]/div[2]/div[2]/div[1]/div[4]/div/div[1]/input"));
            comparisonPrice.click();
            comparisonPrice.clear();
            comparisonPrice.clear();
        }
        /* Sepetteki ürünlerin silinir */

        driver.findElement(By.className("gg-icon-bin-medium")).click();
        Thread.sleep(5000);

    }
    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        driver.quit();
    }
}