package core.java.log;

import core.java.dependency.DependencyContainer;
import core.java.exception.DependencyException;
import core.java.module.log.interfaces.ILog;

public class Log {

    private Log(){
    }

    public static void log(String message) throws DependencyException {
        ILog log = DependencyContainer.getInstance(ILog.class);
        log.log(message);
    }

}
