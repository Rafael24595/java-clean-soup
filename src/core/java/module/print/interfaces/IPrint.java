package core.java.module.print.interfaces;

import core.java.entities.Panel;
import core.java.module.IModule;
import core.java.module.print.ConsolePrint;

public interface IPrint extends IModule {

    Class<ConsolePrint> def_instance = ConsolePrint.class;
    void print(Panel panel);

}
