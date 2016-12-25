package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyApp {

    public String getPathStorage(){
        String pathStorage = null;
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream =  getClass().getClassLoader().getResourceAsStream("setting.properties");
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + "' not found in the classpath");
            }
            pathStorage = properties.getProperty("PATH_STORAGE");
            //System.out.println("PATH_PROP = " + pathStorage);
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
