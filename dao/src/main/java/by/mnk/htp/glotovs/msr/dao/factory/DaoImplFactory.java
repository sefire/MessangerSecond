package by.mnk.htp.glotovs.msr.dao.factory;

import by.mnk.htp.glotovs.msr.dao.interfaces.IDao;
import by.mnk.htp.glotovs.msr.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sefire on 25.10.2016.
 */

public class DaoImplFactory {
    private static volatile DaoImplFactory instance = null;

    private Map<DaoImplName, IDao> daoImplMap = null;

    private DaoImplFactory() {
        daoImplMap = new HashMap<DaoImplName, IDao>();
        daoImplMap.put(DaoImplName.USER, new UserDaoImpl());
/*        daoImplMap.put(DaoImplName.FRIEND, new FriendDaoImpl());
        daoImplMap.put(DaoImplName.CHAT, new ChatDaoImpl());
        daoImplMap.put(DaoImplName.MESSAGE, new MessageDaoImpl());*/
    }

    public static DaoImplFactory getInstance() {
        if (instance == null) {
            synchronized (DaoImplFactory.class) {
                if (instance == null) {
                    instance = new DaoImplFactory();
                } else {
                    return instance;
                }
            }
        }
        return instance;
    }

    public IDao getDaoImpl (DaoImplName daoImplName) {
        return daoImplMap.get(daoImplName);
    }
}
