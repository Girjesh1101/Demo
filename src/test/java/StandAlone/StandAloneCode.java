package StandAlone;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StandAloneCode {

    public static void main(String[] args) {

        String url ="https://rahulshettyacademy.com/client/";
        String productName ="IPHONE 13 PRO";

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(url);
        driver.manage().window().maximize();

        WebElement userEmail = driver.findElement(By.id("userEmail"));
        userEmail.sendKeys("girjesh@gmail.com");

        WebElement password = driver.findElement(By.id("userPassword"));
        password.sendKeys("Girjesh@1");

        WebElement loginBtn = driver.findElement(By.id("login"));
        loginBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> allProduct=driver.findElements(By.cssSelector(".mb-3"));
        //searching product
        WebElement prod =allProduct.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);

        // after search click on cart

        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        // wait for black spot loading page
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        // wait for add to cart message
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        String cartMessage = driver.findElement(By.cssSelector("#toast-container")).getText();
        System.out.println(cartMessage);

        //click on cart

        WebElement cart =driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']"));
        cart.click();

        List<WebElement> allCartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean matchCartProduct = allCartProduct.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
        System.out.println(matchCartProduct);
        //Assert.assertTrue(matchCartProduct);

        WebElement checkoutBtn = driver.findElement(By.cssSelector(".subtotal button"));
        checkoutBtn.click();

        //checkoutPage
        //select Month
        Select selectMonths = new Select(driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[1]/form/div/div[2]/div[1]/select[1]")));
        selectMonths.selectByIndex(10);

        Select selectYear = new Select(driver.findElement(By.xpath("/html/body/app-root/app-order/section/div/div/div[2]/div/div/div[3]/div[1]/form/div/div[2]/div[1]/select[2]")));
        selectYear.selectByVisibleText("25");

        WebElement cvv = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
        cvv.sendKeys("123");

        WebElement coupon = driver.findElement(By.xpath("(//input[@type='text'])[4]"));
        coupon.sendKeys("rahulshetty");

        driver.findElement(By.xpath("//button[text()='Apply Coupon']")).click();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        String couponMessage = driver.findElement(By.xpath("//p[text()='* Invalid Coupon']")).getText();
        System.out.println(couponMessage);
    }
}
