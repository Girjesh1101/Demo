package TestComponent;


import PageObject.*;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SubmitOrderTest extends  BrowserLaunch{

    @Test
    public void sumbitOrder() throws IOException {
       // LandingPage landingPage=launchingApplication();

        ProductCatalogues productCatalogues= landingPage.loginApplication("girjesh@gmail.com","Girjesh@1");
        List<WebElement> products = productCatalogues.getProductList();
        productCatalogues.addCart();
        CartPage cartPage = productCatalogues.cart();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmPage confirmPage = checkoutPage.submitOrder();
        String confirmMessage = confirmPage.confirm();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));



    }
}
