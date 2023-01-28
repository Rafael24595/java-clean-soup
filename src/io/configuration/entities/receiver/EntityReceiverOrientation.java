package io.configuration.entities.receiver;

import io.configuration.entities.receiver.interfaces.IOrientationReceiver;

class EntityReceiverOrientation extends AbstractEntityReceiver implements IOrientationReceiver {

    static final String ORIENTATION_RECEIVER = "orientation_receiver";

    EntityReceiverOrientation(){
        super();
    }

}