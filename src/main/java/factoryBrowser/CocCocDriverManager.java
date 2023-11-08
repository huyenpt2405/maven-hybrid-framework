package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CocCocDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.chromedriver().driverVersion("versionchrome - 6").setup();
		ChromeOptions options = new ChromeOptions();
		options.setBinary("C:\\...");
		return new ChromeDriver(options);
	}
	
}
