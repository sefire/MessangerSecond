package by.mnk.htp.glotovs.msr.logic;

import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.factory.ServiceFactory;
import by.mnk.htp.glotovs.msr.services.factory.ServiceName;
import by.mnk.htp.glotovs.msr.services.impl.UserService;

/**
 * Created by Sefire on 25.10.2016.
 */

public class LoginCommandLogic {

    private static String PHONEFROMBD = "";
    private static String PASSWORDFROMBD = "";

    public static boolean checkLogin(String phoneFromUser, String passFromUser) {

        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceName.USER);
        UserEntity user = userService.getUserEntityByPhone(phoneFromUser);

        PHONEFROMBD = user.getPhone();
        PASSWORDFROMBD = user.getPassword();
        if (PHONEFROMBD == null || PASSWORDFROMBD == null) return false;
        return PHONEFROMBD.equals(phoneFromUser) &&
                PASSWORDFROMBD.equals(passFromUser);
    }

    public static String getFullName(String phoneFromUser)
    {
        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceName.USER);
        UserEntity user = userService.getUserEntityByPhone(phoneFromUser);
        return user.getFirstName() + " " + user.getLastName();
    }
}
