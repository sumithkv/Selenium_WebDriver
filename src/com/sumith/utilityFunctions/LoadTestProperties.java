package com.sumith.utilityFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadTestProperties {
    
    public static String getProperty(String PropName) {
        
        Properties prop = new Properties();
        InputStream input = null;
        String PropValue = null;
     
        try {
     
            input = new FileInputStream("D:/Workspace/MyStudy_Space/SeleniumWebDriver/ConfigFiles/TestProperties.properties");
     
            // load a properties file
            prop.load(input);
     
            // get the property value and print it out
            PropValue = prop.getProperty(PropName);
    
            // Printing all the elements in the Property file   
            /*
            Enumeration<?> e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println("Key is: " + key + ", Value is: " + value);
            }
            
            */
     
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
              }
                   
     return PropValue;
      }
    
    
    


}
