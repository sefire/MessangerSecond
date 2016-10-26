package by.mnk.htp.glotovs.msr.services.impl;

import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.factory.DaoImplFactory;
import by.mnk.htp.glotovs.msr.dao.factory.DaoImplName;
import by.mnk.htp.glotovs.msr.dao.impl.UserDaoImpl;
import by.mnk.htp.glotovs.msr.entities.UserEntity;
import by.mnk.htp.glotovs.msr.services.interfaces.IUserService;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sefire on 25.10.2016.
 */

public class UserService implements IUserService{

    //private UserDaoImpl userDao;
    public List getAll() throws ServiceException {
        return null;
    }

    public UserEntity read(Integer id) throws ServiceException {
        try {
            UserDaoImpl userDaoImpl = (UserDaoImpl) DaoImplFactory.getInstance().getDaoImpl(DaoImplName.USER);
            return userDaoImpl.read(id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void create(UserEntity object) throws ServiceException {
        UserDaoImpl userDao = (UserDaoImpl) DaoImplFactory.getInstance().getDaoImpl(DaoImplName.USER);
        try {
            userDao.create(object);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    public void update(UserEntity object) throws ServiceException {

    }

    public void delete(UserEntity object) throws ServiceException {

    }

    public UserEntity getUserEntityByPhone(String phone) {
        UserDaoImpl userDao = (UserDaoImpl) DaoImplFactory.getInstance().getDaoImpl(DaoImplName.USER);
        return userDao.getUserEntityByPhone(phone);
    }

    public UserEntity getUserEntityById(int idUser) {
        return null;
    }

    public List<UserEntity> getUserEntitiesByFNandLN(String firstName, String lastName) {
        return null;
    }

    public String getUserEntityPasswordByPhone(String phone) {
        return null;
    }

    public int getUserIdByPhone(String phone) {
        return 0;
    }

    public String changeUserEntityCountryById(int idUser) {
        return null;
    }

    public String changeUserEntityCityById(int idUser) {
        return null;
    }

    public int changeUserEntityAgeById(int idUser) {
        return 0;
    }

    public String changeUserEntityPasswordById(int idUser) {
        return null;
    }
}
