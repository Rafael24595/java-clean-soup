package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.IPrint;

class Print extends AbstractReceiver implements IPrint {

    static final String PRINT_RECEIVER = "print_receiver";

    Print() {
        super();
    }

}