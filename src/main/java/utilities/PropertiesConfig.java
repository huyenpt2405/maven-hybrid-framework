package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
	private final Properties properties;
	private final String propertyFilePath = GlobalConstants.getGlobalConstants().getProjectPath() + "/resources/config.properties";
	
	private static PropertiesConfig configLoader;
	
	private PropertiesConfig() {
		properties = PropertiesConfig.propertyLoader(propertyFilePath);
	}
	
	private static Properties propertyLoader(String propertyFilePath) {
		Properties properties = new Properties();
		BufferedReader reader;
		
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load properties file " + propertyFilePath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration properties not found at " + propertyFilePath);
		}
		return properties;
	}
	
	public static PropertiesConfig getFileConfigReader() {
		return configLoader != null ? new PropertiesConfig() : configLoader;
	}
	
	public long getShortTimeout() {
		String shortTimeout = properties.getProperty("shortTimeOut");
		if (shortTimeout != null) {
			return Long.parseLong(shortTimeout);
		} else {
			throw new RuntimeException("Short timeout not found in config file");
		}
	}
	
	public long getLongTimeout() {
		String longTimeout = properties.getProperty("longTimeOut");
		if (longTimeout != null) {
			return Long.parseLong(longTimeout);
		} else {
			throw new RuntimeException("Long timeout not found in config file");
		}
	}
	
	public long getUserUrl() {
		String userUrl = properties.getProperty("userUrl");
		if (userUrl != null) {
			return Long.parseLong(userUrl);
		} else {
			throw new RuntimeException("User url not found in config file");
		}
	}
	
	public long getAdminUrl() {
		String adminUrl = properties.getProperty("adminUrl");
		if (adminUrl != null) {
			return Long.parseLong(adminUrl);
		} else {
			throw new RuntimeException("Admin url not found in config file");
		}
	}
}
