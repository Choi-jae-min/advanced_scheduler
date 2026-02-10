package sparta.advancedscheduler.global.exception.auth;

public class UnauthorizedException extends AuthException {
    public UnauthorizedException() {
        super("유효하지 않은세션입니다.");
    }
}
