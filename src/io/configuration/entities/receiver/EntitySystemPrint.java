package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.ISystem;

class EntitySystemPrint extends AbstractEntityModule implements ISystem {

    static final String PRINTER_SYSTEM = "printer";

    EntitySystemPrint() {
        super();
    }

}