package christmas.model.exception;

public class BlankException extends IllegalArgumentException {
    private static final String INVALID_BLANK = "[ERROR] 아무것도 입력하지 않았습니다.";

    public BlankException() {
        super(INVALID_BLANK);
    }
}
