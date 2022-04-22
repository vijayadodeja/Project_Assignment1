package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AmazonHomePage {
	//Webdriver intance
	WebDriver driver;

	//Constructor
	public AmazonHomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchBox;
	
	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	WebElement searchButton;
	
	@FindBy(xpath = "(//span[@class='a-price-whole'])[1]")
	WebElement price;
	
	@FindBy(xpath = "(//div[contains(@class, 's-title-instructions-style')])[1]//a")
	WebElement productTitle;
	
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
	
	public void clickProductTitle()
	{
		productTitle.click();
	}
	
	public String getProductTitle()
	{
		return productTitle.getText();
	}
}
