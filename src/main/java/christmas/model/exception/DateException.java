package christmas.model.exception;

public class DateException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public DateException() {
        super(ERROR_MESSAGE);
    }
}
