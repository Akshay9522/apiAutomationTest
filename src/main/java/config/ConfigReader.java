package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
     
	private static Properties properties;
	
	static {
		
		try{
			properties = new Properties();
			File file = new File("C:\\Users\\Admin\\testAutomation\\apiAutomationTest\\src\\test\\resources\\config.properties");
			FileInputStream fis = new FileInputStream(file);
			properties.load(fis);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
