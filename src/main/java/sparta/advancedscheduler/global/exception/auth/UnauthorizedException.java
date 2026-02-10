package sparta.advancedscheduler.global.exception.auth;

public class UnauthorizedException extends AuthException {
    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException() {
        super("유효하지 않은세션입니다.");
    }
}
