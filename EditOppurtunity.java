package seleniumPractice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOppurtunity {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		// Automatically downloads & sets path
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions actions = new Actions(driver);
		
		
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
		Thread.sleep(1000);
		viewAll.click();
		

		WebElement searchBox = driver.findElement(By.xpath("(//input[@type='search'])[2]"));
		searchBox.sendKeys("Sales");

		WebElement clickSales = driver.findElement(By.xpath("(//div[@class='slds-truncate'])[3]"));
		clickSales.click();
		Thread.sleep(1000);
		
		//Click on Opportunity tab
		WebElement opportunities = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		js.executeScript("arguments[0].click();", opportunities);
		
		//click dropdown Icon
		WebElement clickDropDown = driver.findElement(By.xpath("(//span[text()='Kalai'])/ancestor::th/following::td[6]"));
		clickDropDown.click();
		
		//clickEdit Icon
		WebElement clickEdit = driver.findElement(By.xpath("//a[@title='Edit']"));
		clickEdit.click();
		//a[@title="Edit"]
		
		
		WebElement closeDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		closeDate.click();
		
		WebElement clickToday = driver.findElement(By.xpath("//button[text()='Today']"));
		
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formatted = tomorrow.format(formatter);
		closeDate.clear();
		closeDate.sendKeys(formatted);
		
		
		WebElement stage = driver.findElement(By.xpath("(//button[@aria-haspopup='listbox'])[3]"));
		actions.moveToElement(stage).perform();
		js.executeScript("arguments[0].click();", stage);
		
		WebElement stagePerceeption = driver.findElement(By.xpath("(//span[@title='Perception Analysis'])"));
		js.executeScript("arguments[0].click();", stagePerceeption);
		
		WebElement clickStatus = driver.findElement(By.xpath("//button[@aria-label='Delivery/Installation Status']"));
		js.executeScript("arguments[0].click();", clickStatus);
		WebElement clickInprogress = driver.findElement(By.xpath("//lightning-base-combobox-item[@data-value=\"In progress\"]"));
		js.executeScript("arguments[0].click();", clickInprogress);
		
		WebElement description = driver.findElement(By.xpath("//textarea[@part=\"textarea\"]"));
		description.sendKeys("SalesForce");
		
		WebElement save = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		js.executeScript("arguments[0].click();", save);
		
		String actual = driver.findElement(By.xpath("(//span[text()='Kalai'])/ancestor::th/following::td[3]")).getText();
		String expected = "Perception Analysis";
		if (actual.equals(expected)) {
		    System.out.println("✅ Stage match");
		} else {
		    System.out.println("❌ Stage do not match");
		}
		
	}
}
