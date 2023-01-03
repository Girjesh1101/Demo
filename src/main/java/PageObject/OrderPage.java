package PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import UtilityComponent.UtilityComponent;

public class OrderPage extends UtilityComponent {
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> cartProductName;
	
	
	public Boolean verifyOrderDisplay(String productName) {
		
		Boolean match= cartProductName.stream().anyMatch(product-> product.getText().equalsIgnoreCase(productName));
		return match;
	}
}
