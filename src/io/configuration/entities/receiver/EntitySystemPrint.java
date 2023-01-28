package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.ISystem;

class EntitySystemPrint extends AbstractEntityModule implements ISystem {

    static final String PRINT_RECEIVER = "print_receiver";

    EntitySystemPrint() {
        super();
    }

}