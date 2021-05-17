package com.equifax.util;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
	public static Properties prop = null;
    static String filePath=null;
    public static Logger logger = Logger.getLogger(BaseClass.class.getName());
	public BaseClass() {
		filePath= System.getProperty("user.dir")+ "\\src\\main\\resources\\project.properties";
		loadProperties();
	}
	public void wait(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
 
	public static Properties loadProperties() {
		try {
			prop = new Properties();
			String path = filePath;
					//System.getProperty("user.dir") + "\\src\\main\\resources\\project.properties";
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
