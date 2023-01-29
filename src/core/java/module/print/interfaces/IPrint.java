package core.java.module.print.interfaces;

import core.java.entities.Panel;
import core.java.module.IModule;
import core.java.module.print.ConsolePrinter;

public interface IPrint extends IModule {

    Class<ConsolePrinter> def_instance = ConsolePrinter.class;
    void print(Panel panel);

}
