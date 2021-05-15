package com.epam.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public final class PropertyDataReader {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static Properties properties;

    private PropertyDataReader() {
    }

    public static Properties getProperties(final String propertyFileName) {
        try (FileReader fileReader = new FileReader("src/test/resources/" + propertyFileName + ".properties")) {
            properties = new Properties();
            properties.load(fileReader);
        } catch (IOException e) {
            LOGGER.error("Can't read {}.properties file", propertyFileName);
        }
        return properties;
    }
}
