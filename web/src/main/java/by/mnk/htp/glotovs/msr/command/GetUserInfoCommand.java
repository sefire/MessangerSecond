package by.mnk.htp.glotovs.msr.command;

import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.logic.GetUserInfoCommandLogic;
import by.mnk.htp.glotovs.msr.logic.LoginCommandLogic;
import by.mnk.htp.glotovs.msr.resource.ConfigurationManager;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.factory.ServiceFactory;
import by.mnk.htp.glotovs.msr.services.factory.ServiceName;
import by.mnk.htp.glotovs.msr.services.impl.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by Sefire on 26.10.2016.
 */
public class GetUserInfoCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        String phoneFromSession = (String) session.getAttribute("phoneNumberSession");
        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceName.USER);
        UserEntity userEntity = userService.getUserEntityByPhone(phoneFromSession);
        try {
            //userService.create(userEntity);
            request.setAttribute("phone", phoneFromSession);
            request.setAttribute("firstName", userEntity.getFirstName());
            request.setAttribute("lastName", userEntity.getLastName());
            request.setAttribute("country", userEntity.getCountry());
            request.setAttribute("city", userEntity.getCity());
            request.setAttribute("age", userEntity.getAge());
            request.setAttribute("password", userEntity.getPassword());

            page = ConfigurationManager.getProperty("path.page.userinfo");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
    }
}
