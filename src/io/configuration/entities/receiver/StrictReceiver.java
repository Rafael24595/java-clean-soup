package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.IStrictReceiver;

class StrictReceiver extends AbstractReceiver implements IStrictReceiver {
    
    static final String STRICT_RECEIVER = "strict_receiver";

    StrictReceiver(){
        super();
    }

}