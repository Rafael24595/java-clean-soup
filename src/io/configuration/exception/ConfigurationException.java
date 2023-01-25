package io.configuration.exception;

import core.java.exception.ErrorCode;
import core.java.exception.SoupException;

import static core.java.exception.ErrorCode.*;

public class ConfigurationException extends SoupException {

    private static final ErrorCode CODE = CONFIGURATION_EXCEPTION;

    public ConfigurationException(Throwable cause) {
        super(cause, CODE);
    }

    public ConfigurationException(Throwable cause, ErrorCode code) {
        super(cause, code);
    }

    public ConfigurationException(String message, String ...args) {
        super(CODE, message, args);
    }

    public ConfigurationException(ErrorCode code, String message, String ...args) {
        super(code, message, args);
    }

    public ConfigurationException(Throwable cause, String message, String ...args) {
        super(cause, CODE, message, args);
    }

    public ConfigurationException(Throwable cause, ErrorCode code, String message, String ...args) {
        super(cause, code, message, args);
    }

}