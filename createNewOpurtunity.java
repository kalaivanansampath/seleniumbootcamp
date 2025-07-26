package seleniumPractice;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class createNewOpurtunity {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		// Automatically downloads & sets path
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		try {
			driver.manage().window().maximize();
			
			
			

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			driver.get("https://login.salesforce.com");// TODO Auto-generated method stub

			WebElement userName = driver.findElement(By.xpath("//input[@type='email']"));
			WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
			WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));

			// Login to https://login.salesforce.com

			userName.sendKeys("bootCamp@testleaf.com");
			password.sendKeys("Sales@123");
			submit.click();

			WebElement toggleButton = driver.findElement(By.xpath("//button[@title='App Launcher']"));

			// Click on toggle menu button from the left corner
			toggleButton.click();

			WebElement viewAll = driver.findElement(By.xpath("//button[@aria-label='View All Applications']"));

			// Click view All and click Sales from App Launcher
			Thread.sleep(2000);
			viewAll.click();
			

			WebElement searchBox = driver.findElement(By.xpath("(//input[@type='search'])[2]"));
			searchBox.sendKeys("Sales");

			WebElement clickSales = driver.findElement(By.xpath("(//div[@class='slds-truncate'])[3]"));
			clickSales.click();
			Thread.sleep(1000);
			
			//Click on Opportunity tab
			WebElement opportunities = driver.findElement(By.xpath("//span[text()='Opportunities']"));
			js.executeScript("arguments[0].click();", opportunities);
			
			//Click on New button
			WebElement clickNew = driver.findElement(By.xpath("//a[@title='New']"));
			clickNew.click();
			
			
			WebElement save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
			WebElement oppName = driver.findElement(By.xpath("//input[@name='Name']"));
			WebElement closeDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
			WebElement stage = driver.findElement(By.xpath("(//button[@aria-haspopup='listbox'])[2]"));
			Random rand = new Random();
			int randomNum = rand.nextInt(1000);
			String expected="Salesforce Automation by Your Kalai"+randomNum;
			oppName.sendKeys(expected);
			
			closeDate.click();
			WebElement clickToday = driver.findElement(By.xpath("//button[text()='Today']"));
			clickToday.click();
			
			js.executeScript("arguments[0].click();", stage);
			//stage.click();
			WebElement selecctStage = driver.findElement(By.xpath("(//span[@class='slds-media__body'])[4]"));
			js.executeScript("arguments[0].click();", selecctStage);
			//selecctStage.click();
			js.executeScript("arguments[0].click();", save);
			//save.click();
			
			String actual = driver.findElement(By.xpath("//lightning-formatted-text[@slot='primaryField']")).getText();
			if (actual.equals(expected)) {
			    System.out.println("✅ Strings match");
			} else {
			    System.out.println("❌ Strings do not match");
			}
			
			

		}  finally {
			// Always closes the current tab
			driver.close();
		}

	}

}
