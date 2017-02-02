package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

public class PropertyApp {

    public static final String PATH_AVATAR_UPLOAD = getPathStorage("PATH_AVATAR_UPLOADS");
    public static final String PATH_PRODUCT_IMAGE = getPathStorage("PATH_PRODUCT_IMAGE");

    public static String getPathStorage(String resourceName){
        String pathStorage = null;
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream =  PropertyApp.class.getClassLoader().getResourceAsStream("setting.properties");
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + "' not found in the classpath");
            }
            pathStorage = properties.getProperty(resourceName);
        } catch (Exception e){
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return pathStorage;
    }

}
