package sparta.advancedscheduler.global.exception.auth;

public class InvalidPasswordException extends AuthException {
    public InvalidPasswordException() {
        super("계정 또는 비밀번호가 올바르지 않습니다.");
    }
}
