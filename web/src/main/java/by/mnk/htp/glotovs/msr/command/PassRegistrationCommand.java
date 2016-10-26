package by.mnk.htp.glotovs.msr.command;

import by.mnk.htp.glotovs.msr.command.ActionCommand;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.logic.LoginCommandLogic;
import by.mnk.htp.glotovs.msr.resource.ConfigurationManager;
import by.mnk.htp.glotovs.msr.resource.MessageManager;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;
import by.mnk.htp.glotovs.msr.services.factory.ServiceFactory;
import by.mnk.htp.glotovs.msr.services.factory.ServiceName;
import by.mnk.htp.glotovs.msr.services.impl.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Sefire on 26.10.2016.
 */
public class PassRegistrationCommand implements ActionCommand  {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        UserEntity userEntity = new UserEntity();
        userEntity.setPhone((String)request.getParameter("phone"));
        userEntity.setFirstName((String)request.getParameter("firstname"));
        userEntity.setLastName((String)request.getParameter("laststname"));
        userEntity.setCountry((String)request.getParameter("country"));
        userEntity.setCity((String)request.getParameter("city"));
        userEntity.setAge((Integer.valueOf(request.getParameter("age"))));
        userEntity.setPassword((String)request.getParameter("password"));

        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceName.USER);
        try {
            userService.create(userEntity);
            page = ConfigurationManager.getProperty("path.page.afterRegistration");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return page;
    }
}