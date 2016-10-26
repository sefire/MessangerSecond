package by.mnk.htp.glotovs.msr.dao.interfaces;
import by.mnk.htp.glotovs.msr.dao.exception.DaoException;
import by.mnk.htp.glotovs.msr.entities.IEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sefire on 24.10.2016.
 */
public interface IDao <E extends IEntity, K extends Serializable> {

    List<E> getAll() throws DaoException;

    void create(E entity) throws DaoException;

    E read(K id) throws DaoException;

    void update(E entity) throws DaoException;

    void delete(E entity) throws DaoException;
}
