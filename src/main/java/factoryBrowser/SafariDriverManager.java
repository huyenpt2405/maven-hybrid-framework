package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class SafariDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.safaridriver().setup();
		if (!IS_OS_MAC) {
			throw new BrowserNotSupportedException("Safari is not support on " + System.getProperty("os.name"));
		}
		
		SafariOptions options = new SafariOptions();
		
		return new SafariDriver(options);
	}

}
