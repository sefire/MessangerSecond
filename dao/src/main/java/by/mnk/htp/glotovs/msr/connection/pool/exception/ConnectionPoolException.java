package by.mnk.htp.glotovs.msr.connection.pool.exception;

/**
 * Created by Sefire on 24.10.2016.
 */
public class ConnectionPoolException extends Exception {

    public ConnectionPoolException(String message, Exception e) {
        super(message, e);
    }
}
