package core.java.exception;

import core.java.tools.MessageBuilder;

import static core.java.exception.ErrorCode.*;

public class SoupException extends Exception {
    
    private static final ErrorCode CODE = SOUP_EXCEPTION;

    private final String rawMessage;
    private final String[] args;

    public SoupException(Throwable cause) {
        this(cause, "");
    }

    public SoupException(Throwable cause, ErrorCode code) {
        this(cause, code, "");
    }

    public SoupException(String message, String ...args) {
        this(CODE, message, args);
    }

    public SoupException(ErrorCode code, String message, String ...args) {
        this(null, code, message, args);
    }

    public SoupException(Throwable cause, String message, String ...args) {
        this(cause, CODE, message, args);
    }

    public SoupException(Throwable cause, ErrorCode code, String message, String ...args) {
        super(MessageBuilder.build(code.name(), message.isEmpty() ? "Error general" : message, args), cause);
        this.rawMessage = message;
        this.args = args;
    }

    protected String getRawMessage(){
        return this.rawMessage;
    }

    protected String[] getMessageArgs(){
        return this.args;
    }

}