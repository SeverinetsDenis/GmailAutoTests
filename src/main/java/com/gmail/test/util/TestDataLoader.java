package com.gmail.test.util;

import java.io.IOException;
import java.util.Properties;

/*
 * Class that loads test data for different environment from the file.
 */
public class TestDataLoader {

    private static final String PROP_FILE = "/testdata.properties";

    public static String loadTestData(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertyLoader.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = "";
        String environment = PropertyLoader.loadProperty("env");

        if (name != null) {
            value = props.getProperty(environment + "." + name);
        }
        return value;
    }

}
