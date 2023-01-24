package core.java.exception;

import static core.java.exception.ErrorCode.*;

public class DependecyException extends SoupException {
    
    private static final ErrorCode CODE = DEPENDENCY_EXCEPTION;

    public DependecyException(String message, String ...args){
        super(CODE, message, args);
    }

    public DependecyException(ErrorCode code, String message, String ...args){
        super(code, message, args);
    }

    public DependecyException(Throwable cause, String message, String ...args){
        super(cause, CODE, message, args);
    }

    public DependecyException(Throwable cause, ErrorCode code, String message, String ...args){
        super(cause, code, message, args);
    }

}