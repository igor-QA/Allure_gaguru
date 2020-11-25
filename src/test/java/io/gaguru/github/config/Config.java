package io.gaguru.github.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static class Init {
        private static final Config conf = new Config();
    }
    private Config() {}

    public static Config config() {
        return Init.conf;
    }
    public String getLoginPage(){
        return getProperty("login");
    }

    public String getRepository() {
        return getProperty("repository");
    }

    public String getUserName() {
        return getProperty("user");
    }

    public String getPassword() {
        return getProperty("password");
    }
    private String getProperty(String key) {
        String property = "";
        InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            property = properties.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return property;
    }
}





