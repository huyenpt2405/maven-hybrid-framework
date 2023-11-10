package utilities;

import java.io.File;

import lombok.Getter;

@Getter
public class GlobalConstants {
	private static GlobalConstants globalInstance;
	
	private GlobalConstants() { }
	
	public static synchronized GlobalConstants getGlobalConstants() {
		if (globalInstance == null) {
			return new GlobalConstants();
		}
		return globalInstance;
	}

	private final String userPortalUrl = "https://demo.nopcommerce.com/";
	private final String adminUrl = "https://admin-demo.nopcommerce.com/";
	
	private final String techpandaAdminUrl = "http://live.techpanda.org/index.php/backendlogin/customer/";
	
	private final String projectPath = System.getProperty("user.dir");
	private final String uploadFilesPath = projectPath + File.separator + "uploadFiles" + File.separator;
	private final String downFilesPath = projectPath + File.separator + "downloadFiles";
	private final String browserLogFilePath = projectPath + File.separator + "browserLogs";
	private final String dragDropPath = projectPath + File.separator + "dragDropHTML5" + File.separator;
	private final String testingScreenshotPath = projectPath + File.separator + "ReportNGScreenShots" + File.separator;
	private final String extentReportV2Path = projectPath + File.separator + "ExtentReportV2" + File.separator + "ExtentReport.html";
	private final long longTimeOut = 30;
	private final long shortTimeOut = 5;
	private final long retryTestFailed = 5;
	private final String extentPath = projectPath + "File.separator";
	private final String javaVersion = System.getProperty("java.version");

	
	// Database Account/ User/ Pass/ Port
	private final String dbDevUrl = "192.146.54.32:8080";
	private final String dbDevUser = "huyenpt";
	private final String dbDevPass = "P@ssw0rd!";
	
	private final String dbTestUrl = "192.146.54.33:8080";
	private final String dbTestUser = "huyenpt";
	private final String dbTestPass = "P@ssw0rd!";
	
	private final String browserStackUsername = "huynphm_tgNvqW";
	private final String browserStackAccessKey = "81ig1uQSRzzsSwj2vWXy";
	private final String browserStackUrl = "https://" + browserStackUsername + ":" + browserStackAccessKey + "@hub.browserstack.com/wd/hub";
}
