import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases {

	WebDriver driver = new ChromeDriver();
	String websiteURL = "https://codenboxautomationlab.com/practice/#top";
	Random rand = new Random();

	@BeforeTest
	public void mysetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(websiteURL);
	}

	@Test(priority = 1)
	public void radioButtons() {

		// select one random radio button

		WebElement divForRadio = driver.findElement(By.id("radio-btn-example"));

		int randomIndex = rand.nextInt(divForRadio.findElements(By.tagName("input")).size());

		WebElement selectedInput = divForRadio.findElements(By.tagName("input")).get(randomIndex);

		selectedInput.click();

		boolean actualResult = selectedInput.isSelected();
		boolean expectedResult = true;

		Assert.assertEquals(actualResult, expectedResult);

	}

	@Test(priority = 2)
	public void dynamicDropDown() throws InterruptedException {
		String[] myRandomTwoCharacter = { "AB", "EA", "GH", "IJ", "KL", "MO", "OP" };
		int randomIndex = rand.nextInt(myRandomTwoCharacter.length);
		String myInputdata = myRandomTwoCharacter[randomIndex];

		WebElement autocompleteInput = driver.findElement(By.id("autocomplete"));

		autocompleteInput.sendKeys(myInputdata);
		Thread.sleep(1000);
		autocompleteInput.sendKeys(Keys.chord(Keys.ARROW_DOWN, Keys.ENTER));

//		js code cause extract data from feild input	
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String dataValue = (String) js.executeScript("return arguments[0].value", autocompleteInput);
		String updateData = dataValue.toUpperCase();
		boolean actual = updateData.contains(myInputdata);
//		System.out.println(updateData + " ****** " + myInputdata);
		Assert.assertEquals(actual, true);

		// sol 2
//		String val=autocompleteInput.getAttribute("value");
//		System.out.println(val.isEmpty());

	}

	@Test(priority = 3)
	public void select() {
		WebElement select = driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(select);
		int randomSelect = rand.nextInt(1, 4);
		selector.selectByIndex(randomSelect);
//		selector.selectByValue("API");;
//		selector.selectByValue("option3");

	}

	@Test(priority = 4)
	public void checkBox() {

		List<WebElement> checkBox = driver.findElement(By.id("checkbox-example")).findElements(By.tagName("input"));

		for (int i = 0; i < checkBox.size(); i++) {
			checkBox.get(i).click();
			boolean expected = true;
			Assert.assertEquals(checkBox.get(i).isSelected(), expected);
		}

	}

	@Test(priority = 5)
	public void openWindow() throws InterruptedException {
		driver.findElement(By.id("openwindow")).click();
		Thread.sleep(1000);
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		// switch to open window
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);

		driver.findElement(By.id("menu-item-9675")).click();
		Thread.sleep(3000);
		driver.switchTo().window(windowHandles.get(1)).close();
		// back to the main page
		driver.switchTo().window(windowHandles.get(0));

	}

	@Test(priority = 6)
	public void switchTabs() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.id("opentab")).click();
		Thread.sleep(1000);
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);
		String actual = driver.getTitle();
		String expected = "Codenbox AutomationLab - YouTube";
		Assert.assertEquals(actual, expected);
		Thread.sleep(3000);
		driver.switchTo().window(windowHandles.get(1)).close();
		driver.switchTo().window(windowHandles.get(0));

	}

	@Test(priority = 7)
	public void switchALERT() throws InterruptedException {
		Thread.sleep(3000);
//		********alert*********
//		driver.findElement(By.id("alertbtn")).click();
//		Thread.sleep(2000);
//		driver.switchTo().alert().accept();

//		********confirm*******
		// to dismiss the confirm alert ( to cancel it )
		// driver.switchTo().alert().dismiss();
		driver.findElement(By.id("name")).sendKeys("mosab");
		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(2000);
		boolean actual = driver.switchTo().alert().getText().contains("mosab");
		boolean expected = true;
		Assert.assertEquals(actual, expected);
		driver.switchTo().alert().accept();
	}

	@Test(priority = 8)
	public void TableExample() throws InterruptedException {
		Thread.sleep(3000);
		WebElement table = driver.findElement(By.id("product"));
		List<WebElement> coursename = table.findElements(By.tagName("td"));
		for (int i = 1; i < coursename.size(); i += 3) {
			System.out.println(coursename.get(i).getText());
		}

	}

	@Test(priority = 9)
	public void elementDisplayed() throws InterruptedException {
		Thread.sleep(3000);
//		hide textbox
		driver.findElement(By.id("hide-textbox")).click();
		boolean actual = driver.findElement(By.id("displayed-text")).isDisplayed();
		boolean expected = false;
		Assert.assertEquals(actual, expected);
		Thread.sleep(2000);
//		show textbox
		driver.findElement(By.id("show-textbox")).click();
		boolean actual2 = driver.findElement(By.id("displayed-text")).isDisplayed();
		boolean expected2 = true;
		Assert.assertEquals(actual2, expected2);

	}

	@Test(priority = 10)
	public void enabled_Disabled() throws InterruptedException {
		Thread.sleep(3000);
		// disableButton
		WebElement disableButton = driver.findElement(By.id("disabled-button"));
		disableButton.click();
		WebElement inputField = driver.findElement(By.id("enabled-example-input"));
		boolean ActualResult = inputField.isEnabled();
		boolean ExpectedResult = false;
		Assert.assertEquals(ActualResult, ExpectedResult);
		// EnableButton
		WebElement EnableButton = driver.findElement(By.id("enabled-button"));
		EnableButton.click();
		boolean ActualResult2 = inputField.isEnabled();
		boolean ExpectedResult2 = true;
		Assert.assertEquals(ActualResult2, ExpectedResult2);

	}

	@Test(priority = 11)
	public void mouseHover() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1800)");

		Actions action = new Actions(driver);

		WebElement theTarget = driver.findElement(By.id("mousehover"));
		action.moveToElement(theTarget).perform();

		driver.findElement(By.linkText("Reload")).click();
	}

	@Test(priority = 12)
	public void calendar() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.linkText("Booking Calendar")).click();
		List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(windowHandles.get(1));
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		Thread.sleep(3000);
		driver.switchTo().window(windowHandles.get(1)).close();
		driver.switchTo().window(windowHandles.get(0));
	}

	@Test(priority = 13)
	public void iFrame() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,2500)");

		WebElement myFrame = driver.findElement(By.id("courses-iframe"));

		Thread.sleep(2000);
		driver.switchTo().frame(myFrame);
		js.executeScript("window.scrollTo(0,10000)");
		Thread.sleep(1000);
		driver.findElement(By.linkText("About us")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Home")).click();

	}

}
