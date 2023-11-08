package commons;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

import factoryBrowser.BraveDriverManager;
import factoryBrowser.BrowserList;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.CocCocDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFirefoxDriverManager;
import factoryBrowser.SafariDriverManager;
import utilities.EnvironmentList;
import utilities.GlobalConstants;

public class BaseTest {
	private WebDriver driver;
	protected String projectPath = GlobalConstants.PROJECT_PATH;
	protected final Logger log;
	
	protected BaseTest() {
		log = LogManager.getLogger(getClass());
	}
	
	protected WebDriver openMultiBrowser(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browser) {
			case CHROME:
				driver = new ChromeDriverManager().getBrowserDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriverManager().getBrowserDriver();
				break;
			case EDGE:
				driver = new EdgeDriverManager().getBrowserDriver();
				break;
//			case SAFARI:
//				driver = new SafariDriverManager().getBrowserDriver();
//				break;
			case H_CHROME:
				driver = new HeadlessChromeDriverManager().getBrowserDriver();
				break;
			case H_FIREFOX:
				driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
				break;
			case COCCOC:
				driver = new CocCocDriverManager().getBrowserDriver();
				break;
			case BRAVE:
				driver = new BraveDriverManager().getBrowserDriver();
				break;
			default:
				throw new BrowserNotSupportedException(browserName);
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(GlobalConstants.USER_PORTAL_URL);
		return driver;
	}
	
	protected WebDriver openMultiBrowserByAppUrl(String browserName, String appUrl) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browser) {
			case CHROME:
				driver = new ChromeDriverManager().getBrowserDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriverManager().getBrowserDriver();
				break;
			case EDGE:
				driver = new EdgeDriverManager().getBrowserDriver();
				break;
			case SAFARI:
				driver = new SafariDriverManager().getBrowserDriver();
				break;
			case H_CHROME:
				driver = new HeadlessChromeDriverManager().getBrowserDriver();
				break;
			case H_FIREFOX:
				driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
				break;
			case COCCOC:
				driver = new CocCocDriverManager().getBrowserDriver();
				break;
			case BRAVE:
				driver = new BraveDriverManager().getBrowserDriver();
				break;
			default:
				throw new BrowserNotSupportedException(browserName);
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}
	
	protected WebDriver openMultiBrowser(String browserName, String env) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browser) {
			case CHROME:
				driver = new ChromeDriverManager().getBrowserDriver();
				break;
			case FIREFOX:
				driver = new FirefoxDriverManager().getBrowserDriver();
				break;
			case EDGE:
				driver = new EdgeDriverManager().getBrowserDriver();
				break;
			case SAFARI:
				driver = new SafariDriverManager().getBrowserDriver();
				break;
			case H_CHROME:
				driver = new HeadlessChromeDriverManager().getBrowserDriver();
				break;
			case H_FIREFOX:
				driver = new HeadlessFirefoxDriverManager().getBrowserDriver();
				break;
			case COCCOC:
				driver = new CocCocDriverManager().getBrowserDriver();
				break;
			case BRAVE:
				driver = new BraveDriverManager().getBrowserDriver();
				break;
			default:
				throw new BrowserNotSupportedException(browserName);
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(getEnvironmentUrl(env));
		return driver;
	}
	
	protected WebDriver openMultiBrowserBrowserStack(String browserName, String appUrl, String osName, String osVersion) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("os", osName);
		capabilities.setCapability("os_version", osVersion);
		capabilities.setCapability("browser", browserName);
		capabilities.setCapability("browser_version", "latest");
		capabilities.setCapability("name", "Run on " + osName + " | " + osVersion + " | " + browserName);
		capabilities.setCapability("browserstack.local", "false");
		capabilities.setCapability("resolution", "1920x1080");
		capabilities.setCapability("browserstack.selenium_version", "3.141.59");

		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSERSTACK_URL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("failed");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;
	}
	
	public String getEnvironmentUrl(String env) {
		String envUrl;
		EnvironmentList enviroment = EnvironmentList.valueOf(env.toUpperCase());
		switch (enviroment) {
		case DEV:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case TEST:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case PRODUCTION:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case STAGING:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		case PRE_PRODUCTION:
			envUrl = "https://demo.nopcommerce.com/";
			break;
		default:
			envUrl = null;
			break;
		}
		return envUrl;
	}
	
	
	public WebDriver getWebdriver() {
		return this.driver;
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	

	protected int generateFakeNumber() {
		Random random = new Random();
		return random.nextInt(99999);
	}
	
	public long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}
	
	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			String driverInstanceName = driver.toString().toLowerCase();
			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
