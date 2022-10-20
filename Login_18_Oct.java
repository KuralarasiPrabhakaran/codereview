package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {

	@Test(dataProvider = "credentials")
	public void verifyLoginCredentials(String scenario, String username, String Password) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");

		driver.findElement(By.id("user-name")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(Password);
		driver.findElement(By.id("login-button")).click();

		if (scenario.equals("bothwrong")) {

			String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"))
					.getText();
			Assert.assertEquals(errorMessage,
					"Epic sadface: Username and password do not match any user in this service");
		}

		else if (scenario.equals("EmptyUsername")) {

			String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"))
					.getText();
			Assert.assertEquals(errorMessage, "Epic sadface: Username is required");
		}

		else if (scenario.equals("Emptypassword")) {

			String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"))
					.getText();
			Assert.assertEquals(errorMessage, "Epic sadface: Password is required");
		}

		else if (scenario.equals("bothEmpty")) {

			String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"))
					.getText();
			Assert.assertEquals(errorMessage, "Epic sadface: Username is required");

		} else if (scenario.equals("wrongusername")) {

			String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"))
					.getText();
			Assert.assertEquals(errorMessage,
					"Epic sadface: Username and password do not match any user in this service");
		} else {

			String errorMessage = driver.findElement(By.xpath("//div[@class='error-message-container error']"))
					.getText();
			Assert.assertEquals(errorMessage,
					"Epic sadface: Username and password do not match any user in this service");
		}

		driver.quit();
	}

	@DataProvider(name = "credentials")
	public Object[][] getData() {

		return new Object[][] {

				{ "bothWrong", "abc", "Abc@123" }, { "EmptyUsername", "", "secret_sauce", },
				{ "Emptypassword", "standard_user", "" }, { "bothEmpty", "", "" },
				{ "wrongusername", "abc", "secret_sauce" }, { "wrongPassword", "standard_user", "Abc@123" },

		};
	}
}
