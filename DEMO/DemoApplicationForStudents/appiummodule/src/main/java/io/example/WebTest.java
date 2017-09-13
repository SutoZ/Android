package io.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WebTest {
	private static final String APPIUM_SERVER_URL =  "http://127.0.0.1:4723/wd/hub";

	protected AndroidDriver<WebElement> driver;

	@Before
	public void setup() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus_7.0");
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		driver = new AndroidDriver(new URL(APPIUM_SERVER_URL), capabilities);
	}

	@Test
	public void webTest() throws InterruptedException {
		driver.get("https://www.epam.com/");
		driver.findElement(By.className("header-hamburger-ui")).click();
		driver.findElement(By.cssSelector("li:nth-child(6)")).click();
		Thread.sleep(3000);
	}

	@After
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
