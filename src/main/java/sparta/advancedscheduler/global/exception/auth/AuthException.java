package sparta.advancedscheduler.global.exception.auth;

public abstract class AuthException extends RuntimeException{
    AuthException(String message) { super(message); }
}
