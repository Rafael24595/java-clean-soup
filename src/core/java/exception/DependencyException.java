package core.java.exception;

import static core.java.exception.ErrorCode.*;

public class DependencyException extends SoupException {
    
    private static final ErrorCode CODE = DEPENDENCY_EXCEPTION;

    public DependencyException(String message, String ...args){
        super(CODE, message, args);
    }

    public DependencyException(ErrorCode code, String message, String ...args){
        super(code, message, args);
    }

    public DependencyException(Throwable cause, String message, String ...args){
        super(cause, CODE, message, args);
    }

    public DependencyException(Throwable cause, ErrorCode code, String message, String ...args){
        super(cause, code, message, args);
    }

}