package com.teoware.refapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Set;

public class PropertiesFile extends Properties {

	private static final long serialVersionUID = 1L;

	public void loadFromFile(String fileName) throws IOException {
		loadProperties(new FileInputStream(fileName));
	}

	public void loadFromClasspath(String fileName) throws IOException {
		loadProperties(this.getClass().getResourceAsStream(fileName));
	}

	public void loadUsingClassLoader(String fileName) throws IOException {
		URL url = ClassLoader.getSystemResource(fileName);
		if (url == null) {
			throw new FileNotFoundException("File '" + fileName
					+ "' could not be found.");
		}
		loadProperties(new FileInputStream(new File(url.getFile())));
	}

	private void loadProperties(InputStream is) throws IOException {
		super.load(is);
		is.close();
		prosessProperties();
	}

	private void prosessProperties() {
		Set<Object> keySet = super.keySet();
		Object[] keys = keySet.toArray();
		for (int i = 0; i < keys.length; i++) {
			Object key1 = keys[i];
			Object value1 = super.get(key1);
			String var = "${" + key1 + "}";
			loop2: for (int j = 0; j < keys.length; j++) {
				Object key2 = keys[j];
				String value2 = super.get(key2).toString();
				if (value2.contains(var)) {
					super.remove(key2);
					super.put(key2, value2.replace(var, value1.toString()));
					i = 0;
					break loop2;
				}
			}
		}
	}

	public String get(String key) {
		return super.getProperty(key);
	}

	public String get(String key, String defaultValue) {
		return super.getProperty(key, defaultValue);
	}

	public Integer getInteger(String key) {
		return new Integer(get(key));
	}

	public Integer getInteger(String key, Integer defaultValue) {
		return new Integer(get(key, defaultValue.toString()));
	}

	public Long getLong(String key) {
		return new Long(get(key));
	}

	public Long getLong(String key, Long defaultValue) {
		return new Long(get(key, defaultValue.toString()));
	}

	public Float getFloat(String key) {
		return new Float(get(key));
	}

	public Float getFloat(String key, Float defaultValue) {
		return new Float(get(key, defaultValue.toString()));
	}

	public Double getDouble(String key) {
		return new Double(get(key));
	}

	public Double getDouble(String key, Double defaultValue) {
		return new Double(get(key, defaultValue.toString()));
	}

	public Boolean getBoolean(String key) {
		return new Boolean(get(key));
	}

	public Boolean getBoolean(String key, Boolean defaultValue) {
		return new Boolean(get(key, defaultValue.toString()));
	}

	public static PropertiesFile createFromFile(String fileName)
			throws FileNotFoundException, IOException {
		PropertiesFile properties = new PropertiesFile();
		properties.loadFromFile(fileName);
		return properties;
	}

	public static PropertiesFile createFromClasspath(String fileName)
			throws IOException {
		PropertiesFile properties = new PropertiesFile();
		properties.loadFromClasspath(fileName);
		return properties;
	}

	public static PropertiesFile createUsingClassLoader(String fileName)
			throws IOException {
		PropertiesFile properties = new PropertiesFile();
		properties.loadUsingClassLoader(fileName);
		return properties;
	}
}
