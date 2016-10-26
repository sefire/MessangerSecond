package by.mnk.htp.glotovs.msr.services.interfaces;

import by.mnk.htp.glotovs.msr.entities.IEntity;
import by.mnk.htp.glotovs.msr.services.exception.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sefire on 25.10.2016.
 */
public interface IService<E extends IEntity, K extends Serializable> {

    List<E> getAll() throws ServiceException;

    void create(E object) throws ServiceException;

    E read (K id) throws ServiceException;

    void update(E object) throws ServiceException;

    void delete(E object) throws ServiceException;
}
