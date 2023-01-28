package core.java.module.log.interfaces;

import core.java.module.IModule;
import core.java.module.log.DefaultLog;

public interface ILog extends IModule {

    Class<DefaultLog> def_instance = DefaultLog.class;
    void log(String message);

}
