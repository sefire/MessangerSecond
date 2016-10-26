package by.mnk.htp.glotovs.msr.services.interfaces;

import by.mnk.htp.glotovs.msr.entities.UserEntity;

import java.util.List;

/**
 * Created by Sefire on 25.10.2016.
 */
public interface IUserService extends IService<UserEntity, Integer> {

    //for startpage
    UserEntity getUserEntityByPhone(String phone);
    UserEntity getUserEntityById(int idUser);
    List<UserEntity> getUserEntitiesByFNandLN(String firstName, String lastName);
    String getUserEntityPasswordByPhone(String phone);
    int getUserIdByPhone(String phone);
    String changeUserEntityCountryById(int idUser);
    String changeUserEntityCityById(int idUser);
    int changeUserEntityAgeById(int idUser);
    String changeUserEntityPasswordById(int idUser);
}
