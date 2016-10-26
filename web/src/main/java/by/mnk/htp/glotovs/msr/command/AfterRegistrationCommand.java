package by.mnk.htp.glotovs.msr.command;

import by.mnk.htp.glotovs.msr.logic.LoginCommandLogic;
import by.mnk.htp.glotovs.msr.resource.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Sefire on 26.10.2016.
 */
public class AfterRegistrationCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String phone = (String) session.getAttribute("phoneNumberSession");

        request.setAttribute("user", LoginCommandLogic.getFullName(phone));
        session.setAttribute("phoneNumberSession", phone);

        page = ConfigurationManager.getProperty("path.page.main");
        return page;
    }
}
