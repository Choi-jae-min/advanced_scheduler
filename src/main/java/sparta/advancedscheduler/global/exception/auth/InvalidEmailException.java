package sparta.advancedscheduler.global.exception.auth;

public class InvalidEmailException extends AuthException {
    public InvalidEmailException() {
        super("계정 또는 비밀번호가 올바르지 않습니다.");
    }
}
