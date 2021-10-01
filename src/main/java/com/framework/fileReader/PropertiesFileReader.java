package com.framework.fileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
	
	private Properties properties;

	public PropertiesFileReader(String propertyFilePath) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties file not found at " + propertyFilePath);
		}
	}
	
	public String getProperty(String propName)
	{
		String propValue ="";
		try
		{
			propValue = properties.getProperty(propName);
		}catch(Exception e)
		{
			throw new RuntimeException("The property "+propName +" is not given in the property file");
		}
		
		return propValue;
	}

}
