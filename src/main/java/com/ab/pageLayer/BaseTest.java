package com.ab.pageLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {

	Properties pro;
	public WebDriver driver;

	public WebDriver initialiseBrowser() {

		String configPath = System.getProperty("user.dir") + "\\src\\test" + "\\resources\\configs\\config.properties";
		pro = new Properties();

		try {
			FileInputStream fis = new FileInputStream(configPath);
			pro.load(fis);

		} catch (Exception e) {

			e.printStackTrace();
		}

		String browserName = pro.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			driver = new ChromeDriver();
		}
		if (browserName.equalsIgnoreCase("edge")) {

			driver = new EdgeDriver();
		}
		if (browserName.equalsIgnoreCase("firefox")) {

			driver = new FirefoxDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		return driver;
		
      }
	
	@BeforeMethod
	public void launchApplication() {
		
		driver = initialiseBrowser();
		
		driver.get(pro.getProperty("url"));
			
	}
	
	
	

}
