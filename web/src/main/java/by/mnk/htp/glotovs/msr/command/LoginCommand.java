package by.mnk.htp.glotovs.msr.command;

import by.mnk.htp.glotovs.msr.logic.LoginCommandLogic;
import by.mnk.htp.glotovs.msr.resource.ConfigurationManager;
import by.mnk.htp.glotovs.msr.resource.MessageManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Sefire on 25.10.2016.
 */

public class LoginCommand implements ActionCommand {
    private static final String PARAM_PHONE_NUMBER= "phoneNumber";
    private static final String PARAM_PASSWORD = "password";

    public String execute(HttpServletRequest request) {
        String page = null;
        // извлечение из запроса логина(номера телефона) и пароля
        String phone = request.getParameter(PARAM_PHONE_NUMBER);
        String pass = request.getParameter(PARAM_PASSWORD);
        // проверка логина и пароля
        if (LoginCommandLogic.checkLogin(phone, pass)) {
            request.setAttribute("user", LoginCommandLogic.getFullName(phone));
            HttpSession session = request.getSession();
            session.setAttribute("phoneNumberSession",phone);
// определение пути к main.jsp
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
