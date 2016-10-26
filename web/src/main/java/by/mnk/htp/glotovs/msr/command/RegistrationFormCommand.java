package by.mnk.htp.glotovs.msr.command;

import by.mnk.htp.glotovs.msr.resource.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sefire on 26.10.2016.
 */
public class RegistrationFormCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        String page = null;
        page = ConfigurationManager.getProperty("path.page.registrationForm");
        return page;
    }
}

