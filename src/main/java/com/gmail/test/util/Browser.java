package com.gmail.test.util;

/*
 * Singleton bean representing a browser. It contains name, version and platform fields.
 */
public class Browser {

	private static Browser instance = null;
	private String name;
	private String version;
	private String platform;

	private Browser(){
		name = PropertyLoader.loadProperty("browser.name");
		version = PropertyLoader.loadProperty("browser.version");
		platform = PropertyLoader.loadProperty("browser.platform");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public static Browser getInstance(){
		if (instance == null) {
			instance = new Browser();
		}
		return instance;
	}
}