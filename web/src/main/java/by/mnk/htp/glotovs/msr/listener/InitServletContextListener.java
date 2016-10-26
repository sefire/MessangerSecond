package by.mnk.htp.glotovs.msr.listener;

import by.mnk.htp.glotovs.msr.connection.pool.ConnectionPool;
import by.mnk.htp.glotovs.msr.connection.pool.exception.ConnectionPoolException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Sefire on 25.10.2016.
 */
@WebListener
public class InitServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().initPoolData();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.getInstance().dispose();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }
}
