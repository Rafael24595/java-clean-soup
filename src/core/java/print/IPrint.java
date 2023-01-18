package core.java.print;

import core.java.dependency.IProjectInterface;
import core.java.entities.Panel;

public interface IPrint extends IProjectInterface {

    Class<ConsolePrint> def_instance = ConsolePrint.class;
    void print(Panel panel);

}
