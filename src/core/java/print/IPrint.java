package core.java.print;

import core.java.entities.Panel;
import core.java.receiver.IReceiver;

public interface IPrint extends IReceiver {

    Class<ConsolePrint> def_instance = ConsolePrint.class;
    void print(Panel panel);

}
