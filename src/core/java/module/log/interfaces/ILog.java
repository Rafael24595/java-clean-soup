package core.java.module.log.interfaces;

import core.java.module.IModule;
import core.java.module.log.ConsoleLog;

public interface ILog extends IModule {

    Class<ConsoleLog> def_instance = ConsoleLog.class;
    void log(String message);

}
