package BaseTest;

import PageObject.CartPage;
import PageObject.CheckoutPage;
import PageObject.LandingPage;
import PageObject.ProductCatalogues;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static void main(String[] args) {

        String url  ="https://rahulshettyacademy.com/client/";

        String productName= "ADIDAS ORIGINAL";

        WebDriverManager.chromedriver().setup();
        WebDriver driver= new ChromeDriver();

        driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        LandingPage lp= new LandingPage(driver);
        lp.Url(url);
        lp.loginApplication("girjesh@gmail.com","Girjesh@1");
       // ProductCatalogues productCatalogues = LandingPage.loginApplication("","");
       ProductCatalogues productCatalogues = new ProductCatalogues(driver);
        productCatalogues.getProductList();
        productCatalogues.getProductByName();
        productCatalogues.addCart();
        CartPage cartPage = new CartPage(driver);
       // cartPage.allProductInCart();
        cartPage.matchProduct(productName);
        cartPage.goToCheckout();
        CheckoutPage checkoutPage= new CheckoutPage(driver);
        //checkoutPage.FormDetails("girjesh");
        checkoutPage.selectCountry("ind");
        checkoutPage.submitOrder();

    }
}
