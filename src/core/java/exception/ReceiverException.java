package core.java.exception;

import static core.java.exception.ErrorCode.*;

public class ReceiverException extends SoupException {

    private static final ErrorCode CODE = RECEIVER_EXCEPTION;

    public ReceiverException(Throwable cause) {
        super(cause, CODE);
    }

    public ReceiverException(Throwable cause, ErrorCode code) {
        super(cause, code);
    }

    public ReceiverException(String message, String ...args) {
        super(CODE, message, args);
    }

    public ReceiverException(ErrorCode code, String message, String ...args) {
        super(code, message, args);
    }

    public ReceiverException(Throwable cause, String message, String ...args) {
        super(cause, CODE, message, args);
    } 

    public ReceiverException(Throwable cause, ErrorCode code, String message, String ...args) {
        super(cause, code, message, args);
    } 

}
