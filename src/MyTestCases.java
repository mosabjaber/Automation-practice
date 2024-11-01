import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

	@Test(priority = 1, enabled = false)
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

	@Test(priority = 2,enabled=false)
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
	
	@Test(priority =3)
	public void select() {
		WebElement select= driver.findElement(By.id("dropdown-class-example"));
		Select selector = new Select(select);
		int randomSelect=rand.nextInt(1,4);
		selector.selectByIndex(randomSelect);
//		selector.selectByValue("API");;
//		selector.selectByValue("option3");

		
	}

}
