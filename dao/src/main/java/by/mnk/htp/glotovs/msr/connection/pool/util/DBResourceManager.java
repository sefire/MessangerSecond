package by.mnk.htp.glotovs.msr.connection.pool.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Sefire on 24.10.2016.
 */
public class DBResourceManager {
    private static final DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("database/db", Locale.ENGLISH);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }

}

