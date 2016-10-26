package by.mnk.htp.glotovs.msr.dao.impl;

import by.mnk.htp.glotovs.msr.connection.pool.ConnectionPool;
import by.mnk.htp.glotovs.msr.connection.pool.exception.ConnectionPoolException;
import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.dao.interfaces.IUserDao;
import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.entities.UserEntity;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 * Created by Sefire on 24.10.2016.
 */


public class UserDaoImpl implements IUserDao {

    public List getAll() {
        return null;
    }

    public void create(UserEntity userEntity) throws DaoException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // opening database connection to MySQL server
            try {
                connection = ConnectionPool.getInstance().takeConnection();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
            // getting Statement object to execute query
            preparedStatement = connection.prepareStatement("INSERT INTO user(phone, firstName, lastName, country, city, age, password) VALUES(?, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, userEntity.getPhone());
            preparedStatement.setString(2, userEntity.getFirstName());
            preparedStatement.setString(3, userEntity.getLastName());
            preparedStatement.setString(4, userEntity.getCountry());
            preparedStatement.setString(5, userEntity.getCity());
            preparedStatement.setInt(6, userEntity.getAge());
            preparedStatement.setString(7, userEntity.getPassword());
            preparedStatement.execute();

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                connection.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                preparedStatement.clearParameters();
                preparedStatement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    public UserEntity read(Integer id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserEntity userEntity = new UserEntity();

        try {
            // получение connection
            try {
                connection = ConnectionPool.getInstance().takeConnection();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
            String query = "SELECT * FROM user WHERE idUser = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userEntity.setId(resultSet.getInt("idUser"));
                userEntity.setPhone(resultSet.getString("phone"));
                userEntity.setFirstName(resultSet.getString("firstName"));
                userEntity.setLastName(resultSet.getString("lastName"));
                userEntity.setCountry(resultSet.getString("country"));
                userEntity.setCity(resultSet.getString("city"));
                userEntity.setAge(resultSet.getInt("age"));
                userEntity.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                connection.close();
            } catch (SQLException se) {
                /*can't do anything */
            }
            try {
                preparedStatement.close();
            } catch (SQLException se) {
                    /*can't do anything */
            }
            try {
                resultSet.close();
            } catch (SQLException se) {
                    /*can't do anything */
            }
        }
        return userEntity;
    }

    public UserEntity getUserEntityByPhone(String phone) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        UserEntity userEntity = new UserEntity();


        try {
            // получение connection
            try {
                connection = ConnectionPool.getInstance().takeConnection();
            } catch (ConnectionPoolException e) {
                e.printStackTrace();
            }
            String query = "SELECT * FROM user WHERE phone = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phone);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userEntity.setId(resultSet.getInt("idUser"));
                userEntity.setPhone(resultSet.getString("phone"));
                userEntity.setFirstName(resultSet.getString("firstName"));
                userEntity.setLastName(resultSet.getString("lastName"));
                userEntity.setCountry(resultSet.getString("country"));
                userEntity.setCity(resultSet.getString("city"));
                userEntity.setAge(resultSet.getInt("age"));
                userEntity.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                connection.close();
            } catch (SQLException se) {
                /*can't do anything */
            }
            try {
                preparedStatement.close();
            } catch (SQLException se) {
                    /*can't do anything */
            }
            try {
                resultSet.close();
            } catch (SQLException se) {
                    /*can't do anything */
            }
        }
        return userEntity;
    }

    public void update(UserEntity entity) throws DaoException {

    }

    public void delete(UserEntity entity) throws DaoException {

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
