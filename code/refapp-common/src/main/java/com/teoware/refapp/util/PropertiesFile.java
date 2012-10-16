package com.teoware.refapp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertiesFile extends Properties {

	private static final long serialVersionUID = 1L;

	public void loadFromFile(String fileName) throws FileNotFoundException, IOException {
		super.load(new FileInputStream(fileName));
	}

	public void loadFromClasspath(String fileName) throws IOException {
		super.load(this.getClass().getResourceAsStream(fileName));
	}

	public void loadUsingClassLoader(String fileName) throws IOException {
		URL url =  ClassLoader.getSystemResource(fileName);
		if (url != null) {
			super.load(new FileInputStream(new File(url.getFile())));
		}
	}

	public String get(String key) {
		return super.getProperty(key);
	}

	public String get(String key, String defaultValue) {
		return super.getProperty(key, defaultValue);
	}

	public int getInt(String key) {
		return new Integer(super.getProperty(key));
	}

	public int getInt(String key, int defaultValue) {
		return new Integer(super.getProperty(key, new Integer(defaultValue).toString()));
	}

	public boolean getBoolean(String key) {
		return new Boolean(super.getProperty(key));
	}

	public boolean getBoolean(String key, boolean defaultValue) {
		return new Boolean(super.getProperty(key, new Boolean(defaultValue).toString()));
	}

	public static PropertiesFile createFromFile(String fileName) throws FileNotFoundException, IOException {
		PropertiesFile properties = new PropertiesFile();
		properties.loadFromFile(fileName);
		return properties;
	}

	public static PropertiesFile createFromClasspath(String fileName) throws IOException {
		PropertiesFile properties = new PropertiesFile();
		properties.loadFromClasspath(fileName);
		return properties;
	}

	public static PropertiesFile createUsingClassLoader(String fileName) throws IOException {
		PropertiesFile properties = new PropertiesFile();
		properties.loadUsingClassLoader(fileName);
		return properties;
	}
}
