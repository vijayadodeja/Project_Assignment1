package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlipKartHomePage {
	WebDriver driver;
	
	public FlipKartHomePage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//button[contains(text(),'✕')]")
	WebElement loginCloseButton;
	
	@FindBy(xpath = "//input[@placeholder='Search for products, brands and more']")
	WebElement searchBox;
	
	@FindBy(xpath = "//button[@type='submit']")
	WebElement searchButton;
	
	@FindBy(xpath = "(//span[contains(text(), 'Reviews')]/parent::span/parent::span/parent::div/parent::div/parent::div/parent::a/parent::div/parent::div/parent::div/parent::div/parent::div//div[contains(@class, 'row')]/div[2]/div[1]/div[1]/div[1])[1]")
	WebElement price;
	
	@FindBy(xpath = "(//span[contains(text(), 'Reviews')]/parent::span/parent::span/parent::div/parent::div/parent::div/parent::a/parent::div/parent::div/parent::div/parent::div/parent::div//div[contains(@class, 'row')]/div[1]/div[1])[1]")
	WebElement productTitle;
	
	public void clickLoginCloseButton()
	{
		loginCloseButton.click();
	}
	
	public void setSearchItem(String sItem)
	{
		searchBox.sendKeys(sItem);
	}

	public void clickSearchButton() 
	{
		searchButton.click();
	}

	public String getPrice() 
	{
		return price.getText();
	}
	
	public String getProductTitle()
	{
		return productTitle.getText();
	}
}
