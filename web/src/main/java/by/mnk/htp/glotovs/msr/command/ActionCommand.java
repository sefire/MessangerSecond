package by.mnk.htp.glotovs.msr.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sefire on 25.10.2016.
 */
public interface ActionCommand {
    String execute(HttpServletRequest request);
}
