package core.java.module.log;

import core.java.module.log.interfaces.ILog;

public class ConsoleLog implements ILog {

    @Override
    public void log(String message) {
        System.out.println(message);
    }

}
