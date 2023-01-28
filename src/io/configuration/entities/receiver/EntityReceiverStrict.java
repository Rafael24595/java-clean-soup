package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.IStrictReceiver;

class EntityReceiverStrict extends AbstractEntityReceiver implements IStrictReceiver {
    
    static final String STRICT_RECEIVER = "strict_receiver";

    EntityReceiverStrict(){
        super();
    }

}