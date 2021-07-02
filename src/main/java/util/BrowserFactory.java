package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
static WebDriver driver;
static String browser; 
static String url;


public static void readconfig() {
	Properties prop = new Properties();
	try {
		InputStream input = new FileInputStream(".\\src\\main\\java\\config.properties");
		prop.load(input);
		browser = prop.getProperty("browser");
		System.out.println("Browser Used " + browser);
		url = prop.getProperty("url");
	} catch (IOException e) {
		e.printStackTrace();
	}

	}
	
	public static WebDriver init() {
readconfig();
		if (browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
	} else if (browser.equalsIgnoreCase("Firefox")) {
		System.setProperty("webdriver.gecko.driver", "driver\\geckodriver.exe");
		driver = new FirefoxDriver();
	}

	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get("https://techfios.com/billing/?ng=login/");
	return driver;
}

public static void teardown() {
driver.close();
driver.quit();
}
}
