package by.mnk.htp.glotovs.msr.resource;

import java.util.ResourceBundle;

/**
 * Created by Sefire on 25.10.2016.
 */
public class ConfigurationManager {
    private final static ResourceBundle resourceBundle =
            ResourceBundle.getBundle("config");
    private ConfigurationManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
