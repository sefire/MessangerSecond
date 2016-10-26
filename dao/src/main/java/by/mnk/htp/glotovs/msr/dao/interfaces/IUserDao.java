package by.mnk.htp.glotovs.msr.dao.interfaces;

import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sefire on 24.10.2016.
 */
public interface IUserDao extends IDao<UserEntity, Integer> {
    //public interface IUserDao<UserEntity extends IEntity, Integer extends Serializable> extends IDao<UserEntity, Integer> {

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
