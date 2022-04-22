package pageObjects;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePageTest {
	WebDriver driver;
	AmazonHomePage amazonHomePage;
	FlipKartHomePage flipKartHomePage;
		
	@BeforeClass
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void testSearch() throws InterruptedException, ParseException
	{
		String product = "Apple iPhone 11 (64GB) - Yellow";

		// Amazon
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		amazonHomePage = new AmazonHomePage(driver);
		amazonHomePage.setSearchItem(product);
		amazonHomePage.clickSearchButton();
		
		String productTitle = amazonHomePage.getProductTitle(); 
		if (!productTitle.contains("iPhone 11") || !productTitle.contains("64") || !productTitle.contains("Yellow"))
		{
			System.out.println("Amazon does not have such a product.");
			return;
		}
		
		String priceText = amazonHomePage.getPrice();
		System.out.println(priceText);
		
		NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
		Number number = format.parse(priceText);
		double amazonPrice = number.doubleValue();

		// Flipkart
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		flipKartHomePage = new FlipKartHomePage(driver);
		
		flipKartHomePage.clickLoginCloseButton();
		flipKartHomePage.setSearchItem(product);
		flipKartHomePage.clickSearchButton();
		
		productTitle = flipKartHomePage.getProductTitle(); 
		if (!productTitle.contains("iPhone 11") || !productTitle.contains("64") || !productTitle.contains("Yellow"))
		{
			System.out.println("Flipkart does not have such a product.");
			return;
		}
		
		priceText = flipKartHomePage.getPrice().replace("₹", "");
		System.out.println(priceText);
		
		format = NumberFormat.getInstance(Locale.getDefault());
		number = format.parse(priceText);
		double flipkartPrice = number.doubleValue();

		if (amazonPrice == flipkartPrice)
			System.out.println("Same price");
		else if (amazonPrice < flipkartPrice)
			System.out.println("Amazon is cheaper.");
		else if (amazonPrice > flipkartPrice)
			System.out.println("Flipkart is cheaper.");
	}
	
	@AfterClass
	public void teardown()
	{
		driver.quit();
	}
}
