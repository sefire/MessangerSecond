package by.mnk.htp.glotovs.msr.logic;

import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.factory.ServiceFactory;
import by.mnk.htp.glotovs.msr.services.factory.ServiceName;
import by.mnk.htp.glotovs.msr.services.impl.UserService;

/**
 * Created by Sefire on 26.10.2016.
 */
public class GetUserInfoCommandLogic {

    public static UserEntity getUserInfoByPhone(String phone)
    {
        UserService userService = (UserService) ServiceFactory.getInstance().getService(ServiceName.USER);
        UserEntity user = userService.getUserEntityByPhone(phone);
        return  user;
    }
}
