package magento;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends Parameters {

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void MySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(priority = 1)
	public void SignUp() {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.findElement(By.linkText("Create an Account")).click();

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement passInput = driver.findElement(By.id("password"));
		WebElement confirmPassInput = driver.findElement(By.id("password-confirmation"));
		WebElement emailInput = driver.findElement(By.id("email_address"));

		WebElement createAccBtn = driver.findElement(By.cssSelector("button[title='Create an Account'] span"));

		firstNameInput.sendKeys(firstNames[rndIndex]);
		lastNameInput.sendKeys(lastNames[rndIndex]);
		passInput.sendKeys(commonPass);
		confirmPassInput.sendKeys(commonPass);
		emailInput.sendKeys(email);

		createAccBtn.click();

		String wlcMsg = driver.findElement(By.className("message-success")).getText();

		assertEquals(wlcMsg, "Thank you for registering with Main Website Store.");
	}

	@Test(priority = 2, enabled = false)
	public void Logout() {
		driver.get("https://magento.softwaretestingboard.com/customer/account/logout/");
		assertEquals(driver.getCurrentUrl().contains("logoutSuccess"), true);
	}

	@Test(priority = 3, enabled = false)
	public void SignIn() throws InterruptedException {

		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(commonPass);

		driver.findElement(By.id("send2")).click();

		Thread.sleep(2000);

		assertEquals(driver.findElement(By.className("logged-in")).getText()
				.contains("Welcome, " + firstNames[rndIndex] + " " + lastNames[rndIndex] + "!"), true);
	}

	@Test(priority = 4)
	public void AddOneRndItem() throws InterruptedException {
		driver.findElement(By.className("logo")).click();
		List<WebElement> items = driver.findElement(By.cssSelector(".product-items.widget-product-grid"))
				.findElements(By.tagName("li"));

		System.out.println(items);

		Random rndItem = new Random();
		
		items.get(rndItem.nextInt(4)).click();

		WebElement itemSizes = driver.findElement(By.cssSelector(".swatch-attribute.size"));
		int sizesNum = itemSizes.findElements(By.className("swatch-option")).size();
		itemSizes.findElements(By.className("swatch-option")).get(rndItem.nextInt(sizesNum)).click();
		
		Thread.sleep(2000);
		
		WebElement itemColors = driver.findElement(By.cssSelector(".swatch-attribute.color"));
		int colorsNum = itemColors.findElements(By.className("swatch-option")).size();
		itemColors.findElements(By.className("swatch-option")).get(rndItem.nextInt(colorsNum)).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("product-addtocart-button")).click();
		
		Thread.sleep(2000);
		
		assertEquals(driver.findElement(By.cssSelector(".page.messages")).getText().contains("You added"), true);
		
	}
}
