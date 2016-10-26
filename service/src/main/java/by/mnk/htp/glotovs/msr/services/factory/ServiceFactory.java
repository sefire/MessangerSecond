package by.mnk.htp.glotovs.msr.services.factory;

import by.mnk.htp.glotovs.msr.services.interfaces.IService;
import by.mnk.htp.glotovs.msr.services.impl.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sefire on 25.10.2016.
 */


public class ServiceFactory {
    private static volatile ServiceFactory instance = null;

    private Map<ServiceName, IService> serviceMap = null;

    private ServiceFactory() {
        serviceMap = new HashMap<ServiceName, IService>();
        serviceMap.put(ServiceName.USER, new UserService());
        //serviceMap.put(ServiceName.FRIEND, new ProductService());
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                    instance = new ServiceFactory();
                } else {
                    return instance;
                }
            }
        }

        return instance;
    }

    public IService getService(ServiceName serviceName) {
        return serviceMap.get(serviceName);
    }
}
