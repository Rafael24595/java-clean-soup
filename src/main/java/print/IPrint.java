package print;

import dependency.IProjectInterface;
import entities.Panel;

public interface IPrint extends IProjectInterface {

    Class<ConsolePrint> def_instance = ConsolePrint.class;
    void print(Panel panel);

}
